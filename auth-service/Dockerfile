FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x gradlew && ./gradlew :auth-service:bootJar -x test
EXPOSE 8081
CMD ["java", "-jar", "auth-service/build/libs/auth-service-1.0.0.jar"]
