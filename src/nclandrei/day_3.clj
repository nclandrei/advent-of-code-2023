(ns day-3
  (:require [util :as u]))

(def data (vec (u/read-data "day-3.txt")))

(defn re-seq-pos [pattern line y]
  (let [m (re-matcher pattern line)]
    ((fn step []
       (when (. m find)
         (cons {:start (. m start) :y y :end (. m end) :group (. m group)}
               (lazy-seq (step))))))))

(defn chars-on-line
  [line start end]
  (drop (dec start) (take (inc end) line)))

(defn line
  [y]
  (or (get data y) []))

(defn positions
  [y start]
  (if (nil? (get data y)) [] ()))

(defn adjacent-chars
  [y start end]
  (map #(chars-on-line % start end)
       [(line y)
        (line (inc y))
        (line (dec y))]))

(defn adjacent-star-positions
  [y start]
  [])

(defn part-number?
  [y start end]
  (let [adjacent-chars (flatten (adjacent-chars y start end))]
    (not (empty? (filter #(and (not (Character/isDigit %)) (not= % \.)) adjacent-chars)))))

(defn day-3-part-1
  []
  (->>
   (map-indexed (fn [idx line] (re-seq-pos #"\d+" line idx)) data)
   (flatten)
   (filter #(and (some? %) (part-number? (% :y) (% :start) (% :end))))
   (map #(Integer/parseInt (% :group)))
   (apply +)))

(defn day-3-part-2
  []
  (let [numbers (map-indexed (fn [idx line] (re-seq-pos #"\d+" line idx)) data)]
    (->>
     (map-indexed (fn [idx line] (re-seq-pos #"\*" line idx)) data)
     (flatten))))

(day-3-part-1)

(day-3-part-2)


