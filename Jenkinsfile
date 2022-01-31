pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            println "=============================== STARTING BUILD ====================================="
            steps{
                withMaven {
                      sh "mvn clean verify"
                }
            }
            println "=============================== BUILD SUCCESSFUL ==================================="
        }
        stage('Test'){
            println "=============================== STARTING TESTS ====================================="
            steps {
                echo "Test stage works fine!"
            }
            println "=============================== TESTS ARE SUCCESSFUL ==============================="
        }
        stage('Deploy') {
            println "=============================== STARTING DEPLOY ===================================="
            steps {
                echo "Deploy stage works fine!"
            }
            println "=============================== DEPLOY SUCCESSFUL =================================="
        }
    }
}