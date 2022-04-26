FROM yt8492/kotlin-native:1.5.31-gradle7.0.2 as build-stage
ADD . /Blog
WORKDIR /Blog
RUN gradle --no-daemon "-Dorg.gradle.jvmargs=-Xmx4g -XX:MaxRAMPercentage=75.0" :server:installShadowDist

FROM openjdk:8-jre-slim as exec-stage
COPY --from=build-stage /Blog/server/build/install/server-shadow .

ENTRYPOINT ["./bin/server"]
