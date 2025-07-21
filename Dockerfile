FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY /src .
COPY gradlew .
COPY gradle/wrapper/ ./gradle/wrapper/
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/lms_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres
RUN ./gradlew test --no-daemon
COPY build/libs/LearningManageSystem-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]