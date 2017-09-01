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
                sh 'mvn -B clean install archetype:integration-test'
            }
            post {
                failure {
                    catchError { // JENKINS-42478: in case the failure occurred prior to getting the node
                        archiveArtifacts artifacts: '**/target/test-classes/**/*', allowEmptyArchive: true
                    }
                }
            }
        }
    }
}
