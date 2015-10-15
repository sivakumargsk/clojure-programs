(ns clojure-programs.core-test
  (:require
   [midje.sweet :refer :all]
   [clojure-programs.core :refer :all]))


(facts "about `factorial-functions`"
       (fact "it normally returns the factorial of given number"
             (factorial 6) => 720
             (factorial 0) => 1
             (factorial1 6) => 720
             (factorial1 0) => 1
             (factorial2 6) => 720
             (factorial2 0) => 1 ))

(facts "about `find-rept-char-str-Function`"
       (fact "it normally returns the no.of.times the charecter is repeated in a string"
             (find-rept-char-str \a "siva kumar") => 2
             (find-rept-char-str \a "ramana") => 3
             (find-rept-char-str \a "") => 0))


(facts "about `grep-function`"
       (fact "it returns the vector of line-number for given string is present in the multiline String"
             (grep "plays" "ramu plays cricket\n raju plays football\n siva is ready to play") => [1 2]
             (grep "plays" "ramu watches cricket\n raju plays football\n siva is ready to play") => [2]
             (grep "plays" "") => []
             (grep "plays" "ramu plays plays cricket\n raju plays football" )=> [1 2]
             (grep "" "ramu plays plays cricket\n raju plays football" )=> [1 2]))


(facts "about `day-givenData`"
       (fact "it normally returns the day of a given Date"
             (day-givenDate 07 07 1992) => "Tuesday"
             (day-givenDate 07 07 2015) => "Tuesday"
             (day-givenDate 01 01 0001) => "Monday"
             (day-givenDate 01 01 20202020) => "Wednesday" ))
