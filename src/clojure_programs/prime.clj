(ns clojure-programs.core)

(defn is-prime? [num]
  (if (< num 2)
    false
    (empty? (filter (fn [x]
                      (if (= 0 (mod num x)) true false )) (range 2 num)))))


(defn prime-seq [n]
  "This function returns the given size of prime numbers"
  (take n (filter is-prime? (iterate inc 0))))
