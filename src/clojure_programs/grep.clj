(ns clojure-programs.core
  (:gen-class))

;;function for finding the given string is present in multiline string
;;it returns the line numbers...
;;we can use two functions

;;(.contains big_string word)===> it returns boolean...

;;user> (.contains "the method" "the")
;;true

;;(re-find #word big_string)=====> but it returns the value word
;;if it is true else nil

;;user>(re-find #"the" "the method the")
;;"the"

;;user>(re-find #"hi" "the method the")
;;nil

;;so i use .contains function for my program......
(defn grep [word multiline_string]
  "it returns the line numbers where the given word is appered in string"
  (def list-of-strings (clojure.string/split-lines multiline_string))
  (reduce (fn [result string]
            (if (.contains string word)
              (conj result (inc (.indexOf list-of-strings string)))
              result))
          []
          list-of-strings))

;;output===
;;user>(grep "cin" "cout << \"Enter two numbers....\";\n cin >> number1;\n cin >> number2;")
;; [2 3]

;;user>(grep "hi!" "hello, who are you")
;;[]




