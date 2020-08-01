(ns template-clj.components.database
  (:require [com.stuartsierra.component :as component]
            [next.jdbc :as jdbc]))


(def db {:dbtype "postgresql"
         :dbname "example"
         :user "postgres"
         :password "postgres"
         :host "127.0.0.1"
         :port 5432})


(defn create-connection [option]
  (jdbc/get-datasource option))

(defrecord DatabasePostgress
  [option conn]
  component/Lifecycle
  (start [component]
    (if conn
      component
      (let [conn (create-connection option)]
            ;jdbc/execute! conn ["CREATE TABLE user
            ;;                    user_uuid int,
            ;;                    username varchar(255),
            ;                    email varchar(255)"]]
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

(defn create-database! []
  (->DatabasePostgress db nil))

