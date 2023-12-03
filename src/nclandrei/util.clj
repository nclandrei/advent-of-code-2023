(ns util
  (:require [clojure.java.io :as io]))

(defn read-data [fname]
  (with-open [rdr (io/reader (format "/Users/anicolae/Code/advent-of-code-2023/resources/%s" fname))]
    (doall (line-seq rdr))))
