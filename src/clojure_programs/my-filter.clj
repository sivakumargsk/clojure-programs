(ns clojure-programs.core
  (:gen-class))

;; creating my own filter
;; the functionality of the filter is to show the result collection when the given function shows true

(defn my-filter1 [function collection]
  (loop [fun function
         coll collection
         newcol ()]
    (if (= coll ())
      newcol
      (recur fun (rest coll) (if (= true (fun (first coll)))
                               (conj (vec newcol) (first coll))
                               newcol )))))



;; Using reduce function we can create a filter function
(defn my-filter2 [function collection]
  (reduce (fn [result element]
            (if (function element)
              (conj result element)
              result
              ))
          []
          collection))




;;using bad recursion method
;;infact this function is bad also but i am trying to implement
;; soory its not working

( defn my-filter [f coll]
  (def result [])
  (if-not (= (first coll) nil)
    (if (f (first coll))
      (conj (my-filter f (rest coll)) (first coll)))
    result))
