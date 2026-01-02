(ns app.frontend
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(defn root []
  [:h1 "Hello world"])

(defn ^:export init []
  (rdom/render [root] (.getElementById js/document "app")))
