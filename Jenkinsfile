pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }
    agent {
        label 'maven-21'
    }
    stages {
        stage('Build') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    infra.runMaven(["clean", "verify"], 21)
                }
            }
        }
    }
}
