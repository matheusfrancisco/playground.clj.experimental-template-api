(ns template-clj.core
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [template-clj.components.config :as config]
            [template-clj.components.routes :as routes]
            [template-clj.components.storage :as storage]
            [template-clj.components.database :as db]
            [template-clj.components.webserver :as webserver]
            [template-clj.components.datomic :as dt]
            [template-clj.server :as server]
            [template-clj.service :as service]
            [io.pedestal.service-tools.dev :as dev]
            [environ.core :refer [env]]))

(def system (atom nil))

(defn- build-system-map []
  (component/system-map
    :config (config/new-config config/config-map)
    :storage (storage/new-in-memory)
    :db (db/create-database! config/db)
    :datomic (dt/new-datomic (env :db-connection-uri))
    :routes  (routes/new-routes #'template-clj.service/routes)
    :http-server (component/using (webserver/new-webserver) [:config :routes :storage :db :datomic])))

(defn -main
  "The entry-point for 'lein run-dev'"
  [& args]
  (-> (build-system-map)
      (server/start-system! system)))

(defn run-dev []
  (dev/watch) ;; auto-reload namespaces only in run-dev / repl-start
  (-main))
