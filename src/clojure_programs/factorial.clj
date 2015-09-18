(ns clojure-programs.core
  (:gen-class))


;; function for finding a factorial of the given number
;; using tail recursion functionality
(defn factorial [x]
  (loop [n x
         acc 1]
    (if (= n 0)
      acc
      (recur (dec n) (* n acc) ))))


;; function for finding a factorial of the given number
;; using reduce method

(defn factorial1 [n]
  (reduce * (range 1 (inc n))))


;; function for finding a factorial of the given number
;; using recursion method-sig
(defn factorial2 [n]
  (if (= n 0)
    (*)
    (* n (factorial2 (dec n)))))
