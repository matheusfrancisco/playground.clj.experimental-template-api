# Template API using clojure

This is my API REST tempalte to clojure, using components and `databases in memory`, `postgres` or `datomic`, you choose your database.
Exist an basic test setup to integration test with components to use database in memory, if you need test with other database
you must change the config-map test


# HOW TO RUN
```
lein run
```

```
http://localhost:8080/
```


# TODO
- [ ] create command to run only datomic
- [ ] create command to run only postgresql
- [ ] create command to run only in memory datomic
- [ ] move routes in service clj to your own service for each database

# Contribute
