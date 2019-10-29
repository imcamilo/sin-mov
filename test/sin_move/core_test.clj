(ns sin-move.core-test
    (:require [cheshire.core :as cheshire]
            [clojure.test :refer :all]
            [sin-move.core :refer :all]
            [ring.mock.request :as mock]))

(defn parse-body [body]
    (cheshire/parse-string (slurp body) true))

(deftest endpoints-test
    (testing "Test GET request to / returns expected response"
        (let [response (app (-> (mock/request :get  "/api")))
            body (parse-body (:body response))]
        (is (= (:status response) 200))
        (is (= (:result body) "Hello Clj!")))))
    