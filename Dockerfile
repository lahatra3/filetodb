FROM maven:3-eclipse-temurin-17-alpine as app_builder

WORKDIR /app

COPY src/ .
COPY pom.xml .

RUN mvn clean install


FROM eclipse-temurin:17-alpine

ENV APP_NAME=filetodb
ENV BUILD_FOLDER=/app/target/appassembler
ENV START_SCRIPT=${BUILD_FOLDER}/bin/${APP_NAME}.sh

WORKDIR /app

COPY --from=app_builder ${BUILD_FOLDER}/bin ./bin
COPY --from=app_builder ${BUILD_FOLDER}/libs ./libs
COPY --from=app_builder ${BUILD_FOLDER}/resources ./resources

ENTRYPOINT [ "sh", "-c", "${START_SCRIPT}" ]
