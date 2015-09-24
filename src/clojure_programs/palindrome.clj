(ns clojure-programs.core
  (:require [clojure.string :as cstr]))

(defn is-palindrome [word]
  "This function returns a true if the given string is palindrome or false if not"
  (= word (cstr/reverse word)))
