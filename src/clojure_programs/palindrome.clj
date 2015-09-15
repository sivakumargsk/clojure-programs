(ns clojure-programs.core
  (:gen-class))

;; function to find out if a string is a palindrome–that is,
;; if it looks the same forwards and backwards.
(defn is-palindrome [word]
  "This function returns a true if the given string is palindrome or false if not"
  (= word (apply str (reverse word))))
