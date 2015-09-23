(ns clojure-programs.core
  (:gen-class))

;;function for returning the index of the given element in a list
;; using tail recursion



(defn index [coll ele]
  "function for returning the index of the given element in a list."
  (loop [c coll
         acc 0]
    (if (empty? c)
      -1
      (if (= ele (first c))
        acc
        (recur (rest c) (inc acc))))))


;;clojure-programs.core> (index [1 2 3 4 5 6] 5)
;;4
;;clojure-programs.core> (index [1 2 3 4 5 6] 9)
;;-1
;;clojure-programs.core> (index [] 9)
;;-1
