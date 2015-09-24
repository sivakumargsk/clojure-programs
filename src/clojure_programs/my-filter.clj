(ns clojure-programs.core)


(defn my-filter1 [fun collection]
  (loop [coll collection
         newcol []]
    (if (empty? coll)
      newcol
      (recur (rest coll) (if (= true (fun (first coll)))
                           (conj newcol (first coll))
                           newcol )))))

;;clojure-programs.core> (my-filter1 pos? [1 -2 3 4 -5 6 ])
;;[1 3 4 6]


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
