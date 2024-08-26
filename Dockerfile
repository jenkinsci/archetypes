# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:e895a7e85a1acb128d3670fa3ee45f2a9f07836b28364d19a4ab2916f9dd2e14
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
