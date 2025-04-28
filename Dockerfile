# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:2e3824afeb41f61761adee95318814e6669bd59aaf61255b2af47064b8755c02
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
