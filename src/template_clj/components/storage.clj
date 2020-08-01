(ns template-clj.components.storage
  (:require [com.stuartsierra.component :as component]))

(defrecord InMemoryStorage [storage]
  component/Lifecycle
  (start [component]
    component)

  (stop  [component]
    (reset! storage {})
    component))

(defn new-in-memory []
  (->InMemoryStorage (atom [{:user/id 1
                             :user/name "Chico"
                             :user/email "xico@xico.com.br"}])))
