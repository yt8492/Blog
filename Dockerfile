FROM yt8492/kotlin-native:1.7.20-gradle7.6.0 as build-stage
ADD . /Blog
WORKDIR /Blog
ARG IS_PRODUCTION
RUN gradle --no-daemon "-Dorg.gradle.jvmargs=-Xmx4g -XX:MaxRAMPercentage=75.0" :server:installShadowDist

FROM openjdk:8-jre-slim as exec-stage
COPY --from=build-stage /Blog/server/build/install/server-shadow .

ENTRYPOINT ["./bin/server"]
