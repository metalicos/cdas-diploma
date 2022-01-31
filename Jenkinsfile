pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps{
                withMaven {
                      sh "mvn clean verify"
                }
            }
        }
        stage('Test'){
            steps {
                echo "Test stage works fine!"
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploy stage works fine!"
            }
        }
    }
}