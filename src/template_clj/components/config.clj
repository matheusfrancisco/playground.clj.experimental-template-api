(ns template-clj.components.config
  (:require [com.stuartsierra.component :as component]))

(defrecord Config [config]
  component/Lifecycle
  (start [this] this)
  (stop  [this] this))

(def db {:dbtype "postgresql"
         :dbname "example"
         :username "postgres"
         :password "postgres"
         :host "localhost"
         :port 5432})

(def config-map
  {:db db
   :http-port (Integer/parseInt (or (System/getenv "HTTP_PORT") "3000"))
   :http-host (or (System/getenv "HTTP_HOST") "localhost")})


(defn new-config [input-map] (map->Config {:config (or input-map config-map)}))


