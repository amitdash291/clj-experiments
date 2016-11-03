(ns marsrover.rover1
    (:require  []))
(defn init-rover
    [id position direction]
      (hash-map :id id :position position :direction direction))

(defmulti move #(:direction %))
(defmethod move :N
    [rover]
      (update-in rover  [:position :y] inc))

(defmethod move :E
    [rover]
      (update-in rover  [:position :x] inc))

(defmethod move :S
    [rover]
      (update-in rover  [:position :y] dec))

(defmethod move :W
    [rover]
      (update-in rover  [:position :x] dec))

(def left-of  (hash-map :N :W :W :S :S :E :E :N))
(def right-of  (hash-map :N :E :E :S :S :W :W :N))

(defn turn-left
    [rover]
      (assoc rover :direction  ((:direction rover) left-of)))

(defn turn-right
    [rover]
      (assoc rover :direction  ((:direction rover) right-of)))

