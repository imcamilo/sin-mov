(ns sin-move.core
    (:require [compojure.api.sweet :refer :all]
              [ring.util.http-response :refer :all]
              [schema.core :as s]))

(def app
    (api
    
        (context "/api" []
        :tags ["api"]
    
        (GET "/" []
            :return {:result s/Str}
            :query-params []
            :summary "adds two numbers together"
            (ok {:result "Hello Clj!"}))
    
        (GET "/merge" []
            :return {:result Long}
            :query-params [x :- Long, y :- Long]
            :summary "sum two values"
            (ok {:result (+ x y)})))))
                  