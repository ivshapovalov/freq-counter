FROM gradle:8.3.0-jdk17-alpine AS BUILD_STAGE
COPY --chown=gradle:gradle . /home/gradle
RUN gradle clean -x test build || return 1

FROM openjdk:17.0.1-jdk-slim AS RUN_STAGE
ARG JAR_FILE=/opt/app/build/libs/*SNAPSHOT.jar
WORKDIR /opt/app
EXPOSE 8080
ENV TZ="Europe/London"
COPY --from=BUILD_STAGE /home/gradle/build/libs/*SNAPSHOT.jar $JAR_FILE
RUN  cp ${JAR_FILE} /opt/app/freq-counter.jar
ENTRYPOINT ["java", "-jar", "freq-counter.jar"]
