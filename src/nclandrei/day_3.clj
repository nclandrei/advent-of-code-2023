(ns day-3
  (:require [util :as u]))

(def data (vec (u/read-data "day-3.txt")))

(defn re-seq-pos [pattern line y]
  (let [m (re-matcher pattern line)]
    ((fn step []
       (when (. m find)
         (cons {:start (. m start) :y y :end (. m end) :group (Integer/parseInt (. m group))}
               (lazy-seq (step))))))))

(defn chars-on-line
  [line start end]
  (if (nil? line) [] (take (inc (- end start)) (drop (dec start) line))))

(defn adjacent-chars
  [y start end]
  (flatten (map #(chars-on-line % start end) [(get data y) (get data (inc y)) (get data (dec y))])))

(defn part-number?
  [y start end]
  (let [adjacent-chars (adjacent-chars y start end)]
    (not (empty? (filter #(and (not (Character/isDigit %)) (not= % \.)) adjacent-chars)))))

(defn day-1-part-1
  []
  (->>
   (map-indexed (fn [idx line] (re-seq-pos #"\d+" line idx)) data)
   (flatten)
   (filter #(part-number? (get % :y) (get % :start) (get % :end)))
   (reduce + 0)))

(re-seq-pos #"\d+" "467..114..")

(day-1-part-1)

(part-number? 0 7 8)

(numbers-on-line "467..114..")







