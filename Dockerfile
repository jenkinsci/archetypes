# syntax=docker/dockerfile:1.1.1-experimental
FROM maven:3.9.4@sha256:a9b1581fc335b6eb0f56e2c884e870dbfb6519571475a06a941143d407c51bf7
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
