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
                    junit 'skipPublishingChecks: true, target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                sh  "chmod +x -R ${env.WORKSPACE}"
                sh ".//jenkins//scripts//deliver.sh"
            }
        }
    }
}