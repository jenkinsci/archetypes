# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.8@sha256:a850d95693d43c1322b70f904d5c7b83b94618b76d4d7fdb2bd2185a3b98daf8
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
