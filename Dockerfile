# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:f1e4a85afafe57aef902d6c0557ecefa06c3c84100d1ca2a17944d3d39252751
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
