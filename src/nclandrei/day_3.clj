(ns day-3
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def resource-path "/Users/anicolae/Code/advent-of-code-2023/resources/day-3.txt")
(def data (read-data resource-path))
(def columns (count (first data)))
(def rows (count data))

(defn read-data [fname]
  (with-open [rdr (io/reader fname)]
    (doall (line-seq rdr))))

(defn parse-line-part
  [part]
  (let [first-element (first part)
        part-length (count part)]
    (if
     (Character/isDigit first-element) (Integer/parseInt (apply str part))
     (map #(if (= \. %) :non-symbol :symbol) part))))

(defn parse-line
  [line]
  (->>
   (partition-by #(not (Character/isDigit %)) line)
   (map parse-line-part)
   (flatten)))

(defn adjacent-coordinates
  [n row column]
  (let [digits-count (count (str n))
        min-left (max 0 (- column digits-count))
        max-right (min columns (+ column digits-count))
        min-top (max 0 (dec row))
        max-bottom (min rows (inc row))]
    ()))

(defn day-1-part-1
  []
  (->>
   (read-data "/Users/anicolae/Code/advent-of-code-2023/resources/day-3.txt")
   (map parse-line)))

(day-1-part-1)

(parse-line ".664.598*.")















