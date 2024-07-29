# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.8@sha256:8446f646d94f73aa2cbd7e5c50b45342d2c7d39e30e3a42fefe0469a6ef27a52
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
