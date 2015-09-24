(ns clojure-programs.core
  (:require [clojure.string :as cstr]))


;;function for finding the given string is present in multiline string

(defn grep [word multiline_string]
  "it returns the line numbers where the given word is appered in string"
  (def list-of-strings (cstr/split-lines multiline_string))
  (reduce (fn [result string]
            (if (.contains string word)
              (conj result (inc (.indexOf list-of-strings string)))
              result))
          []
          list-of-strings))

;;user>(grep "cin" "cout << \"Enter two numbers....\";\n cin >> number1;\n cin >> number2;")
;; [2 3]



