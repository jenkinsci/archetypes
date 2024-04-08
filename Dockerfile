# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:db0744d1d8f99bc1050f0fae6041a81fa3981fae21c383ef3d2cbb9b08faf2e6
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
