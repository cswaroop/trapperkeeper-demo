(ns trapperkeeper-demo.trapperkeeper-demo-web-core-test
  (:require [clojure.test :refer :all]
            [trapperkeeper-demo.trapperkeeper-demo-service :as meow-svc]
            [trapperkeeper-demo.trapperkeeper-demo-web-core :refer :all]
            [ring.mock.request :as mock]))

(deftest meow-web-test
  (testing "says meow to caller"
    (let [meow-service (reify meow-svc/MeowService
                          (meow [this caller] (format "Testing, %s." caller)))
          ring-app (app meow-service)
          response (ring-app (mock/request :get "/foo"))]
      (is (= 200 (:status response)))
      (is (= "Testing, foo." (:body response))))))
