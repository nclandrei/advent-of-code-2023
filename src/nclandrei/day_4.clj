(ns day-4
  (:require [util :as u]
            [clojure.set :as set]
            [clojure.math :as math]))

(def data (vec (u/read-data "day-4.txt")))

(defn number-pairs
  []
  (map (fn [line]
         (->>
          (re-seq #"Card\s+\d+: (.*) \| (.*)" line)
          (first)
          (rest)))
       data))

(defn numbers
  [n]
  (->>
   (re-seq #"(\d+)" n)
   (map #(second %))
   (into #{})))

(defn day-4-part-1
  []
  (->>
   (map (fn [pair] (map numbers pair)) (number-pairs))
   (map (fn [[winners choices]] (set/intersection (into #{} winners) (into #{} choices))))
   (map count)
   (map #(if (<= % 1) % (math/pow 2 (dec %))))
   (apply +)))













