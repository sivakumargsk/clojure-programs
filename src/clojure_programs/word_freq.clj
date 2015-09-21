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

;;clojure-programs.core> (word-freq "i am so happy to see you my friend.")
;;{1 1, 2 4, 3 2, 5 1, 7 1}
