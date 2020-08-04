(ns template-clj.components.database
  (:require [com.stuartsierra.component :as component]
            [next.jdbc.connection :as connection]
            [next.jdbc :as jdbc])
  (:import (com.zaxxer.hikari HikariDataSource)))


(defrecord DatabasePostgress
  [option conn]
  component/Lifecycle
  (start [component]
    (if conn
      component
      (let [conn (connection/->pool HikariDataSource option)]
        (assoc component :conn conn))))

  (stop  [component]
    (if conn
      (do
        (try
          (.close conn)
          (catch Exception e
            (prn "Error while stoping database")))
        (assoc component :conn nil))
      component)))

(defn create-database! [db]
  (->DatabasePostgress db nil))

