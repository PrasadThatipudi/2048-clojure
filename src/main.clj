(ns main
  "Main entry point for the 2048 game application."
  (:require [game-2048 :as game]
            [utils]
            [org.httpkit.server :as server]
            [ring.middleware.file :refer [wrap-file]]
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

(def initial-board [[0 0 0 0] [0 0 0 0] [0 0 0 0] [0 0 0 0]])


(defn index-page []
  (html
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:title "App"]]
    [:body
     [:div {:id "app"}]
     [:script {:src "/js/main.js"}]]]))

(defn handler [req]
  (let [uri (:uri req)
        method (:request-method req)]
    (case [method uri]
      [:get "/"] {:status 200
                    :headers {"Content-Type" "text/html; charset=utf-8"}
                    :body (index-page)}
      {:status 404
       :headers {"Content-Type" "text/plain; charset=utf-8"}
       :body "Not found"})))

(def app
  (-> handler
      (wrap-file "public")))

(defn -main []
  (server/run-server app {:port 8000})
  (println "Server running at http://localhost:8000"))
