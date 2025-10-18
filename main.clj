(ns main
  "Main entry point for the 2048 game application."
  (:require [game-2048 :as game] [utils]))

(defn select-random-empty-position [board]
  (let [empty-positions (utils/list-empty-boxes board)]
    (nth  empty-positions (utils/random-in-range 0 (count empty-positions)))))

(defn- update-board-with [board new-number]
  (utils/put-number-in-board (utils/deep-vec board)
                             (select-random-empty-position board) new-number))

(defn read-player-move [board]
  (utils/clear-screen)
  (utils/print-board board)
  (println "\n  w")
  (println "a s d\n")
  [board (utils/prompt "Enter your move: ")])

(defn execute-player-move [board player-option]
  (case player-option
    "w" (game/move-up board)
    "a" (game/move-left board)
    "s" (game/move-down board)
    "d" (game/move-right board)))

(def initial-board [[0 0 0 0] [0 0 0 0] [0 0 0 0] [0 0 0 0]])

(defn -main []
  (loop [board initial-board]
    (cond (utils/game-over? board) (println "Game Over!")
          :else (recur
                 (apply execute-player-move
                        (read-player-move (update-board-with board 2)))))))

(-main)
