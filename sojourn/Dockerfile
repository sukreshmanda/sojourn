FROM maven:3.8-openjdk-18-slim as build
WORKDIR /sojourn
COPY . .
RUN cd ./sojourn
RUN mvn clean package

FROM openjdk:11.0.10-jre-slim
WORKDIR /app
COPY --from=build ./target/sojourn-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
CMD ["java", "-jar", "sojourn-0.0.1-SNAPSHOT.jar"]