# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.11@sha256:fb525d54d52bbdce5c5768c5a80580f4bbf6b5679214f35dfd039e580b5172bf
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
