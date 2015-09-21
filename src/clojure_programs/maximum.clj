(ns clojure-programs.core
  (:gen-class))

;;function for finding a maximum values in the given list
;; using sort function
(defn maximum [x]
  (last (vec (sort x))))


;; function for finding a maximum value in given list
;; using tail recursion

(defn maximum1 [coll]
  (if (empty? coll)
    nil
    (loop [c coll
           acc 0]
      (if (empty? c)
        acc
        (recur (rest c) (if (< acc (first c))
                          (first c)
                          acc))))))

;;clojure-programs.core> (maximum1 [66 75 96 101 254 784 12410 21 3220])
;;12410
;;clojure-programs.core> (maximum1 [0 0 0 0 0 0 0])
;;0
;;clojure-programs.core> (maximum1 [0 -4  -5  -6 1  0 0])
;;1
;;clojure-programs.core> (maximum1 [])
;;nil
