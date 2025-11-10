pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }
    agent {
        label 'maven-25'
    }
    stages {
        stage('Build') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    script {
                        infra.runMaven(["clean", "verify"], 25)
                    }
                }
            }
        }
    }
}
