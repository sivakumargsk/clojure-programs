(ns clojure-programs.core
  (:require [clojure.string :as cstr]))


;;function for finding the given string is present in multiline string

(defn grep [word multiline-string]
  (let [list-of-strings (cstr/split-lines multiline-string)
        line-no-string (zipmap list-of-strings (range 1 (inc (count list-of-strings))))]
    (reduce (fn [result string]
              (if (.contains string word)
                (conj result (line-no-string string))
                result))
            []
            list-of-strings)))

;;user>(grep "cin" "cout << \"Enter two numbers....\";\n cin >> number1;\n cin >> number2;")
;; [2 3]


