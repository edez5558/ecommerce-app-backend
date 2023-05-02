FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY /compiler/rest-1.jar rest.jar
ENTRYPOINT [ "java","-jar","rest.jar"]