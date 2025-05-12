# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:933900d8738eab72ddebb7ad971fc9bca91ae6bc4c7b6d6bbc17fb3609f5e64b
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
