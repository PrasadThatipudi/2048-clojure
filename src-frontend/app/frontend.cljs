(ns app.frontend
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(defn make-col [col-value]
  [:td {:style {:width 100 :height 100 :text-align "center"}} col-value])

(defn make-row [row-values]
  [:tr (map make-col row-values)])

(defn make-board [table-data]
  [:table {:border 1} (map make-row table-data)])

(def initial-board '((0 0 0 0) (0 0 0 0) (0 0 0 0) (0 0 0 0)))

(defn root []
  (make-board initial-board))

(defn ^:export init []
  (rdom/render [root] (.getElementById js/document "app")))
