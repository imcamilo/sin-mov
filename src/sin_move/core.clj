(ns sin-move.core
    (:require [compojure.api.sweet :refer :all]
              [ring.util.http-response :refer :all]
              [schema.core :as s]))

(s/defschema Language
{:name s/Str
    (s/optional-key :description) s/Str
    :functional boolean})

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
            :summary "expose simple message in json"
            (ok {:result "Hello Clj!"}))
    
        (GET "/sum" []
            :return {:result Long}
            :query-params [x :- Long, y :- Long]
            :summary "sum two values"
            (ok {:result (+ x y)}))
            
        (POST "/lang" []
            :return Language
            :body [lang Language]
            :summary "Receive a lang"
            (ok lang)))))
                  