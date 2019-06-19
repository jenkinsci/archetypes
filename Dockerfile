FROM maven:3.6.1-jdk-8
COPY . /src
WORKDIR /src
RUN rm -rf .git
RUN mvn -B -ntp -s settings-azure.xml -Darchetype.test.settingsFile=`pwd`/settings-azure.xml clean verify
