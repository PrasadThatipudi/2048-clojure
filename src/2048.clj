;; (defn move-right [& numbers]
;;   (add-pairs (pair (remove-zeros numbers))))

(defn not-zero? [number]
  (not (zero? number)))

(defn remove-zeros [numbers]
  (filter not-zero?  numbers))

(defn make-pair [pairs, current-value]
  (def last-pair (last pairs))
  (cond (< (count last-pair) 2) (assoc pairs
                                       (dec (count pairs))
                                       (conj last-pair current-value))
        :else (assoc pairs
                     (count pairs)
                     (conj [current-value]))))

(defn make-pairs [numbers]
  (reduce make-pair [[]] numbers))

(defn add-vector [vector] (apply + vector))

(defn add-pairs [pairs]
  (map add-vector  pairs))

(defn move-line-left [& numbers]
  (add-pairs (make-pairs (remove-zeros numbers))))

