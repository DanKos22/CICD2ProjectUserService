FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/UserService-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "UserService-0.0.1-SNAPSHOT.jar"]