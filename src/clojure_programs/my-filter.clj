(ns clojure-programs.core
  (:gen-class))

;; creating my own filter
;; the functionality of the filter is to show the result collection when the given function shows true

(defn my-filter1 [fun collection]
  (loop [coll collection
         newcol ()]
    (if (empty? coll)
      newcol
      (recur (rest coll) (if (= true (fun (first coll)))
                           (conj (vec newcol) (first coll))
                           newcol )))))

;;clojure-programs.core> (my-filter1 pos? [1 -2 3 4 -5 6 ])
;;[1 3 4 6]
;;clojure-programs.core> (my-filter1 neg? [1 -2 3 4 -5 6 ])
;;[-2 -5]
;;clojure-programs.core> (my-filter1 pos? [])
;;()

;; Using reduce function we can create a filter function
(defn my-filter2 [function collection]
  (reduce (fn [result element]
            (if (function element)
              (conj result element)
              result
              ))
          []
          collection))

;;clojure-programs.core> (my-filter2 pos? [1 -2 3 4 -5 6 ])
;;[1 3 4 6]
;;clojure-programs.core> (my-filter2 neg? [1 -2 3 4 -5 6 ])
;;[-2 -5]
;;clojure-programs.core> (my-filter2 pos? [])
;;[]

;;=============================================================
;;using bad recursion method
;;infact this function is bad also but i am trying to implement
;; soory its not working

( defn my-filter [f coll]
  (def result [])
  (if-not (= (first coll) nil)
    (if (f (first coll))
      (conj (my-filter f (rest coll)) (first coll)))
    result))
