# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:d98e229685654e7c38a2f121bdf3766505c7fe6841284e45d46ed0411cd480a1
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
