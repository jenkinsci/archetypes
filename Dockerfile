# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:a56564e1101ee96bf451f847e74c67b55c104638f20e12aa72965e85626256b5
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
