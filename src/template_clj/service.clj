(ns template-clj.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]))

(defn- sucess-response
  [result status]
   (-> result
       ring-resp/response
       (ring-resp/status status)))

(defn- bad-response
  ([errors]
   (bad-response errors 500))
  ([errors status]
   (let [st (if (nil? status) 500 status)]
     (-> {:errors errors}
         ring-resp/response
         (ring-resp/status st)))))

(defn- handle-response-create!
  [response]
  (let [object response]
    (if false
      (bad-response "error" 404)
        (sucess-response {:message "create" :db object} 201))))


(defn home-page
  [request]
  (ring-resp/response {:message "Hello World!!" :status 200}))

(defn get-all-users! [storage]
  @storage)

(defn get-in-memory-database
  [{{storage :storage} :components}]
  (:storage storage))

(defn get-all-users
  [request]
  (prn request)
  (let [storage (get-in-memory-database request)]
    (ring-resp/response {:message (get-all-users! storage)})))

(def common-interceptors
  [(body-params/body-params) http/json-body])

(def routes
  #{["/" :get (conj common-interceptors `home-page)]
    ["/users" :get (conj common-interceptors `get-all-users)]})
