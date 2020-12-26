FROM yt8492/kotlin-native:1.4.21-gradle6.7.1 as build-stage
ADD . /Blog
WORKDIR /Blog
RUN gradle clean :server:installDist

FROM openjdk:8-jre-alpine as exec-stage
COPY --from=build-stage /Blog/server/build/install/server .

ENTRYPOINT ["./bin/server"]
