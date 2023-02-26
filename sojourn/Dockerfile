FROM maven:3.8-openjdk-17-slim as build
WORKDIR /sojourn
COPY . .
WORKDIR /sojourn/sojourn
RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine as run
WORKDIR /app
COPY --from=build /sojourn/sojourn/target/sojourn-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "sojourn-0.0.1-SNAPSHOT.jar"]