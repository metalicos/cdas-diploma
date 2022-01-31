#!groovy
properties([disableConcurrentBuilds()])
pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        skipDefaultCheckout(true)
        skipStagesAfterUnstable()
        timestamps()
    }
    stages {
        stage('However I want to name a stage') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps{
                echo "=============================== STARTING BUILD ====================================="
//                 withMaven(maven: 'maven-latest') {
//                       sh "mvn clean verify"
//                 }
                echo "=============================== BUILD SUCCESSFUL ==================================="
            }
        }
        stage('Test'){
            steps {
                echo "=============================== STARTING TESTS ====================================="
                echo "Test stage works fine!"
                echo "=============================== TESTS ARE SUCCESSFUL ==============================="
            }
        }
        stage('Deploy') {
            steps {
                echo "=============================== STARTING DEPLOY ===================================="
                echo "Deploy stage works fine!"
                echo "=============================== DEPLOY SUCCESSFUL =================================="
            }
        }
    }
}