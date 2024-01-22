# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:072a8c74232cdddae27bfaaa3c0a12649fdbe6e96fdad39918ce222b4a69d475
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
