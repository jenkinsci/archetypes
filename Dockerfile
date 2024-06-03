# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.7@sha256:7bc6b603a7f9a646e7a31990374fb21119a8a5eb2e713771ee8338b56fbccdcd
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
