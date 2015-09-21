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

;;clojure-programs.core> (is-prime? 101)
;;true
;;clojure-programs.core> (is-prime? 99)
;;false



;;Another
;;function for finding the given number is prime or not.
;;Using reduce function concept.

(defn is-prime?? [n]
  "This function also returns true if the given number is prime or not"
  (empty?  (if (< n 2)
             ["i am not a prime numbers"]
             (reduce (fn [result element]
                       (if (= 0 (mod n element))
                         (conj result element)
                         result))
                     []
                     (vec (range 2 n))))))

;;clojure-programs.core> (is-prime?? 3)
;;true
;;clojure-programs.core> (is-prime?? 153)
;;false


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

;;clojure-programs.core> (prime-seq2 100)
;;(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97
;;101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179 181 191
;;193 197 199 211 223 227 229 233 239 241 251 257 263 269 271 277 281 283
;;293 307 311 313 317 331 337 347 349 353 359 367 373 379 383 389 397 401
;;409 419 421 431 433 439 443 449 457 461 463 467 479 487 491 499 503 509
;;521 523 541)

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
;;(defn is-prime? [n]  (empty? (filter #(= 0 (mod n  %)) (range 2 n))))
