(ns utils)
(require '[clojure.string :as str])

(defn clear-screen []
  (print "\033[2J\033[3J\033[H")
  (flush))

(defn random-in-range [start end]
  (+ start (rand-int (- end start))))

(defn- line [column-length support]
  (str/join (repeat column-length support)))

(defn- enclose [wrapper str]
  (str/join (concat wrapper str wrapper)))

(defn- boarder [column-length column-count corner support mid]
  (enclose corner
           (str/join mid (repeat column-count (line column-length support)))))

(defn- empty-boarder [column-length column-count]
  (boarder column-length column-count "|" " " "|"))

(defn- line-boarder [column-length column-count]
  (boarder column-length column-count "+" "-" "+"))

(defn- align-centre [string column-length]
  (let [text (str string) space-count (- column-length (count text))]
    (str/join (concat
               (line (/ space-count 2) " ") text
               (line (Math/round (float (/ space-count 2))) " ")))))

(defn- make-row [row column-length]
  (enclose "|"
           (str/join "|"
                     (map (fn [element] (align-centre element column-length)) row))))

(defn- make-board [board col-length]
  (let [col-count (count (first board))
        boarder (line-boarder col-length col-count)]
    (cons boarder (flatten
                   (map (fn [row]
                          [(empty-boarder col-length col-count)
                           (make-row row col-length)
                           (empty-boarder col-length col-count) boarder])
                        board)))))

(defn print-board [board]
  (println (str/join "\n" (make-board board 6))))

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
