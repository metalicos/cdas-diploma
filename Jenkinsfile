pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            echo "=============================== STARTING BUILD ====================================="
            steps{
                withMaven(maven: 'maven-latest') {
                      sh "mvn clean verify"
                }
            }
            echo "=============================== BUILD SUCCESSFUL ==================================="
        }
        stage('Test'){
            echo "=============================== STARTING TESTS ====================================="
            steps {
                echo "Test stage works fine!"
            }
            echo "=============================== TESTS ARE SUCCESSFUL ==============================="
        }
        stage('Deploy') {
            echo "=============================== STARTING DEPLOY ===================================="
            steps {
                echo "Deploy stage works fine!"
            }
            echo "=============================== DEPLOY SUCCESSFUL =================================="
        }
    }
}