FROM openjdk:11-ea-jdk-slim

ARG version=$(cat ./build.gradle | grep "version = '.*'" -o | cut -d "'" -f 2)

COPY ./build/libs/family-introduction-backend-renew-${version}.jar javaexec.jar

ENTRYPOINT ["java","-jar","javaexec.jar"]