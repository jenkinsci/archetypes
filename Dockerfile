# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:fd5186a92c0432cbc9bd948c00838e7ade0b540765cc0c84e95b472f122cbcd3
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
