FROM openjdk:11-ea-jdk-slim

RUN mkdir -p /log
RUN mkdir tokens
RUN mkdir upload

COPY ./build/libs/*.jar javaexec.jar
COPY ./tokens ./tokens

ENTRYPOINT ["java","-jar","javaexec.jar"]