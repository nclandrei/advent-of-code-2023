(ns day-3
  (:require [util :as u]))

(defn parse-line
  [line]
  (partition-by #(not (Character/isDigit %)) line))

(defn numbers-on-line
  [line]
  (let [indexed-line (map-indexed (fn [idx itm] [idx itm]) line)]
    (reduce (fn [acc [idx ch]]
              (cond
                (and (Character/isDigit %) (nil? (acc :start-index))) (assoc acc :current-number (str ch) :start-index idx) ; beginning of a number
                (and (Character/isDigit %) (some? (acc :start-index))) (assoc acc :current-number (str (acc :current-number) ch)) ; we're STILL inside a number
                (and (not (Character/isDigit %)) (some? (acc :current-number))) (assoc acc :numbers () :current-number (str (acc :current-number) ch)) ; we just finished a number and we had one before
                )){:numbers [] :start-index nil :current-number nil} indexed-line) :numbers))

({:a 123} :a)

(parse-line "467..114..")

;; (/1 /2 /3)(..)(/1 /1 /4)($.)

(def numbers
  [data]
  (for [line data
        row-index (range (count data))]
    (let [parsed-line (parse-line line)])))

(numbers (u/read-data "day-3.txt"))















