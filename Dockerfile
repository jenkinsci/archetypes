# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.7@sha256:3b5f7c15b1a16d3fdf09e6883cde602e4a5406cf5bdf6b251b8ac5c510219311
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
