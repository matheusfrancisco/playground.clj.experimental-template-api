(defproject template-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [cheshire "5.8.1"]
                 [com.stuartsierra/component "0.4.0"]
                 [dawcs/flow "1.0.0"]
                 [io.pedestal/pedestal.jetty "0.5.5"]
                 [hikari-cp "2.13.0"]
                 [prismatic/schema "1.1.12"]
                 [clj-http "3.10.1"]
                 [org.postgresql/postgresql "42.2.10"]
                 [io.pedestal/pedestal.service "0.5.5"]
                 [io.pedestal/pedestal.service-tools "0.5.5"]
                 [seancorfield/next.jdbc "1.1.569"]
                 [prismatic/schema "1.1.10"]]
  :main ^:skip-aot template-clj.core
  :resource-paths ["configs"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :uberjar-name "api.jar"
  :test-selectors {:default (complement :integration)
                   :integration :integration}
  :repl-options {:init-ns template-clj.core})
