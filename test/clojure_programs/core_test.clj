(ns clojure-programs.core-test
  (:require
   ;;[clojure.test :as t]
   [clojure.test :refer :all]
   [clojure-programs.test-helper :rafer :all];;inoder to minimize the output
   ;;[clojure-programs.core :refer :all]
   ))

;; doing jay fields thoughts blog artical for Clojure.test Introduction

;;testing a functions with help os " clojure.test.is " function

(deftest add-1-to-1
  (is (= 2 (+ 1 1))))

(defn add [x y]
  (+ x y))

(deftest add-x-to-y
  (is (= 8 (add 4 4))))

(deftest add-x-to-y-a-few-times
  (is (= 8 (add 8 0)))
  (is (= 8 (add 4 4)))
  (is (= 8 (add 3 5) (add 10 -2) (add 17 -9))))

;;testing a functions with help os " clojure.test.are " function

(deftest add-x-to-y-using-are
  (are [x y] (= 8 (add x y))
    8 0
    4 4
    3 5
    10 -2
    17 -9))

(deftest grab-map-value-using-are
  (are [y z] (= y (:x z))
    2 {:x 2}
    9 {:x 9}
    2 {:y 10 :x 2}
    8 {:z 8 :y 8 :x 8}))

;;Now lets create a grab-map-value function
(defn grab-map-value [mykey my-map]
  (mykey my-map))

(deftest grab-map-value-using-are-function
  (are [mykey my-map] (= 2 (grab-map-value mykey my-map))
    :x {:x 2}
    :x {:x 2}
    :y {:y 2 :x 10}
    :y {:z 10 :y 2 :x 8}))

(deftest grab-map-value-using-are-function
  (are [mykey my-map value] (= value (grab-map-value mykey my-map))
    :x {:x 2} 2
    :x {:x 2} 2
    :x {:y 2 :x 10} 10
    :z {:z 10 :y 2 :x 8} 10))


(run-tests)
;;(run-all-tests)
