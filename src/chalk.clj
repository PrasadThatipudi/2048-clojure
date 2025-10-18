(ns chalk)

(defn- apply-color-code [text color-code]
  (str "\u001B[" color-code "m" text "\u001B[0m"))

(defn bg-blue [text]
  (apply-color-code text "44;30"))

(defn bg-black [text]
  (apply-color-code text "40;37"))

(defn bg-red [text]
  (apply-color-code text "41;37"))

(defn bg-green [text]
  (apply-color-code text "42;37"))

(defn bg-yellow [text]
  (apply-color-code text "43;37"))

(defn bg-magenta [text]
  (apply-color-code text "45;37"))

(defn bg-cyan [text]
  (apply-color-code text "46;37"))

(defn bg-white [text]
  (apply-color-code text "47;30"))

(defn bg-bright-black [text]
  (apply-color-code text "100;37"))

(defn bg-bright-red [text]
  (apply-color-code text "101;37"))

(defn bg-bright-green [text]
  (apply-color-code text "102;37"))

(defn bg-bright-yellow [text]
  (apply-color-code text "103;37"))

(defn bg-bright-magenta [text]
  (apply-color-code text "104;37"))

(defn bg-bright-cyan [text]
  (apply-color-code text "105;37"))

(defn bg-bright-white [text]
  (apply-color-code text "106;30"))

(defn blue [text]
  (apply-color-code text "34"))

(defn red [text]
  (apply-color-code text "31"))

(defn green [text]
  (apply-color-code text "32"))

(defn yellow [text]
  (apply-color-code text "33"))

(defn magenta [text]
  (apply-color-code text "35"))

(defn cyan [text]
  (apply-color-code text "36"))

(defn white [text]
  (apply-color-code text "37"))

(defn gray [text]
  (apply-color-code text "90"))

(defn bright-red [text]
  (apply-color-code text "91"))

(defn bright-green [text]
  (apply-color-code text "92"))

(defn bright-yellow [text]
  (apply-color-code text "93"))

(defn bright-blue [text]
  (apply-color-code text "94"))

(defn bright-magenta [text]
  (apply-color-code text "95"))

(defn bright-cyan [text]
  (apply-color-code text "96"))

(defn bright-white [text]
  (apply-color-code text "97"))
