(ns sin-move.core-test
    (:require [cheshire.core :as cheshire]
            [clojure.test :refer :all]
            [sin-move.core :refer :all]
            [ring.mock.request :as mock]))

(defn parse-body [body]
    (cheshire/parse-string (slurp body) true))

(deftest endpoints-test

    (testing "Test GET request to /api returns expected response"
        (let [response (app (-> (mock/request :get  "/api")))
            body (parse-body (:body response))]
        (is (= (:status response) 200))
        (is (= (:result body) "Hello Clj!"))))
        
    (testing "Test GET request to /sum returns expected response"
        (let [response (app (-> (mock/request :get "/api/sum?x=10&y=2")))
            body (parse-body (:body response))]
        (is (= (:status response) 200))
        (is (= (:result body) 12))))
       
    (testing "Test POST request to /lang returns expected response"
        (let [lang {:name "Clojure"
                        :description "Robust, practical, and fast programming language."
                        :functional true}
                response (app (-> (mock/request :post "/api/lang")
                                (mock/content-type "application/json")
                                (mock/body  (cheshire/generate-string lang))))
                body (parse-body (:body response))]
            (is (= (:status response) 200))
            (is (= body lang)))))
