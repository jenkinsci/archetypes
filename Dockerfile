# syntax=docker/dockerfile:1.1.1-experimental
FROM maven:3.9.5@sha256:985c886611175c440c476276799a6f39d07fa5ce0c2035cf90d34ef5165c0b9c
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
