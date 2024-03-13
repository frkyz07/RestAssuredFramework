pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit "junit skipPublishingChecks: true, testResults: '**/cpputest_*.xml'"

                }
            }
        }
        stage('Deliver') {
            steps {
                sh  "chmod +x -R ${env.WORKSPACE}"
                sh "./deliver.sh"
            }
        }
    }
}