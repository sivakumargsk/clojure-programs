(ns clojure-programs.core
  (:gen-class))

;;This program logic is based on finding the doomsday of a year. It means the doomsday shows last day of february for any given year.

;;i create a days variable using def because i use this in every function
(def days {"Sunday" 0
           "Monday" 1
           "Tuesday" 2
           "Wednesday" 3
           "Thursday" 4
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

;;clojure-programs.core> (century-day 1992)
;;3 <-- 19th century anchor day wednesday

;;clojure-programs.core> (century-day 2015)
;;2 <-- 20th century anchor day is tuesday



;;This function returns the last day of February month of a given year.
(defn doomsday [year]
  (let [val1 (quot (mod year 100) 12 )
        val2 (- (mod year 100) (* 12 val1))
        val3 (quot val2 4)
        val4 (anchor-century-day year)]
    (mod (+ val1 val2 val3 val4) 7)))

;;clojure-programs.core>(doomsday 1899)
;;2 --> means Tuesday
;;clojure-programs.core> (doomsday 1992)
;;6
;;clojure-programs.core> (doomsday 2012)
;;3

;;function for finding the given function is leap year or not
(defn leap? [year]
  (if (and (= 0 (mod year 4))(not= 0 (mod year 100)))
    true
    false))

;;clojure-programs.core> (leap? 1992)
;;true
;;clojure-programs.core> (leap? 1111)
;;false
;;clojure-programs.core> (leap? 2015)
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
;;clojure-programs.core> (day-givenDate 1 1 1)
;;"Monday"
;;clojure-programs.core> (day-givenDate 1 1 20156)
;;"Thursday"
;;clojure-programs.core> (day-givenDate 23 9 2015)
;;"Wednesday"


;;=======================================================================
;;testing the code
;;=======================================================================

(defn doomsday1 [year]
  (let [val1 (quot (mod year 100) 12 )
        val2 (- (mod year 100) (* 12 val1))
        val3 (quot val2 4)
        val4 (anchor-century-day year)
        map-keys-list (keys days)]
    (nth map-keys-list (mod (+ val1 val2 val3 val4) 7))))

;;clojure-programs.core> (doomsday1 1992)
;;"Saturday"
;;clojure-programs.core> (doomsday1 2012)
;;"Wednesday"
;;clojure-programs.core> (doomsday1 1899)
;;"Tuesday"


;;now creating the months based on doomsday year
(defn months-doomsday [year]
  {1 (if (leap? year) 4 3 )
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
   12 12})

;;here i giving the leap year so 1st and 2nd values are changed
;;clojure-programs.core> (months-doomsday 1992)
;;{7 11, 1 4, 4 4, 6 6, 3 0, 12 12, 2 29, 11 7, 9 5, 5 9, 10 10, 8 8}

;;here i dont give leap year so again 1st and 2nd values are changed
;;clojure-programs.core> (months-doomsday 2015)
;;{7 11, 1 3, 4 4, 6 6, 3 0, 12 12, 2 28, 11 7, 9 5, 5 9, 10 10, 8 8}


;;============================================================================================
;;Trying to write a single function
;;============================================================================================
