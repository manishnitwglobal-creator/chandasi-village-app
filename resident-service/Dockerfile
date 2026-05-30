FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN ./gradlew :resident-service:bootJar -x test
WORKDIR /app/resident-service/build/libs
EXPOSE 8082
CMD ["java", "-jar", "resident-service-1.0.0.jar"]
