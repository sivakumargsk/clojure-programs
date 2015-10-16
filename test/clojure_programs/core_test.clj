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

(facts "about" `index` function
       (fact "it normally returns index of given element to the given collection"
             (index [1 2 3 4 5 6 7 8] 4) => 3
             (index [] 4) => -1
             (index [1 2 3 4 5] 8) => -1))

(facts "about `is-perfect` function"
       (fact "it normally returns boolean if the given number is perfect number or not i.e ex 6 = 3+2+1"
             (is-perfect 6) => true
             (is-perfect 0) => true
             (is-perfect 2) => false
             (is-perfect -6) => false))

(facts "about `maximum-functions` "
       (fact "it normally returns maximum valuer in a seq if seq is empty returns default"
             (max1 [1 58 6 4 354 25 9] :default) => 354
             (max2 [1 2 3 5 9 87 5 6 ] :default) => 87
             (max1 [] :default) => :default
             (max2 [] :default) => :default ))

(facts "about `my-filter-functions`"
       (fact "it normally returns collection element where each satisfies the given function"
             (my-filter1 pos? [3 2 1 0 -1 -2 -3]) => [3 2 1]
             (my-filter1 neg? [3 2 1 0 -1 -2 -3]) => [-1 -2 -3]
             (my-filter2 pos? [3 2 1 0 -1 -2 -3]) => [3 2 1]
             (my-filter2 neg? [3 2 1 0 -1 -2 -3]) => [-1 -2 -3]))

(facts "about `is-palindrome?-function`"
       (fact "it normally returns true if the given string is palindrome or false"
             (is-palindrome? "malayalam") => true
             (is-palindrome? "sivakumar") => false))

(facts "about `is-prime?-and-prime-seq-functions`"
       (fact "is-prime? fun returns true if the given number is true"
             (is-prime? 3) => true
             (is-prime? 0) => false
             (is-prime? 1) => false
             (is-prime? 81) => false
             (is-prime? 133) => false)
       (fact "prime-seq fun returns prime number sequence of count of given number"
             (prime-seq 6) => [2 3 5 7 11 13]
             (prime-seq 0) => []
             (prime-seq -1) => []))

(facts "about `word-freq-function`"
       (fact "returns a map of str-count as key and no.of.strings having count of key-val as values"
             (word-freq "ramu raju and siva plays football" ) => {3 1, 4 3, 5 1, 8 1}
             (word-freq "i am so happy to see you my friend.") => {1 1, 2 4, 3 2, 5 1, 7 1}
             (word-freq "") => {0 1}))

(facts "about `day-givenData`"
       (fact "it normally returns the day of a given Date"
             (day-givenDate 07 07 1992) => "Tuesday"
             (day-givenDate 07 07 2015) => "Tuesday"
             (day-givenDate 01 01 0001) => "Monday"
             (day-givenDate 01 01 20202020) => "Wednesday" ))
