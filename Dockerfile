FROM openjdk:8-jre-alpine
COPY /target/loofah-graph-api.jar .
CMD /usr/bin/java -Dspring.data.mongodb.uri=mongodb://loofah-database:27017/test -jar ./loofah-graph-api.jar