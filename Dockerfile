FROM gradle:8.1-jdk17-alpine AS build
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/rest-1.jar rest.jar
ENTRYPOINT [ "java","-jar","rest.jar"]