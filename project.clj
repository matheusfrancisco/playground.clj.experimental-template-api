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
                 [com.datomic/datomic-pro "0.9.5561"]
                 [clj-http "3.10.1"]
                 [org.postgresql/postgresql "42.2.10"]
                 [io.pedestal/pedestal.service "0.5.5"]
                 [environ "1.1.0"]
                 [io.pedestal/pedestal.service-tools "0.5.5"]
                 [datomic-schema "1.3.0"]
                 [seancorfield/next.jdbc "1.1.569"]
                 [prismatic/schema "1.1.10"]]
  :main ^:skip-aot template-clj.core
  :resource-paths ["configs"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev  {:repl-options         {:timeout 320000}
                    :env          {:db-connection-uri "datomic:mem://template-dev"
                                   :http-server-port 3000}
                    :source-paths ["src/template_clj/"]}
             :test  {:env    {:db-connection-uri "datomic:mem://template-test"
                             :http-server-port 3333}}}
  :uberjar-name "api.jar"
  :test-selectors {:default (complement :integration)
                   :integration :integration}
  :repl-options {:init-ns template-clj.core}
  :repositories [["my.datomic.com" {:url      "https://my.datomic.com/repo"
                                    :username [:env/my_datomic_username]
                                    :password [:env/my_datomic_password]}]])
