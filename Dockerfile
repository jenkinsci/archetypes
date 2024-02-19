# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:ef6c85125449082775e012b72b74c61aade088dc27dfb0be8cc12b85438b8b90
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
