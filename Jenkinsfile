#!groovy

properties([disableConcurrentBuilds()])
pipeline {
  agent any
  triggers {
    pollSCM('* * * * *')
  }
  environment {
    try{
      IMAGE = ""
      VERSION = ""
      IMAGE = readMavenPom().getArtifactId().toLowerCase()
      VERSION = readMavenPom().getVersion().toLowerCase()
    }catch(Exception ex){
      echo "First run - no pom.file yet because we before Prepare step"
      FIRST_RUN = true;
    }
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
        if(FIRST_RUN){
          IMAGE = readMavenPom().getArtifactId().toLowerCase()
          VERSION = readMavenPom().getVersion().toLowerCase()
        }
        echo IMAGE
        echo VERSION
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
        bat "docker build -t ${IMAGE}-${BUILD_NUMBER}:${VERSION} -t ${IMAGE}-${BUILD_NUMBER}:latest ."
        echo "======================== DOCKER IMAGE CREATION IS SUCCESSFUL ======================="
      }
    }
    stage('Run Docker Image') {
      steps {
        echo "=============================== STARTING DEPLOY ===================================="
        script {
          try {
            def containerIdThatRunning = bat(returnStdout: true, script: "docker ps -q --filter name=${IMAGE}-${VERSION}")
            bat "docker stop ${IMAGE}-${VERSION}"
            bat "docker rm ${IMAGE}-${VERSION}"
          } catch (Exception e) {
            echo "None ${IMAGE} running containers found, continue."
          }
          bat("""docker run -d -t -i \
          -e CDAS_DB_PASSWORD=${CDAS_DB_PASSWORD} \
          -e CDAS_DB_USERNAME=${CDAS_DB_USERNAME} \
          -e CDAS_DB_URL=${CDAS_DB_URL} \
          -e JWT_SECRET=${JWT_SECRET} \
          -p 7070:5051 \
          --name=${IMAGE}-${VERSION} ${IMAGE}-${BUILD_NUMBER}""")
          echo "=============================== DEPLOY SUCCESSFUL =================================="
        }
      }
    }
  }
}