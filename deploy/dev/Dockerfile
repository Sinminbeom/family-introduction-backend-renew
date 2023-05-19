FROM openjdk:11-ea-jdk-slim

COPY ./build/libs/*.jar javaexec.jar

ENTRYPOINT ["java","-jar","javaexec.jar"]