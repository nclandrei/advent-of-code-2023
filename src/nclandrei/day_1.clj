(ns day-1
  (:require [clojure.java.io :as io])
  )

(def digits [\0 \1 \2 \3 \4 \5 \6 \7 \8 \9])

(defn read-data [fname]
  (with-open [rdr (io/reader fname)]
    (doall (line-seq rdr))))

(defn number
  [line]
  (let [dd (filter #(Character/isDigit %) line)]
    (Integer/parseInt (str (first dd) (last dd)))))

(defn day-1-part-1
  []
  (->>
   (read-data "/Users/anicolae/Code/advent-of-code-2023/resources/day-1.txt")
   (map number)
   (apply +)))

(day-1-part-1)
