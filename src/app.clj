(ns app
  (:require
   [org.httpkit.server :as http]
   [mount.core :as mount :refer [defstate]]
   [my-app.env]))


(defn app [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "hello"})


(defonce stop-http (atom nil))

(defn start! []
  (let [port (or (Integer. (System/getenv "HTTP_PORT")) 8080)]
    (println (str "Running HTTP server at localhost:" port))
    (reset! stop-http (http/run-server app {:port port})))
  nil)

(defn stop! []
  (println "Stopping HTTP server")
  (when (fn? @stop-http)
    (@stop-http))
  (reset! stop-http nil))

(defstate http-server
  :start (start!)
  :stop  (stop!))


(defn -main [& args]
  (mount/start))