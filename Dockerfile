# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:a7fc7af5e97f7dc47f36a96fe1a08d7bccb9c5eebdb600522818087e960a3c71
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
