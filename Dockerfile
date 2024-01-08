FROM gradle:7.6.0-jdk11 as build-stage
ADD . /Blog
WORKDIR /Blog
ARG IS_PRODUCTION
RUN gradle --no-daemon "-Dorg.gradle.jvmargs=-Xmx4g -XX:MaxRAMPercentage=75.0" :server:installShadowDist

FROM openjdk:11 as exec-stage
COPY --from=build-stage /Blog/server/build/install/server-shadow .

ENTRYPOINT ["./bin/server"]
