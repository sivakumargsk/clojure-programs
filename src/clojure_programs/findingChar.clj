(ns clojure-programs.core
  (:gen-class))

;;This function returns the number of times the charecter is repeated in the given string.
(defn find-rept-char-str [ch w]
  "This function returns the number of times the charecter is repeated in the given string."
  (loop [word w
         sum 0]
    (if (empty? word)
      sum
      (recur  (apply str (rest word))(if (= ch (first word))
                                       (inc sum)
                                       sum )))))

;;clojure-programs.core> (find-rept-char-str \a "siva kumar")
;;2
;;clojure-programs.core> (find-rept-char-str \a "potter")
;;0
