(ns day-4
  (:require [util :as u]
            [clojure.set :as set]
            [clojure.math :as math]))

(def data (vec (u/read-data "day-4.txt")))

(defn numbers
  [n]
  (->>
   (re-seq #"(\d+)" n)
   (map #(second %))
   (into #{})))

(defn number-pairs
  []
  (map (fn [line]
         (->>
          (re-seq #"Card\s+\d+: (.*) \| (.*)" line)
          (first)
          (rest)
          (map numbers)))
       data))

(defn winners
  []
  (map
   (fn [[winners choices]]
     (set/intersection (into #{} winners) (into #{} choices)))
   (number-pairs)))

(defn increment-scores
  [acc idx count matches]
  (reduce
   (fn [nacc nidx] (do
                     (println "nacc" nacc)
                     (update nacc nidx (partial * count))))
   acc
   (range (inc idx) (dec matches))))

(defn scratchcards
  []
  (let [winners (map count (winners))
        indexed-winners (into {} (map-indexed (fn [idx itm] [idx 1]) winners))]
    (reduce (fn [acc [idx count]]
              (let [matches (nth winners idx)]
                (increment-scores acc idx count matches)))
            indexed-winners
            indexed-winners)))

(increment-scores (into {} (map-indexed (fn [idx itm] [idx 1]) (map count (winners)))) 0 1)

(scratchcardsq)

(defn day-4-part-1
  []
  (->>
   (winners)
   (map count)
   (map #(if (<= % 1) % (math/pow 2 (dec %))))
   (apply +)))

(day-4-part-1)


























