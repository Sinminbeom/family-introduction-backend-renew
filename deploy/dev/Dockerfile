FROM openjdk:11-ea-jdk-slim

COPY ./build/libs/*.jar javaexec.jar
COPY ./tokens .
ENTRYPOINT ["java","-jar","javaexec.jar"]