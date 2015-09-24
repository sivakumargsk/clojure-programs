(ns clojure-programs.core)

;;This function returns the number of times the charecter is repeated in the given string.
(defn find-rept-char-str [ch w]
  "This function returns the number of times the charecter is repeated in the given string."
  (loop [word (vec w)
         sum 0]
    (if (empty? word)
      sum
      (recur  (rest word) (if (= ch (first word))
                            (inc sum)
                            sum )))))
