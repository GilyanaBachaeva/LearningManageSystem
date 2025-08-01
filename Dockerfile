# Этап 1: Сборка приложения
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY /src .
COPY gradlew .
COPY gradle/wrapper/ ./gradle/wrapper/
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon
RUN ./gradlew test --no-daemon

# Этап 2: Запуск приложения
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/LearningManageSystem-0.0.1-SNAPSHOT.jar app.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/lms_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres
ENTRYPOINT ["java", "-jar", "app.jar"]