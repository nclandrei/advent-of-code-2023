(ns day-2
  (:require [clojure.java.io :as io]))

(defn read-data [fname]
  (with-open [rdr (io/reader fname)]
    (doall (line-seq rdr))))
