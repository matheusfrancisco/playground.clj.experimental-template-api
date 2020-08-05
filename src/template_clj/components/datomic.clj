(ns template-clj.components.datomic
  (:require [com.stuartsierra.component :as component]
            [template-clj.datomic.schema :refer [setup-db]]
            [datomic.api :as d])
  (:gen-class))

(defn in-memory-db? [uri]
  (if (re-find #"^datomic:mem://" uri) true false))

(defrecord Datomic [uri]
  component/Lifecycle
  (start [{conn :conn :as component}]
    (if conn
      component
      (let [conn (setup-db uri)]
        (assoc component :conn conn))))

  (stop [{conn :conn :as component}]
    (if-not conn
      component
      (do
        (d/delete-database uri)
        (assoc component :conn nil)))))

(defn new-datomic [uri]
  (map->Datomic {:uri uri}))
