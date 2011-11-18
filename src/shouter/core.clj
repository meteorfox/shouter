(ns shouter.core
  (:use [compojure.core :only [defroutes]])
  (:require [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [shouter.controllers.shouts]
            [shouter.views.layout :as layout]))

(defroutes routes
  shouter.controllers.shouts/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (ring/run-jetty (var application) {:port port :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (start port)))
