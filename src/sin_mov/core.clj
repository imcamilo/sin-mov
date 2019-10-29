(ns sin-mov.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json])
  (:gen-class))

; simple endpoint
(defn simple-endpoint [req]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (str (json/write-str "Hello World"))})

; simple request
(defn simple-request [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str req)})

; Main routes
(defroutes app-routes
  (GET "/" [] simple-endpoint)
  (GET "/simplereq" [] simple-request)
  (route/not-found "Error, page not found!"))

; Main entry function
(defn -main
  "This is the main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8766"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running with lein and Clojure at http:/127.0.0.1:" port "/"))))