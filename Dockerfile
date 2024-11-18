# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:5a44dff9390bff393693518d70233977ff245c2876320655a47be5622719becf
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
