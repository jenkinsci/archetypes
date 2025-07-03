# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.10@sha256:615bd38fa00dd2416d90cbbc99895b9955400fa8110e83646e3e04ebcb7c471e
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
