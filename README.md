cirmanager
==========

- mvn clean package

- java -jar target/cirmanager-0.0.1-SNAPSHOT.jar db migrate cirmanager.yml

- java -cp target/cirmanager-0.0.1-SNAPSHOT.jar org.h2.tools.Shell -url jdbc:h2:db/cirmanager -user sa -password sa -sql "insert into ciids(name) values('ciid://foo/bar/baz');"
- java -cp target/cirmanager-0.0.1-SNAPSHOT.jar org.h2.tools.Shell -url jdbc:h2:db/cirmanager -user sa -password sa -sql "insert into cirs(cirid,ciid) values(711111111,1);"

- start_server [java -jar -Ddw.server.connector.port=7777 target/cirmanager-0.0.1-SNAPSHOT.jar server cirmanager.yml]
