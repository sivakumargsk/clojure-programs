(ns clojure-programs.core
  (:gen-class)
  (:require [clojure.string :as str]))


;;function for finding the word frequencies of a given string
;;using freqencies, sort and reduce functions

(defn word-freq [string]
  (let [list-string (clojure.string/split string #" ")]
    (if (empty? list-string)
      nil
      (frequencies(sort (reduce (fn [x y]
                                  (conj x (count y)))
                                []
                                list-string))))))

