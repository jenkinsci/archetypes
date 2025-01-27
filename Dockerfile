# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:8472bdbac39626b70d2efcf87e23468ff0bea9ba48c35181b07e6932b5fda001
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
