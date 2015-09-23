(ns clojure-programs.core
  (:gen-class))

;;function for finding a maximum values in the given list
;;using reduce method
(defn maximum2 [coll]
  (if (empty? coll)
    nil
    (reduce (fn [x y] (if (> x y) x y)) coll)))
;;clojure-programs.core> (maximum2 [1 2 9 7 5])
;;9

;;clojure-programs.core> (maximum2 [])
;;nil

;;==============================================================================
;;writing the maximum function higher order functions and tail recursion

(defn max-two [x y]
  (if (> x y) x y))

;;clojure-programs.core> (max-two 6 9)
;;9

(defn max-all [coll]
  (if (empty? coll)
    nil
    (loop [c coll
           acc 0]
      (if (empty? c)
        acc
        (recur (rest c) (max-two acc (first c)))))))

;;clojure-programs.core> (max-all [1 2 3 4 5 6 7 8 9])
;;9
;;clojure-programs.core> (max-all [7 5 9 42 1 55 62 453 8])
;;453

;;============================================================================
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

;;========================================================================
;;it is also shows the value but it takes large amount of time

;; using sort function
(defn maximum [x]
  (last (vec (sort x))))
