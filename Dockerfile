# syntax=docker/dockerfile:1.1.7-experimental
FROM maven:3.9.6@sha256:14cb66655eaecdc41c46039c009a082b21784abde83b4139aefd06ddd904caf4
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp clean verify
