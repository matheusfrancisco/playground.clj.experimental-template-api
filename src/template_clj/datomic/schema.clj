(ns template-clj.datomic.schema
  (:require [datomic-schema.schema :as s]
            [datomic.api :as d]
            [template-clj.datomic.fixtures :refer [tx-fixtures]]))

(defn dbschema []
  [{:db/ident       :user/name
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident       :user/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}
   {:db/ident       :user/email
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}
   {:db/ident       :user/password
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident       :user/type
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}])

(defn in-memory-db? [uri]
  (if (re-find #"^datomic:mem://" uri) true false))

(defn initialize-db [conn uri]
  @(d/transact-async
     conn
     (dbschema))

  (when (in-memory-db? uri)
    @(d/transact-async conn (tx-fixtures))))

(defn setup-db [uri]
  (d/create-database uri)
  (let [conn (d/connect uri)]
    (initialize-db conn uri)
    conn))
