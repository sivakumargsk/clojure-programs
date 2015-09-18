(ns clojure-programs.core
  (:gen-class))

;;function for finding a maximum values in the given list
;; using sort function
(defn maximum [x]
  (last (vec (sort x))))


;; function for finding a maximum value in given list
;; using tail recursion

(defn maximum1 [coll]
  (loop [c coll
         acc 0]
    (if (= nil (first c))
      acc
      (recur (rest c) (if (< acc (first c))
                        (first c)
                        acc)))))
