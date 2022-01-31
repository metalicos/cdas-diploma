#!groovy

properties([disableConcurrentBuilds()])
pipeline {
  agent any
  pom = readMavenPom file: 'pom.xml'
  triggers {
    pollSCM('* * * * *')
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
        echo pom.version
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
    stage('Run Docker Image') {
      steps {
        echo "=============================== STARTING DEPLOY ===================================="
        bat "docker run -d -t -i -e DB_PASSWORD=${DB_PASSWORD} -e DB_USERNAME=${DB_USERNAME} -e DB_URL=${DB_URL} -e JWT_SECRET=${JWT_SECRET} -p 80:5051 --name=cdas-micro account-microservice"
        echo "=============================== DEPLOY SUCCESSFUL =================================="
      }
    }
  }
}