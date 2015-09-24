(ns clojure-programs.core)

(defn max-two [x y]
  (if (> x y) x y))


(defn maximum [coll]
  (if (empty? coll)
    nil
    (reduce max-two coll)))

(defn max-all [coll]
  (if (empty? coll)
    nil
    (loop [c coll
           acc 0]
      (if (empty? c)
        acc
        (recur (rest c) (max-two acc (first c)))))))

;;clojure-programs.core> (max-all [1 2 3 4 5 6 7 8 9])
;;9
