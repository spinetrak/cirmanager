cirmanager
==========

- psql -d cirmanagerdb -h localhost -U cirmanager  -f ./db/seed_db_tables.sql
- mvn clean package
- start_server [java -jar -Ddw.server.connector.port=7777 target/cirmanager-0.0.1-SNAPSHOT.jar server cirmanager.yml]
