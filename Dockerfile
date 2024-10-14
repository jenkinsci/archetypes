# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:86c6a51c530cbe62ed345126861e37f088f9aa6b9b2557d298a579fbf1b1e957
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
