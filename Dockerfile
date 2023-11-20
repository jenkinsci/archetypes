# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.5@sha256:e0900e5953fddc181fdbe449ba8c27f124470fb545fbbb29f39f50d6093c9b18
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
