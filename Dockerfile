FROM gradle:7.6-jdk19-alpine AS build
COPY --chown=gradle:gradle . /api_onboarding
WORKDIR /api_onboarding
RUN gradle bootJar --no-daemon --stacktrace
FROM openjdk:19-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY app_params.properties app_params.properties
COPY --from=build /api_onboarding/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]