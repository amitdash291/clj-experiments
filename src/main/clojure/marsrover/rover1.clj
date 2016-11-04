(ns marsrover.rover1)

(defn create-rover
  [x-pos y-pos direction]
  (hash-map :position {:x x-pos, :y y-pos} :direction direction)
)

(def movements  (hash-map
                  :W #(update-in %  [:position :x] inc)
                  :E #(update-in %  [:position :x] dec)
                  :N #(update-in %  [:position :y] inc)
                  :S #(update-in %  [:position :y] dec)))

(def left-of  (hash-map :N :W :W :S :S :E :E :N))

(def right-of  (hash-map :N :E :E :S :S :W :W :N))

(def turns (hash-map :L left-of :R right-of))

(defn turn
  [rover turn-type]
  (assoc rover :direction
    ((:direction rover) (turn-type turns))
  )
)

(defn turn-left
  [rover] 
  (turn rover :L)
)

(defn turn-right
  [rover] 
  (turn rover :R)
)

(defn move
  [rover]
  (((:direction rover) movements) rover)
)

(def rover (create-rover 0 0 :N))

;; The following move sequence should result in the rover at (1,1) position and facing north
(def move-sequence1 (-> rover turn-left move turn-right move))

;; The following move sequence should result in the rover at (0,1) position and facing east
(def move-sequence2 (-> rover turn-left move turn-right move turn-right move))

