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

(defn fill-right [vector replacement length]
  (concat vector (repeat (- length (count vector)) replacement)))

(defn move-line-left [numbers]
  (fill-right (add-pairs (consecutive-pairs (remove-zeros numbers))) 0
              (count numbers)))

(defn move-left [table-numbers]
  (map move-line-left table-numbers))
