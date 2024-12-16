# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:b89ede2635fb8ebd9ba7a3f7d56140f2bd31337b8b0e9fa586b657ee003307a7
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
