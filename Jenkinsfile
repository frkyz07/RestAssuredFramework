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
                    junit 'target/surefire-reports/*.xml'
                    junit skipPublishingChecks: true, testResults: 'test-results.xml'

                }
            }
            /*        stage('Deliver') {
            steps {
                sh  "chmod +x -R ${env.WORKSPACE}"
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }*/
}