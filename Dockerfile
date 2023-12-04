# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.5@sha256:2bbd188fef2384cf93181bb992b75542a2837afdd5c4c2274cebcae71cd05156
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
