# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:9ae8f001f2d978fdfe36052cc9950eda99929d410e2b1f03bd35c09f49890fc9
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
