(ns clojure-programs.core
  (:gen-class))

(defn find-rept-char-str [ch w]
  "This function returns the number of times the charecter is repeated in the given string."
  (loop [character ch
         word w
         sum 0]
    (if (= word "")
      sum
      (recur character (apply str (rest word))
             (if (= character (first word))
               (inc sum)
               sum )))))
