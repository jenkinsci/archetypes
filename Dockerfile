# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:873dec32acf4fbb756a71c8aedeb0a8c8a77579e41728f0c4cda747a339a7d2d
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
