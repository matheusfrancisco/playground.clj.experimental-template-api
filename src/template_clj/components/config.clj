(ns template-clj.components.config
  (:require [com.stuartsierra.component :as component]))

(defrecord Config [config]
  component/Lifecycle
  (start [this] this)
  (stop  [this] this))

(def config-map
  {:http-port (Integer/parseInt (or (System/getenv "HTTP_PORT") "3000"))
   :http-host (or (System/getenv "HTTP_HOST") "localhost")})

(defn new-config [input-map] (map->Config {:config (or input-map config-map)}))

