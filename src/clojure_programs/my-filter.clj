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
                               (conj newcol (first coll))
                               newcol )))))


;;using bad recursion method

(defn my-filter [f coll]
  (if (first coll)
    (cons (when (= true (f (first coll))) (first coll))
          (my-filter1 f (rest coll)))
    (list)))
