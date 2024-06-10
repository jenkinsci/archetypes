# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.7@sha256:c4f38c877ff3b68e10d7424afe09882b2ff5f97b939f17e5dcfd98773a0b8608
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
