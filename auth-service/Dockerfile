FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN ./gradlew :auth-service:bootJar -x test
WORKDIR /app/auth-service/build/libs
EXPOSE 8081
CMD ["java", "-jar", "auth-service-1.0.0.jar"]
