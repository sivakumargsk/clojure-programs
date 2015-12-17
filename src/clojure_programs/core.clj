(ns clojure-programs.core
  (:require [clojure.string :as cstr]))

;;=============================================================================================
(defn factorial [x]
  (loop [n x
         acc 1]
    (if (= n 0)
      acc
      (recur (dec n) (* n acc) ))))

(defn factorial1 [n]
  (reduce * (range 1 (inc n))))

(defn factorial2 [n]
  (if (= n 0) 1
      (* n (factorial2 (dec n)))))


;;===============================================================================================
(defn find-rept-char-str [ch w]
  "This function returns the number of times the charecter is repeated in the given string."
  (loop [word (vec w)
         sum 0]
    (if (empty? word)
      sum
      (recur  (rest word) (if (= ch (first word))
                            (inc sum)
                            sum )))))


;;===============================================================================================
(defn grep [word multiline-string]
  (let [list-of-strings (cstr/split-lines multiline-string)
        line-no-string (zipmap list-of-strings (range 1 (inc (count list-of-strings))))]
    (reduce (fn [result string]
              (if (.contains string word)
                (conj result (line-no-string string))
                result))
            []
            list-of-strings)))


;;==============================================================================================
(defn index [coll ele]
  "function for returning the index of the given element in a list."
  (loop [c coll
         acc 0]
    (if (empty? c) -1
        (if (= ele (first c)) acc
            (recur (rest c) (inc acc))))))


;;==============================================================================================
(defn is-perfect [n]
  (= n (reduce (fn [result element]
                 (if (= 0 (mod n element))
                   (+ result element)
                   result))
               0
               (range 1 (inc (/ n 2))))))


;;==============================================================================================
(defn max-two [x y]
  (if (> x y) x y))


(defn max1 [coll default]
  (if (empty? coll)
    default
    (reduce max-two coll)))

(defn max2 [coll default]
  (if (empty? coll)
    default
    (loop [c coll
           acc 0]
      (if (empty? c) acc
          (recur (rest c) (max-two acc (first c)))))))


;;================================================================================================
(defn my-filter1 [fun collection]
  (loop [coll collection
         newcol []]
    (if (empty? coll)
      newcol
      (recur (rest coll) (if (= true (fun (first coll)))
                           (conj newcol (first coll))
                           newcol )))))

(defn my-filter2 [function collection]
  (reduce (fn [result element]
            (if (function element)
              (conj result element)
              result
              ))
          []
          collection))


;;================================================================================================
(defn is-palindrome?[word]
  "This function returns a true if the given string is palindrome or false if not"
  (= word (cstr/reverse word)))


;;================================================================================================
(defn is-prime? [num]
  (if (< num 2)
    false
    (empty? (filter (fn [x]
                      (if (= 0 (mod num x)) true false )) (range 2 num)))))


(defn prime-seq [n]
  "This function returns the given size of prime numbers"
  (take n (filter is-prime? (iterate inc 0))))


;;================================================================================================
(defn word-freq [string]
  (let [list-string (cstr/split string #" ")]
    (if (empty? list-string)
      nil
      (frequencies (sort (reduce (fn [x y]
                                   (conj x (count y)))
                                 []
                                 list-string))))))


;;================================================================================================
;;This program logic is based on finding the doomsday of a year. It means the doomsday shows last day of february for any given year.

;;i create a days variable using def because i use this in every function
(def days {"Sunday" 0
           "Monday" 1
           "Tuesday" 2
           "Wednesday" 3
           "Thursday" 4
           "Friday" 5
           "Saturday" 6 })

;;i create a anchor-day variable using def because i use this in every function
(def anchor-day {0 "Tuesday"
                 1 "Sunday"
                 2 "Friday"
                 3 "Wednesday"})


;;function for returning the century-anchor-day of a given year
(defn anchor-century-day [year]
  (days (anchor-day (mod (quot year 100) 4))))

;;clojure-programs.core> (century-day 1899)
;;5 <-- 18th century anchor day friday

;;This function returns the last day of February month of a given year.
(defn doomsday [year]
  (let [val1 (quot (mod year 100) 12 )
        val2 (- (mod year 100) (* 12 val1))
        val3 (quot val2 4)
        val4 (anchor-century-day year)]
    (mod (+ val1 val2 val3 val4) 7)))

;;clojure-programs.core>(doomsday 1899)
;;2 --> means Tuesday

;;function for finding the given function is leap year or not
(defn leap? [year]
  (if (and (= 0 (mod year 4))(not= 0 (mod year 100)))
    true
    false))

;;clojure-programs.core> (leap? 1899)
;;false

;;Now function for returning the day of the given date
(defn day-givenDate [day month year]
  (let [ach-mon {1 (if (leap? year) 4 3 )
                 2 (if (leap? year) 29 28 )
                 3 0
                 4 4
                 5 9
                 6 6
                 7 11
                 8 8
                 9 5
                 10 10
                 11 7
                 12 12}
        difference (- (mod day 7) (mod (get ach-mon month) 7))
        map-keys-list (keys days)]
    (nth map-keys-list (mod (+ (doomsday year) difference) 7))))

;;clojure-programs.core> (day-givenDate 7 7 1992)
;;"Tuesday"

;;=================================================================================
;; map function using recurssion

(defn mymap [fun coll]
  (if (empty? coll)
    (list)
    (cons (fun (first coll)) (mymap fun (rest coll)))))

(defn myfilter [fun coll]
  (if (empty? coll)
    (list)
    (if (fun (first coll))
      (cons (first coll) (myfilter fun (rest coll)))
      (myfilter fun (rest coll)))))


(defn myevery? [pred coll]
  (if (empty? coll)
    true
    (if (pred (first coll))
      (myevery? pred (rest coll))
      false)))

(defn myany? [pred coll]
  (if (empty? coll)
    false
    (if (pred (first coll))
      true
      (myany? pred (rest coll)))))

(defn mypartition [fun coll]
  (list (filter fun coll) (remove fun coll)))

(defn mypartition1 [fun coll]
  (list (myfilter fun coll) (myfilter (complement fun) coll)))

(defn mygroup1 [coll]
  (let [cseq (seq coll)
        f    (first cseq)
        c    (mypartition #(= f %) cseq)
        cf   (first c)
        cs   (second c)]
    (if (empty? cs)
      (conj `() cf)
      (conj (mygroup1 cs) cf))))

;;clojure-programs.core>  (mygroup1  [1 1 "siva" 1 2 2 2 "sai" 3 3 3 4 4 4 ])
;;((1 1 1) ("siva") (2 2 2) ("sai") (3 3 3) (4 4 4))

;;clojure-programs.core>  (mygroup1 [1 1 1 2 1  2 2 3 3 1 3 4 4 2 4 ])
;;((1 1 1 1 1) (2 2 2 2) (3 3 3) (4 4 4))


(defn mygroup [fun coll]
  (let [cseq (seq coll)
        f    (first cseq)
        c    (mypartition #(= (fun f) (fun %)) cseq)
        cf   (first c)
        cs   (second c)]
    (if (empty? cs)
      (conj `() cf)
      (conj (mygroup fun cs) cf))))

;;clojure-programs.core> (mygroup type [1 1 "siva" 1 2 2 2 "sai" 3 3 3 4 4 4 ])
;;((1 1 1 2 2 2 3 3 3 4 4 4) ("siva" "sai"))

;;clojure-programs.core> (mygroup identity [1 1 1 2 2 2 3 3 3 4 4 4 ])
;;((1 1 1) (2 2 2) (3 3 3) (4 4 4))

(defn mycompress [coll]
  (let [c (mygroup identity coll)]
    (loop [xc c
           ans []]
      (if (empty? xc)
        ans
        (let [f (first xc)]
          (recur (rest xc) (conj ans (list (first f) (count f)))))))))

(defn mycompress1 [coll]
  (map #(list (first %) (count %))) (mygroup1 coll) )

;;clojure-programs.core> (mycompress [1 2 1 2  4 3 6 25 1  4 5])
;;[(1 3) (2 2) (4 2) (3 1) (6 1) (25 1) (5 1)]

(defn expand-join1 [acc coll]
  (reduce (fn [x y] (conj x y)) acc (repeat (second coll) (first coll))))

(defn uncompress [coll]
  (loop [c coll
         ans []]
    (if (empty? c)
      ans
      (recur (rest c) (expand-join1 ans (first c))))))

(defn uncompress1 [coll]
  (map #(repeat (second %) (first %)) coll))

;;clojure-programs.core> (uncompress [[1 3] [2 3] [4 5]])
;;[1 1 1 2 2 2 4 4 4 4 4]

(defn duplicate [coll]
  (if (empty? coll)
    (list)
    (cons (first coll) (cons (first coll) (duplicate (rest coll)) ) )))

(defn duplicate1 [coll]
  (mapcat #(list % %) coll))

;;clojure-programs.core> (duplicate [1 2 3 4])
;;(1 1 2 2 3 3 4 4)

(defn kth-element [k coll]
  (if (= k 1)
    (first coll)
    (kth-element (dec k) (rest coll))))

;;clojure-programs.core> (kth-element 2 [1 2 3 4 5 6])
;;3

(defn drop-kth-element [k coll]
  (if (empty? coll)
    (list)
    (if (= k 1)
      (drop-kth-element (dec k) (rest coll))
      (cons (first coll) (drop-kth-element (dec k) (rest coll))))))

;;clojure-programs.core> (drop-kth-element 2 [1 2 3 4 5 6])
;;(1 3 4 5 6)

(defn dropevery-kth-element [k coll]
  (loop [n k
         c coll
         ans []]
    (if (empty? c)
      ans
      (if (= n 1)
        (recur k (rest c) ans)
        (recur (dec k) (rest c) (conj ans (first c)))))))

;;clojure-programs.core> (dropevery-kth-element 2 [1 2 3 4 5 6])
;;[1 3 5]

(defn firstpart [k coll]
  (if (< k 1)
    (list)
    (cons (first coll) (firstpart (dec k) (rest coll)))))

(defn secondpart [k coll]
  (if (= k 1)
    (rest coll)
    (secondpart (dec k) (rest coll))))

(defn mysplit-at [k coll]
  (list (firstpart k coll) (secondpart k coll)))

(defn mysplit-at1 [k coll]
  `((take k coll) (drop k coll)))


;;clojure-programs.core> (mysplit-at 2 [1 2 3 4 5 6 7 8])
;;((1 2) (3 4 5 6 7 8))

(defn last-element [coll]
  (let [c (seq coll)]
    (if (empty? (rest c))
      (first c)
      (last-element (rest c)))))

;;clojure-programs.core> (last-element [1 2 5 6 9 2 1 4 ])
;;4

(defn penultimate [coll]
  (let [c (seq coll)]
    (if (= nil (next(next c)))
      (first c)
      (penultimate (rest c)))))

;;clojure-programs.core> (penultimate [5 64 2 8 9 4 ])
;;9

(defn myreverse [coll]
  (let [c (seq coll)]
    (if c
      (conj (myreverse (rest c)) (first c))
      [])))

;;clojure-programs.core> (myreverse [5 64 2 8 9 4 ])
;;[4 9 8 2 64 5]

(defn mycompare [ x y]
  (< x y))

(defn compare-list [ coll1 coll2]
  (< (count coll1) (count coll2)))

(defn sort-list-list [ coll]
  (sort compare-list coll))

;;clojure-programs.core> (sort-list-list [[1 2] [5] [9 4 3] [2] [2 56 8 95 3]])
;;([5] [2] [1 2] [9 4 3] [2 56 8 95 3])


(defn myfrequency [word multi_string]
  (let [s (cstr/split multi_string #" ")]
    (count (filter #(= word %) s))))

;;clojure-programs.core> (myfrequency "name" "hi friends my name siva \nmy name is kumar \nmy name is raj \n and finally i am lokesh")
;;=>3


;;This function is for how many times the given word is repeated in given multi_line string
;;with respect to line numbers.

(defn idx-to-element [staridx coll]
  "this function returns a list of list containing idxNo and element where elements are greater than Zero"
  (let [s (seq coll)]
    (if s
      (if (= (first s) 0)
        (idx-to-element (inc staridx) (rest coll))
        (cons (list staridx (first coll)) (idx-to-element (inc staridx) (rest coll))))
      (list))))

(defn myfrequency1 [word multi_string]
  (let [s (cstr/split-lines multi_string)
        lc (map #(myfrequency word %) s)]
    (idx-to-element 1 lc)))

;;clojure-programs.core>(myfrequency1 "name" "hi friends my name siva \nmy name is kumar \nmy name is raj \n and finally i am lokesh")
;;((1 1) (2 1) (3 1))
