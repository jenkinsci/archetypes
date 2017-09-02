pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '20'))
        timeout(time: 1, unit: 'HOURS')
    }
    agent {
        docker {
            image 'maven:3.5.0-jdk-8'
            label 'docker'
        }
    }
    stages {
        stage('main') {
            steps {
                sh 'mvn -B -s settings-azure.xml -Darchetype.test.settingsFile=`pwd`/settings-azure.xml -P pipeline-unit clean verify'
            }
        }
    }
}
