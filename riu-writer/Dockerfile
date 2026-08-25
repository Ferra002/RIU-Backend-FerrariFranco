FROM maven:3.9-eclipse-temurin-26 AS build

WORKDIR /app
COPY . .
RUN mvn clean verify

FROM eclipse-temurin:26-jre

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 3501

ENTRYPOINT ["java", "-jar", "app.jar"]