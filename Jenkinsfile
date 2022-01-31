#!groovy
properties([disableConcurrentBuilds()])
pipeline {
    agent any
    triggers{
        pollSCM('* * * * *') // every 1 minute
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        skipDefaultCheckout(true)
        skipStagesAfterUnstable()
        timestamps()
    }
    stages {
        stage('Prepare') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps{
                echo "=============================== STARTING BUILD ====================================="
                withMaven(maven: 'maven-latest') {
                        echo pwd()
                        sh "mvn clean verify"
                }
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