(ns clojure-programs.core
  (:require [clojure.string :as cstr]))

;;=============================================================================================
(defn factorial [x]
  (loop [n x
         acc 1]
    (if (= n 0)
      acc
      (recur (dec n) (* n acc) ))))

(defn factorial1 [n]
  (reduce * (range 1 (inc n))))

(defn factorial2 [n]
  (if (= n 0) 1
      (* n (factorial2 (dec n)))))

;;===============================================================================================
;;This function returns the number of times the charecter is repeated in the given string.
(defn find-rept-char-str [ch w]
  "This function returns the number of times the charecter is repeated in the given string."
  (loop [word (vec w)
         sum 0]
    (if (empty? word)
      sum
      (recur  (rest word) (if (= ch (first word))
                            (inc sum)
                            sum )))))

;;===============================================================================================
(defn grep [word multiline-string]
  (let [list-of-strings (cstr/split-lines multiline-string)
        line-no-string (zipmap list-of-strings (range 1 (inc (count list-of-strings))))]
    (reduce (fn [result string]
              (if (.contains string word)
                (conj result (line-no-string string))
                result))
            []
            list-of-strings)))


;;======================================================================================================
;;This program logic is based on finding the doomsday of a year. It means the doomsday shows last day of february for any given year.
;;i create a days variable using def because i use this in every function
(def days {"Sunday" 0
           "Monday" 1
           "Tuesday" 2
           "Wednesday" 3
           "Friday" 5
           "Saturday" 6 })

;;i create a anchor-day variable using def because i use this in every function
(def anchor-day {0 "Tuesday"
                 1 "Sunday"
                 2 "Friday"
                 3 "Wednesday"})


;;function for returning the century-anchor-day of a given year
(defn anchor-century-day [year]
  (days (anchor-day (mod (quot year 100) 4))))

;;clojure-programs.core> (century-day 1899)
;;5 <-- 18th century anchor day friday

;;This function returns the last day of February month of a given year.
(defn doomsday [year]
  (let [val1 (quot (mod year 100) 12 )
        val2 (- (mod year 100) (* 12 val1))
        val3 (quot val2 4)
        val4 (anchor-century-day year)]
    (mod (+ val1 val2 val3 val4) 7)))

;;clojure-programs.core>(doomsday 1899)
;;2 --> means Tuesday

;;function for finding the given function is leap year or not
(defn leap? [year]
  (if (and (= 0 (mod year 4))(not= 0 (mod year 100)))
    true
    false))
;;clojure-programs.core> (leap? 1899)
;;false

;;Now function for returning the day of the given date
(defn day-givenDate [day month year]
  (let [ach-mon {1 (if (leap? year) 4 3 )
                 2 (if (leap? year) 29 28 )
                 3 0
                 4 4
                 5 9
                 6 6
                 7 11
                 8 8
                 9 5
                 10 10
                 11 7
                 12 12}
        difference (- (mod day 7) (mod (get ach-mon month) 7))
        map-keys-list (keys days)]
    (nth map-keys-list (mod (+ (doomsday year) difference) 7))))

;;clojure-programs.core> (day-givenDate 7 7 1992)
;;"Tuesday"
