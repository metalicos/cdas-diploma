#!groovy

properties([disableConcurrentBuilds()])
pipeline {
  agent any
  triggers {
    pollSCM('* * * * *')
  }
  environment {
    IMAGE = readMavenPom().getArtifactId().toLowerCase()
    VERSION = readMavenPom().getVersion().toLowerCase()
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
        echo IMAGE
        echo VERSION
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
        bat "docker build -t ${IMAGE}-${VERSION} ."
        echo "======================== DOCKER IMAGE CREATION IS SUCCESSFUL ======================="
      }
    }
    stage('Run Docker Image') {
      steps {
        echo "=============================== STARTING DEPLOY ===================================="
        script {
          def containerIdThatRunning = bat (
            returnStdout: true,
            script: "docker ps -q --filter name=${IMAGE}-${VERSION}"
          ).trim()
          echo containerIdThatRunning
          if (containerIdThatRunning != null && containerIdThatRunning != "") {
            bat "docker stop ${IMAGE}-${VERSION}"
            echo "${IMAGE}-${VERSION} container is stopped"
            bat "docker rm ${IMAGE}-${VERSION}"
            echo "${IMAGE}-${VERSION} container is removed"
          }
          bat "docker run -d -t -i -e DB_PASSWORD=${DB_PASSWORD} -e DB_USERNAME=${DB_USERNAME} -e DB_URL=${DB_URL} -e JWT_SECRET=${JWT_SECRET} -p 80:5051 --name=${IMAGE}-${VERSION} ${IMAGE}-${VERSION}"
          echo "=============================== DEPLOY SUCCESSFUL =================================="
        }
      }
    }
  }
}