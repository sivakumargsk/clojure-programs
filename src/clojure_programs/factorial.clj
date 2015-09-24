(ns clojure-programs.core)


(defn factorial [x]
  (loop [n x
         acc 1]
    (if (= n 0)
      acc
      (recur (dec n) (* n acc) ))))


(defn factorial1 [n]
  (reduce * (range 1 (inc n))))



(defn factorial2 [n]
  (if (= n 0)
    1
    (* n (factorial2 (dec n)))))
