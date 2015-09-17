(ns clojure-programs.core
  (:gen-class))

;;function for finding the given number is prime or not.
;;Using filter function concept.

(defn is-prime? [num]
  "this function returns true if the given number is prime or false "
  (empty? (if (< num 2)
            [0 1]
            (filter (fn [x]
                      (if (and (= 0 (mod num x)) (> num 1)) true false))
                    (range 2 num)))))

;;(defn is-prime? [n]  (empty? (filter #(= 0 (mod n  %)) (range 2 n))))

;;Another
;;function for finding the given number is prime or not.
;;Using reduce function concept.

(defn is-prime?? [n]
  "This function also returns true if the given number is prime or not"
  (empty?  (if (< n 2)
             [0 1]
             (reduce (fn [result element]
                       (if (= 0 (mod n element))
                         (conj result element)
                         result))
                     []
                     (vec (range 2 n))))))


;;function for finding the given number is prime or not.
;;i was trying to implement this concept on tail recursion
;;but its not working
(defn is_prime [num]
  (if (< num  2)
    false
    (loop [n num
           acc false
           den 2]
      (if (== (mod n den) 0)
        acc
        (recur n  true (inc den))))))


;;Function for generating sequence of prime numbers upto given size
;;with help of take, filter and is-prime? functions

(defn prime-seq [n]
  "This function returns the given size of prime numbers"
  (take n (filter is-prime? (iterate inc 0))))



;;another
;;Function for generating sequence of prime numbers upto given size
;;with help of take, filter and is-prime?? functions
(defn prime-seq2 [n]
  "this function also returns the given size of prime numbers"
  (take n (filter is-prime?? (iterate inc 0))))



;;;=============================================================
;;;rough works and testing work

(defn check [n]
  (if (< n 2)
    [0 1]
    (reduce (fn [result element]
              (if (= 0 (mod n element))
                (conj result element)
                result))
            []
            (vec (range 2 n)))))



(defn x [n r e]
  (if (< n 2)
    (conj r n)
    (if (> n 2)
      (conj r e)
      r)))
