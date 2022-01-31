#!groovy

properties([disableConcurrentBuilds()])
pipeline {
  agent any
  triggers {
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
      steps {
        echo "=============================== STARTING BUILD ====================================="
        withMaven(maven: 'maven-latest') {
          bat "mvn clean install"
        }
        echo "=============================== BUILD SUCCESSFUL ==================================="
      }
    }
    stage('Create Docker Image') {
      steps {
        echo "========================== STARTING DOCKER IMAGE CREATION =========================="
        bat "docker build -t account-microservice ."
        echo "======================== DOCKER IMAGE CREATION IS SUCCESSFUL ======================="
      }
    }
    stage('Deploy') {
      steps {
        echo "=============================== STARTING DEPLOY ===================================="
        bat "docker run -dp 80:5051 account-microservice"
        echo "=============================== DEPLOY SUCCESSFUL =================================="
      }
    }
  }
}