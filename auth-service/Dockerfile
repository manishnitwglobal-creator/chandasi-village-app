FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace

# Pehle gradle files copy karo
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .

# Sab services copy karo
COPY auth-service/ auth-service/
COPY eureka-server/ eureka-server/
COPY resident-service/ resident-service/
COPY search-service/ search-service/
COPY api-gateway/ api-gateway/
COPY src/ src/

# Build karo
RUN chmod +x ./gradlew
RUN ./gradlew :auth-service:bootJar -x test --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/auth-service/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Xmx256m","-jar","app.jar"]
