cirmanager
==========

- mvn clean package
- java -jar target/cirmanager-0.0.1-SNAPSHOT.jar db migrate cirmanager.yml
- java -cp target/cirmanager-0.0.1-SNAPSHOT.jar org.h2.tools.Shell -url jdbc:h2:db/cirmanager -user sa -password sa -sql "insert into ciids(name) values('ciid://foo/bar/baz');"
- java -Dserver.http.port=8080 -Dserver.http.adminPort=8081 -jar target/cirmanager-0.0.1-SNAPSHOT.jar server cirmanager.yml
- java -jar target/cirmanager-0.0.1-SNAPSHOT.jar server cirmanager.yml
