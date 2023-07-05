FROM openjdk:11-ea-jdk-slim

COPY ./build/libs/*.jar javaexec.jar
RUN mkdir tokens
COPY ./tokens ./tokens
ENTRYPOINT ["java","-jar","javaexec.jar"]