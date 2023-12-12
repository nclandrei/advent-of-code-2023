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
  [x y]
  (->>
   (apply concat [(if (= y 0) [] (partition 2 (interleave [(dec x) x (inc x)] (repeat 3 (dec y)))))
                  [[(dec x) y] [(inc x) y]]
                  (if (= y (dec (count data))) [] (partition 2 (interleave [(dec x) x (inc x)] (repeat 3 (inc y)))))])
   (filter (fn [[x y]] (and (and (>= x 0) (< x (count (first data)))) (and (>= y 0) (< y (count data))))))))

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

(defn gear-numbers
  [item numbers]
  (let [{star-y-pos :y star-x-pos :start} item
        adjacent-star-positions (adjacent-star-positions star-x-pos star-y-pos)
        y-positions (map (fn [[x y]] y) adjacent-star-positions)
        x-positions (map (fn [[x y]] x) adjacent-star-positions)]
    (assoc item :numbers (filter
                          (fn [{:keys [start end y group]}]
                            (and
                             (some? (some #{y} y-positions))
                             (some? (some #(some #{%} x-positions) (range start end)))))
                          numbers))))

(defn day-3-part-2
  []
  (let [numbers (filter some? (flatten (map-indexed (fn [idx line] (re-seq-pos #"\d+" line idx)) data)))]
    (->>
     (map-indexed (fn [idx line] (re-seq-pos #"\*" line idx)) data)
     (flatten)
     (filter some?)
     (map #(gear-numbers % numbers))
     (filter #(= 2 (count (% :numbers))))
     (map (fn [{numbers :numbers}]
            (map #(Integer/parseInt (% :group)) numbers)))
     (map #(apply * %))
     (apply +))))

(day-3-part-2)

