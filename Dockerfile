# 1-ci mərhələ: Build (Java 21 dəstəkləyən Maven istifadə edirik)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2-ci mərhələ: Run (Java 21 JRE)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]