# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.9@sha256:887820a8941cc4e1bf011ec758e50acd8073bfe6046e8d9489e29ed38914e795
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
