FROM maven:3.8-openjdk-17-slim as build
WORKDIR /sojourn
COPY . .
# RUN mvn clean package -DskipTests
RUN mvn clean install -DskipTests
EXPOSE 1234
CMD ["mvn", "spring-boot:run"]

# FROM eclipse-temurin:17-jre-alpine as run
# WORKDIR /app
# COPY --from=build /sojourn/sojourn/target/sojourn-0.0.1-SNAPSHOT.jar .
# EXPOSE 1234
# CMD ["java", "-jar", "sojourn-0.0.1-SNAPSHOT.jar"]