FROM openjdk:17-jdk-slim-buster

COPY target/ .

EXPOSE 8082
EXPOSE 5000

CMD ["java", "-jar", "supakiassignment-0.0.1-SNAPSHOT.jar"]