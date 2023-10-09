# syntax=docker/dockerfile:1.1.1-experimental
FROM maven:3.9.4@sha256:57250c40de1eabd9e74e0a2ed90636a74d205e47a1aa1c55aef848f125905223
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
