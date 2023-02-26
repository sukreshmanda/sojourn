FROM maven:3.8-openjdk-17-slim as build
WORKDIR /sojourn
COPY . .
RUN ls
WORKDIR /sojourn/sojourn
RUN ls
RUN mvn clean package
RUN ls ./target

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /sojourn/sojourn/target/sojourn-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "sojourn-0.0.1-SNAPSHOT.jar"]