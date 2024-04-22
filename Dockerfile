# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:0cd8671629d6becf5f40ac6e26822bffa9b3ed07bd3c038b88aa17e90dde5f7b
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
