# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:2a68a63e21d00a054351ac20d8c0b22310c362025b059b8728d4f6cd3141aeca
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
