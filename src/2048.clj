(defn not-zero? [number]
  (not (zero? number)))

(defn remove-zeros [numbers]
  (filter not-zero?  numbers))

(defn make-consecutive-pair [pairs current-value]
  (let [last-pair (last pairs)]
    (cond (and
           (= (count last-pair) 1)
           (= current-value (first last-pair)))
          (assoc pairs
                 (dec (count pairs))
                 (conj last-pair current-value))

          :else (assoc pairs
                       (count pairs)
                       (conj [current-value])))))

(defn consecutive-pairs [numbers]
  (reduce make-consecutive-pair [] numbers))

(defn add-vector [vector] (apply + vector))

(defn add-pairs [pairs]
  (map add-vector  pairs))

(defn fill-zero-right [length vector]
  (concat vector (repeat (- length (count vector)) 0)))

(defn move-line-left [line]
  (fill-zero-right (count line)
                   (add-pairs (consecutive-pairs (remove-zeros line)))))

(defn move-left [lines]
  (map move-line-left lines))

(defn move-right [lines]
  (map reverse (move-left (map reverse lines))))

(defn transpose [matrix]
  (loop [transposedMatrix [] index 0]
    (cond (= (count (first matrix)) index) transposedMatrix
          :else (recur
                 (concat transposedMatrix
                         [(map (fn [row] (get row index)) matrix)])
                 (inc index)))))

