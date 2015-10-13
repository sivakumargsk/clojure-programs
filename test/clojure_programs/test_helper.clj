(ns clojure-programs.test-helper
  (:use clojure.test))

(defmethod report :begin-test-ns [m]
  (with-test-out
    (if (some #(:test (meta %)) (vals (ns-interns (:ns m))))
      (println "\nTesting" (ns-name (:ns m))))))

(.addShutdownHook
 (Runtime/getRuntime)
 (proxy [Thread] []
   (run []
     (run-all-tests))))
