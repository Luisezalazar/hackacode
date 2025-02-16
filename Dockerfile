FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/Hackacode1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_hackacode.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_hackacode.jar"]