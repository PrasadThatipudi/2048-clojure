(ns utils)

(defn clear-screen []
  (print "\033[2J\033[3J\033[H")
  (flush))

(defn random-in-range [start end]
  (+ start (rand-int (- end start))))

(defn- align-left [text length]
  (str text (apply str (repeat (- length (count text)) " "))))

(defn table [matrix]
  (doseq [row matrix]
    (apply println (map (fn [element] (align-left (str element) 4)) row))))

(defn prompt [message]
  (print message)
  (flush)
  (read-line))

(defn deep-vec [x]
  (cond (not (seq? x)) x
        :else (vec (map deep-vec x))))

(defn not-zero? [number]
  (not (zero? number)))

(defn game-over? [board]
  (every? not-zero? (flatten board)))

(defn put-number-in-board [board [row column] replacement]
  (assoc board row (assoc (nth board row) column replacement)))

(defn find-all-positions [line search-term]
  (loop [index 0 term-positions []]
    (cond (= (count line) index) term-positions
          :else (recur (inc index) (cond (= (nth line index) search-term)
                                         (conj term-positions index)
                                         :else term-positions)))))

(defn list-empty-boxes [board]
  (loop [row-index 0 empty-boxes []]
    (cond (= (count board) row-index) empty-boxes
          :else (recur
                 (inc row-index)
                 (concat empty-boxes
                         (map (fn [pos] [row-index pos])
                              (find-all-positions (nth board row-index) 0)))))))
