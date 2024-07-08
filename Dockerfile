# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.8@sha256:7a3388321715e15d86f6b540deadeb5cf3e19ac939eda6b94e5e44c681782e9d
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
