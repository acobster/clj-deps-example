# CLJ Tools/Deps example

A simple dummy app that uses `deps.edn` to manage dependencies and can be run with `clj` alone.

This is just a learning tool to help me figure out how to set up a workable Clojure dev environment using only barebones tools rather than fancy stuff like Leiningen.

All this app does is run a dummy HTTP server that says `hello`. It's the tooling that matters.

The dev environment can be started with:

```
clj -A:dev -m app
```

This starts up an HTTP server at localhost:8080. You can use the `HTTP_PORT` env var to override the port number. It also starts an nREPL server on port 7000.

The `env/dev/user.clj` file contains code for restarting the HTTP server from your editor. Once you are connected to the REPL you can evaluate forms in the `comment` at the bottom:

```clojure
(comment
  (mount/stop #'http-server)
  ;; => {:stopped ["#'app/http-server"]}

  (mount/start #'http-server)
  ;; => {:started ["#'app/http-server"]}

  (restart)
  ;; => {:started ["#'app/http-server"]}
)
```