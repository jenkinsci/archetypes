# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:9e63e6c1ab3535c4afe34fd86ec5716719b43c3ba5bdccb03b9afad579110bde
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
