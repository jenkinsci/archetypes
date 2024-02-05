# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:03611a70b807602ffce5a8afe31bcd8991bf244f00e23212724ef240554c9d5e
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
