# syntax=docker/dockerfile:experimental
FROM maven:3.6.1-jdk-8
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN --mount=type=cache,target=/root/.m2,sharing=private mvn -B -ntp -s settings-azure.xml -Darchetype.test.settingsFile=`pwd`/settings-azure.xml clean verify
