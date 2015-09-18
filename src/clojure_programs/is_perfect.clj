(ns clojure-programs.core
  (:gen-class))

;;function for finding the given number is perfect or not
;; i.e the all positive divisor sum is equal to that number
;; eg: 6 = 3 + 2 + 1 where 3 2 1 are their divisors

;;for this program i create a function divisors which stores list of all possible
;;divisors for the given element.....
(defn divisors [n]
  (reduce (fn [result element]
            (if (= 0 (mod n element))
              (conj result element)
              result))
          []
          (range 1 (inc (/ n 2)))))

;; now calling the above function in the is_perfect function.

(defn is_perfect [n]
  (if (= n (reduce + (divisors n)))
    true
    false))
