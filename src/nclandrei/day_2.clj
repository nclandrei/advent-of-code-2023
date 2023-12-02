(ns day-2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn read-data [fname]
  (with-open [rdr (io/reader fname)]
    (doall (line-seq rdr))))

(def max-allowed-values [14 12 13])

(defn color-value
  [part color]
  (let [value (re-find (re-pattern (format "(\\d+) %s" color)) part)]
    (if (nil? value) 0 (Integer/parseInt (second value)))))

(defn parse-line
  [line]
  (let [[game-part cubes-part] (string/split line #":")
        game-id (Integer/parseInt (re-find #"\d+" game-part))
        cube-rounds (map string/trim (string/split cubes-part #";"))
        max-blue (apply max (map #(color-value % "blue") cube-rounds))
        max-red (apply max (map #(color-value % "red") cube-rounds))
        max-green (apply max (map #(color-value % "green") cube-rounds))]
    [game-id max-blue max-red max-green]))

(defn day-1-part-1
  []
  (->>
   (read-data "/Users/anicolae/Code/advent-of-code-2023/resources/day-2.txt")
   (map parse-line)
   (filter #(and (and (<= (second %) (first max-allowed-values)) (<= (nth % 2) (second max-allowed-values))) (<= (nth % 3) (nth max-allowed-values 2))))
   (map #(first %))
   (apply +)))

(day-1-part-1)
