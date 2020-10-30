FROM gradle:6.3.0-jre14 as build-stage
ADD . /Blog
WORKDIR /Blog
RUN gradle :server:clean :server:shadowJar

FROM openjdk:14-jdk-alpine as exec-stage
COPY --from=build-stage /Blog/server/build/libs/server-all.jar .
ENTRYPOINT ["java", "-jar", "server-all.jar"]
