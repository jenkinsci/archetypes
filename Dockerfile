# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:a330654d1807c449d65b69371d37a27632eaa1cbd86088c94f1881a0cc862871
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
