FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Instalar netcat para healthcheck
RUN apk add --no-cache netcat-openbsd

# Copiar JAR da build
COPY --from=build /build/target/*.jar app.jar

# Copiar entrypoint script
COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["./entrypoint.sh"]
