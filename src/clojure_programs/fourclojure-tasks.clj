(ns clojure-programs.fourclojure-tasks
  (:require [clojure.string :as cstr])
  (:require [clojure.set :as set1]))

;;=================================================================================
;;week1=== Tasks
;;=================================================================================

;;task 19
(defn last_element [x]
  (nth x (dec (count x))))

;;using tail recursion
(defn last_element1 [x]
  (loop [coll x]
    (if (= 1 (count coll))
      (first coll)
      (recur (next coll)))))



;;task 21
;;Special Restrictions---nth
(defn nth_element [x e]
  ((vec x) e))

;; using tail recursion
(defn nth_element1 [xcoll ele]
  (loop [coll xcoll
         acc 0]
    (if (= acc ele)
      (first coll)
      (recur (next coll) (inc acc)))))



;;task 24
(defn sum_it_all [xcoll]
  (reduce + xcoll))

;;using tail recursion
(defn sum_it_all-1 [xcoll]
  (let [coll (vec xcoll)]
    (loop [coll xcoll
           sum 0]
      (if (empty? coll)
        sum
        (recur (next coll) (+ sum (first coll)))))))



;;task25
;;(= (__ #{1 2 3 4 5}) '(1 3 5))
;;(= (__ [4 2 1 6]) '(1))
;;(= (__ [2 2 4 6]) '())
;;(= (__ [1 1 1 3]) '(1 1 1 3))
;;Find the odd numbers
(defn list_odd [x]
  (filter odd? x))

;;i am writing this much of code because its satisfies above test conditions
(defn list_odd1 [xcoll]
  (reverse (loop [coll xcoll
                  ans '()]
             (if (empty? coll)
               ans
               (recur (next coll) (if (odd? (first coll))
                                    (conj ans (first coll))
                                    ans))))))


;;function works for both strings and sequence
(defn is_palindrome? [x]
  (let [result (seq x)]
    (if (= result (reverse result))
      true
      false)))

;;(= (__ [1 2 3]) '(1 1 2 2 3 3))
(defn dupl-seq [x]
  (list* (reduce (fn [r e]
                   (conj r  e e))
                 []
                 x)))

(defn dup-seq1 [xs]
  (reduce #(apply conj %1 (list %2 %2)) [] xs))


;;task 30
;;(= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
;;(= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(defn compress-seq [x]
  (let [xs (seq x)]
    (list* (reduce (fn [r e]
                     (if (= (last r) e)
                       r
                       (conj r e)))
                   []
                   x))))

(defn compress-seq1 [x]
  (mapcat set(#(partition-by identity %1) x)))


;;task31
;;(= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(defn pack-a-sequence [xs]
  (partition-by identity xs))


;;task33
;;(=	(__[1 2	3] 2) '(1 1 2 2 3 3))
(defn replicate-seq [coll n]
  (mapcat (fn [ele] (repeat n ele)) coll))


;;task41
;;(=	(__ [1 2 3 4 5 6 7 8] 3)[1 2 4 5 7 8])
(defn drop-every [coll n]
  (when coll
    (lazy-cat (take (dec n) coll) (drop-every (nthnext coll n) n))))

;;================================================================================================
;;Week2 === Tasks
;;================================================================================================

;;task26
;;(= (__ 3) '(1 1 2))
;; fibonacci series using tail recurssion
(defn fib-series1 [n]
  (loop [sum 0
         ans []
         x 1
         y 1]
    (if (= n sum)
      ans
      (recur (inc sum) (if (> sum 1)
                         (conj ans  (+ x y) )
                         (conj ans x))  (if (> sum 1)
                                          (+ x y)
                                          x) (if (> sum 1)
                                               x
                                               y)))))

;;fibonacci series for given elements
(defn fib-series [a b]
  (cons a (lazy-seq (fib-series b (+ b a)))))

;;fibonacci series for a given series
(defn take-fib-series [n]
  (take n (fib-series 1 1)))


(defn take-fib [n]
  (take n ((fn fib-ser [a b]
             (cons a (lazy-seq (fib-ser b (+ a b)))))
           1 1)))

;;task 29 Return capital letter in a string
;;(= (__ "$#A(*&987Zf") "AZ")
(defn get-Cap [x]
  (reduce str (re-seq #"[A-Z]+" x)))


;;A Half-Truth---task 83
;;(= true (__ true true true false))
(defn half-truth [& coll]
  (= (set coll) (set [true false])))



;;greatest common divisor
(defn gcd-two [x y]
  (let [n (min x y )]
    (last (filter (fn [de]
                    (if(= (mod x de) (mod y de) 0) true false )) (range 1 (inc n))))))

(defn gcd-two1 [x y]
  (cond
    (zero? x) y
    (zero? y) x
    :else (recur y (mod x y))))


;;funtion for finding the value of n-to-the-power-of-m

(defn pow [n m]
  (reduce * (repeat m n)))


;;(= 256 ((__ 2) 16) ((__ 8) 2))
;;task 107 simple closures
(defn exp [x]
  (fn [y]
    (reduce * (repeat x y))))


;;task 90 Cartesian Product
;;(= (__ #{1 2 3} #{4 5})
;;#{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})
(defn cartesian-product [xcoll ycoll]
  (set (for [x xcoll
             y ycoll]
         [x y])))



;;task 88 Symmetric difference of given two sets
;;(= (__ #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
(defn symmetric-difference [xset yset]
  (set1/difference (set1/union xset yset) (set1/intersection xset yset)))

;;task 100 Least common multiple
;;(== (__ 2 3) 6)
;;(== (__ 5 3 7) 105)
(defn lcm [& nums]
  (reduce (fn [a b]
            (/ (* a b) ( gcd-two a b ) )) nums ))



;;task 97
;;funtion for returning the nth-row of a pascal triangle
;;(= (__ 11) [1 10 45 120 210 252 210 120 45 10 1])

;;now below function returns the a sequence
;;example pascal (5)
;;1 1 1 1 1  1
;;1 2 3 4  5
;;1 3 6  10
;;1 4  10
;;1  5
;; 1
;;Now below function returns this sequence with repect to above given seqence
(defn changing-coll  [coll]
  (pop (reduce (fn [x y]
                 (conj x (+ (last x) y)))
               [1]
               (next coll))))
;;four-clojure.core> (changing-coll [1 1 1 1 1 1 1 1 1])
;;[1 2 3 4 5 6 7 8]

(defn pascal [n]
  (loop [coll (take n (iterate identity 1))
         nth-row []]
    (if (empty? coll)
      nth-row
      (recur (changing-coll coll) (conj nth-row (last coll))))))

;;four-clojure.core> (pascal 11)
;;[1 10 45 120 210 252 210 120 45 10 1]

;;========================================================================================
;;week3 tasks
;;========================================================================================

;;task 46 Flipping-out
;;(= 3 ((__ nth) 2 [1 2 3 4 5]))
;;(= true ((__ >) 7 8))
(defn flipping-out [x]
  (fn [a b]
    (x b a)))

;;four-clojure.core> ((flipping-out nth)2 [1 2 3 4 5 6 7 9] )
;;3


;;task 47
;;Rotate a sequence
;;(= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
;;(= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
(defn rotate-seq [dir coll]
  (let [n (count coll)
        d (if (pos? dir)
            dir
            (- n (mod (* dir -1) n)))]
    (take n (drop d (cycle coll)))))

;;task 43
;;(= (__ [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))
;;(= (__ (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))

(defn reverse-interleave [xcoll lim]
  (let [ele-size (/ (count xcoll) lim)] ;;ele-size value is doesnot change in loop so i write it in let
    (reverse (loop [coll xcoll
                    seq-size lim
                    result '()]
               (if (pos? seq-size)
                 (recur (next coll)
                        (dec seq-size)
                        (conj result (take ele-size (take-nth lim coll))))
                 result)))))




;;task 50--Split by type
;;(= (set (__ [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
;;(= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})

;;Now i am creating a map with types as keys and element as values
;;example map = {java.lang.long [1 2 3] java.lang.string ["siva" "sai] java.lang.coll [[1 2] [2 3]]}
;;after creating this i just simply asking values of map
;;then it will shows output '( [1 2 3] ["siva" "sai"] [[1 2] [2 3]])
;;using tail recursion and restriction in group-by method
;;so i am creating my-group-by function

(defn my-group-by [map-value-coll-type fun xcoll]
  ;;"i am adding map-val-coll-type argu to improve more flexiblity to the function"
  (loop [coll xcoll
         newmap {}]
    (if (empty? coll)
      newmap
      (recur (next coll) (let [element (first coll)
                               mapkey (fun element)
                               mapval (newmap mapkey)]
                           (if (contains? newmap mapkey)
                             (assoc newmap mapkey (conj mapval element))
                             (assoc newmap mapkey (map-value-coll-type element))))))))


(defn split-by-type [xcoll]
  (vals (my-group-by vector type xcoll)))

;;using vals and groupby functions
(defn split-by-type1 [xcoll]
  (vals (group-by type xcoll)))



;;task 77 anagram
;;(= (__ ["meat" "mat" "team" "mate" "eat"])
;;=>#{#{"meat" "team" "mate"}})
;;(= (__ ["veer" "lake" "item" "kale" "mite" "ever"])
;;=>#{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})
;;restriction in group-by

(defn anagram [xcoll]
  (set (filter #(> (count %1) 1)
               (vals (my-group-by hash-set (fn [element]
                                             (cstr/join (sort element))) xcoll )))))


;;task 67 prime number series
;;(= (__ 2) [2 3])
;;(= (__ 5) [2 3 5 7 11])
;;(last (__ 100)) 541)
(defn is-prime? [num]
  (if (< num 2)
    false
    (empty? (filter (fn [x]
                      (if (= 0 (mod num x)) true false )) (range 2 num)))))

(defn prime-seq [n]
  "This function returns the given size of prime numbers"
  (vec (take n (filter is-prime? (iterate inc 0)))))

;;============================================================================================
;;4clojure Remaining Tasks
;;============================================================================================
;;problem 47

(defn my-interpose [value [x & more]]
  (lazy-seq
   (when x
     (if more
       (cons x (cons value (my-interpose value more)))
       (cons x nil)))))


;;problem 49
;;(= (__ 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
;;= (__ 1 [:a :b :c :d]) [[:a] [:b :c :d]])
(defn split-at-seq [n xcoll]
  (vector (subvec xcoll 0 n)
          (subvec xcoll n)))


(defn only-seq [coll]
  (loop [c coll
         a []]
    (if (empty? c)
      a
      (if (= (first c) (dec (second c)))
        (recur (next c) (conj a (first c)))
        (recur (next c) [])))))

;;task 134
;;Write a function which, given a key and map, returns true iff the map contains an entry with that key and its value is nil.
;;(true?  (__ :a {:a nil :b 2}))
;;(false? (__ :b {:a nil :b 2}))

(defn task134 [xkey xmap ]
  (= nil (xkey xmap)))
