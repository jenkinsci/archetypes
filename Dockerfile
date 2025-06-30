# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.10@sha256:d9f3089fdb012b592ea2d2d287dd40cbe4f15894cb5c5ec63c7f774bc8cb9168
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
