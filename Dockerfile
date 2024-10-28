# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:440a97a9304d5f66cb96e01161724d0ac3a50d1d90c4cb99dffd94fe4282d31f
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
