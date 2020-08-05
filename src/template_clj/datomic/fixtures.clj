(ns template-clj.datomic.fixtures
  (:require [datomic.api :as d]))

(def user-id #uuid "579ef389-525e-4017-bdd7-3fb63a42d2a1")


(def user
    [{:user/id user-id
      :user/email "adm@gmail.com"
      :user/name "Xico"
      :user/password "123123123"
      :user/type  "user"}])


(defn tx-fixtures
  "Returns a transaction which installs all the fixture data."
  []
  (concat
    user))
