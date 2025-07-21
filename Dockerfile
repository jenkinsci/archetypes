# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.10@sha256:193ed19a4806b4bbad65b2e8a2a9bbf901b6e216eb0d5761c70c2c4537cd53d7
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
