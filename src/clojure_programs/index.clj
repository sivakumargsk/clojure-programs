(ns clojure-programs.core
  (:gen-class))

;;function for returning the index of the given element in a list
;; using tail recursion

(defn index [coll ele]
  (if (= nil (first coll))
    (- 1)
    (loop [c coll
           e ele
           acc 0]
      (if (= e (first c))
        acc
        (recur (rest c) e (inc acc))))))
