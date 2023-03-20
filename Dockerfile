FROM openjdk:16-jdk-alpine

COPY target/SpringBootProject-0.0.1-SNAPSHOT.jar SpringBootProject-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/SpringBootProject-0.0.1-SNAPSHOT.jar"]