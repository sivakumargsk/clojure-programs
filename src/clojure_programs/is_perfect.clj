(ns clojure-programs.core)

(defn is-perfect [n]
  (reduce (fn [result element]
            (if (= 0 (mod result element))
              (+ result element)
              result))
          0
          (range 1 (inc (/ n 2)))))
