(ns day-1
  (:require [clojure.java.io :as io]))

(def pattern #"(?i)(?=([1-9]|one|two|three|four|five|six|seven|eight|nine))")

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

(defn read-data [fname]
  (with-open [rdr (io/reader fname)]
    (doall (line-seq rdr))))

(defn digit
  [d]
  (if (contains? letter-digits d) (str (letter-digits d)) (str d)))

(re-seq pattern "ninezjtxp7bpzdgtoneeightoneighth")

(defn number-1
  [line]
  (let [dd (filter #(Character/isDigit %) line)]
    (Integer/parseInt (str (first dd) (last dd)))))

(defn number-2
  [line]
  (let [matches (re-seq pattern line)
        first-match (last (first matches))
        last-match (last (last matches))]
    (Integer/parseInt (clojure.string/join "" [(digit first-match) (digit last-match)]))))

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
   (filter #(not (nil? %)))
   (reduce +)))

(day-1-part-1)
(day-1-part-2)



