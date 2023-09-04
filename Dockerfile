# syntax=docker/dockerfile:1.1.1-experimental
FROM maven:3.9.4@sha256:64de69ce79d9af45a39f5167e8fc6256af59184f62f0ab46e15187585ef37614
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
