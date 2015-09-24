(ns clojure-programs.core)

(defn divisors-sum [n]
  (reduce (fn [x y]
            (if (= 0 (mod n y))
              (+ x y)
              (+ x 0)))
          (range 1 (inc (/ n 2)))))

(defn is-perfect [n]
  (if (= n (divisors-sum n))
    true
    false))
