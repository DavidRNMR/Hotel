FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/hotel-0.0.1-SNAPSHOT.jar hotel-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hotel-0.0.1-SNAPSHOT.jar"]