(ns main
  "Main entry point for the 2048 game application."
  (:require [game-2048 :as game]
            [utils]
            [org.httpkit.server :as server]
            [hiccup.core :refer [html]]))

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


(defn make-column [element]
  [:td {:style {:width 100 :height 100 :text-align "center"}} element])

(defn make-row [row]
  [:tr (map make-column row)])

(defn make-board [board]
  [:table {:border 1}
   (map make-row board)])

(defn make-move []
  (println "clicked!"))

(defn render-board [board]
  (html
   [:html
    [:body (make-board board)]]))

(def initial-board [[0 0 0 0] [0 0 0 0] [0 0 0 0] [0 0 0 0]])

(defn render-game [_req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (render-board initial-board)})

(defn -main []
  (server/run-server render-game {:port 8000})
  (println "Server running at http://localhost:8000"))
