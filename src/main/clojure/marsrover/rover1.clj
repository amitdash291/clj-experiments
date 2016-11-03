(ns marsrover.rover1)
(defn init-rover
  [id position direction]
  (hash-map :id id :position position :direction direction)
  )

(def movements  (hash-map
                  :W #(update-in %  [:position :x] inc)
                  :E #(update-in %  [:position :x] dec)
                  :N #(update-in %  [:position :y] inc)
                  :S #(update-in %  [:position :y] dec)))
(def left-of  (hash-map :N :W :W :S :S :E :E :N))
(def right-of  (hash-map :N :E :E :S :S :W :W :N))

(defn turn-left
  [rover]
  (assoc rover :direction  ((:direction rover) left-of)))

(defn turn-right
  [rover]
  (assoc rover :direction  ((:direction rover) right-of)))

(defn move
  [rover]
  (((:direction rover) movements) rover)
  )

