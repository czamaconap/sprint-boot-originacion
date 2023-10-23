FROM openjdk:19-jdk-alpine

WORKDIR /api-originacion
# COPY . /api-originacion
# RUN chmod +x gradlew
# RUN ./gradlew build
COPY app_params.properties app_params.properties
COPY build/libs/sprint-boot-originacion-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081

CMD ["java", "-jar", "app.jar"]