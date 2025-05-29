FROM gradle:8.5.0-jdk17 as build-stage
ADD . /Blog
WORKDIR /Blog
ARG IS_PRODUCTION
RUN gradle --no-daemon :server:installShadowDist

FROM openjdk:17 as exec-stage
WORKDIR /Blog
COPY --from=build-stage /Blog/server/build/install/server-shadow .

ENTRYPOINT ["./bin/server"]
