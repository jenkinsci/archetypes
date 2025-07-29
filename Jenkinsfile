pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }
    agent {
        label 'maven-21'
    }
    stages {
        stage('main') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    sh 'mvn -B -ntp clean verify'
                }
            }
        }
    }
}
