(ns day-1
  (:require [clojure.java.io :as io]))

(def letter-digits
  {"one" 1
   "two" 2
   "three" 3
   "four" 4
   "five" 5
   "six" 6
   "seven" 7
   "eight" 8
   "nine" 9})

(def pattern #"(?i)(\d|one|two|three|four|five|six|seven|eight|nine)")

(defn read-data [fname]
  (with-open [rdr (io/reader fname)]
    (doall (line-seq rdr))))

(defn digit
  [d]
  (if (contains? letter-digits d) (letter-digits d) d))

(defn number-1
  [line]
  (let [dd (filter #(Character/isDigit %) line)]
    (Integer/parseInt (str (first dd) (last dd)))))

(defn number-2
  [line]
  (let [matches (re-seq pattern line)
        first-match (last (first matches))
        last-match (last (last matches))]
    (Integer/parseInt (str (digit first-match) (digit last-match)))))

(defn day-1-part-1
  []
  (->>
   (read-data "/Users/anicolae/Code/advent-of-code-2023/resources/day-1.txt")
   (map number-1)
   (apply +)))

(defn day-1-part-2
  []
  (->>
   (read-data "/Users/anicolae/Code/advent-of-code-2023/resources/day-1.txt")
   (map number-2)
   (apply +)))

(day-1-part-2)

















