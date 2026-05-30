FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .
COPY auth-service/ auth-service/
COPY eureka-server/ eureka-server/
COPY resident-service/ resident-service/
COPY search-service/ search-service/
COPY api-gateway/ api-gateway/
COPY src/ src/
RUN chmod +x ./gradlew
RUN ./gradlew :auth-service:bootJar -x test --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/auth-service/build/libs/*.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java", \
  "-Xmx256m", \
  "-Dserver.port=10000", \
  "-Dspring.datasource.url=jdbc:postgresql://dpg-d8d6npernols73916hs0-a:5432/chandasi_auth", \
  "-Dspring.datasource.username=chandasi_user", \
  "-Dspring.datasource.password=BXFMYVhtgWpgp5VWnyyGldmFZVxp0Sa6", \
  "-Dspring.datasource.driver-class-name=org.postgresql.Driver", \
  "-Dspring.jpa.hibernate.ddl-auto=update", \
  "-Deureka.client.enabled=false", \
  "-jar", "app.jar"]
