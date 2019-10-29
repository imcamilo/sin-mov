(ns sin-move.core
    (:require [compojure.api.sweet :refer :all]
              [ring.util.http-response :refer :all]
              [schema.core :as s]))

(def app
    (api

        {:swagger
        {:ui "/"
         :spec "/swagger.json"
         :data {:info {:title "sin-mov"
                       :description "Compojure usage for students and collaborators"}
                :tags [{:name "api", :description "API dev with their tests"}]}}}

        (context "/api" []
        :tags ["api"]
    
        (GET "/" []
            :return {:result s/Str}
            :query-params []
            :summary "adds two numbers together"
            (ok {:result "Hello Clj!"}))
    
        (GET "/sum" []
            :return {:result Long}
            :query-params [x :- Long, y :- Long]
            :summary "sum two values"
            (ok {:result (+ x y)})))))
                  