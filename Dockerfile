# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:70591cb7a67e12414b16603c6e89d95625e802667f2a0932d5362c459f362fff
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
