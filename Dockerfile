# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:3ed3ba1760c6c03a0f4b0e7fd7f16b3fefc02443e446a974e1fae62fd3b297e1
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
