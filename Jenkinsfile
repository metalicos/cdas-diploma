// #!groovy
//
// properties([disableConcurrentBuilds()])
// pipeline {
//   agent any
//   triggers {
//     pollSCM('* * * * *')
//   }
//   environment {
//     IMAGE = ""
//     VERSION = ""
//   }
//   options {
//     buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
//     skipDefaultCheckout(true)
//     skipStagesAfterUnstable()
//     timestamps()
//   }
//   stages {
//     stage('Prepare') {
//       steps {
//         checkout scm
//         script {
//           IMAGE = readMavenPom().getArtifactId().toLowerCase()
//           VERSION = readMavenPom().getVersion().toLowerCase()
//         }
//         echo IMAGE
//         echo VERSION
//       }
//     }
//     stage('Build') {
//       steps {
//         echo "=============================== STARTING BUILD ====================================="
//         withMaven(maven: 'maven-latest') {
//           bat "mvn clean install"
//         }
//         echo "=============================== BUILD SUCCESSFUL ==================================="
//       }
//     }
//     stage('Create Docker Image') {
//       steps {
//         echo "========================== STARTING DOCKER IMAGE CREATION =========================="
//         bat "docker build -t ${IMAGE}-${BUILD_NUMBER}:${VERSION} -t ${IMAGE}-${BUILD_NUMBER}:latest ."
//         echo "======================== DOCKER IMAGE CREATION IS SUCCESSFUL ======================="
//       }
//     }
//     stage('Run Docker Image') {
//       steps {
//         echo "=============================== STARTING DEPLOY ===================================="
//         script {
//           try {
//             def containerIdThatRunning = bat(returnStdout: true, script: "docker ps -q --filter name=${IMAGE}-${VERSION}")
//             bat "docker stop ${IMAGE}-${VERSION}"
//             bat "docker rm ${IMAGE}-${VERSION}"
//           } catch (Exception e) {
//             echo "None ${IMAGE} running containers found, continue."
//           }
//           bat("""docker run -d -t -i \
//           -e CDAS_DB_PASSWORD=${CDAS_DB_PASSWORD} \
//           -e CDAS_DB_USERNAME=${CDAS_DB_USERNAME} \
//           -e CDAS_DB_URL=${CDAS_DB_URL} \
//           -e JWT_SECRET=${JWT_SECRET} \
//           -p 7070:5051 \
//           --name=${IMAGE}-${VERSION} ${IMAGE}-${BUILD_NUMBER}""")
//           echo "=============================== DEPLOY SUCCESSFUL =================================="
//         }
//       }
//     }
//   }
// }

properties([disableConcurrentBuilds()])
pipeline {
  agent any
  triggers {
    pollSCM('* * * * *')
  }
  environment {
    IMAGE = ""
    VERSION = ""
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
        script {
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
        bat "docker build -t ${IMAGE}:${VERSION} -t ${IMAGE}:latest ."
        echo "======================== DOCKER IMAGE CREATION IS SUCCESSFUL ======================="
      }
    }
    stage('Run Docker Image') {
      steps {
        echo "=============================== STARTING DEPLOY ===================================="
        script {
          int CONTAINERS_NUM = 5;
          for ( int i = 0; i < CONTAINERS_NUM; i++ ) {
            try {
              bat "docker stop ${IMAGE}_" + i + "-${VERSION}"
              bat "docker rm ${IMAGE}_" + i + "-${VERSION}"
            } catch (Exception e) {
              echo "${IMAGE}_" + i + "-${VERSION} container is not running."
            }
            bat "docker run -d -t -i -e CDAS_DB_PASSWORD=\"${CDAS_DB_PASSWORD}\" -e CDAS_DB_USERNAME=\"${CDAS_DB_USERNAME}\" -e CDAS_DB_URL=\"${CDAS_DB_URL}\" -e JWT_SECRET=\"${JWT_SECRET}\" -p 707" + i + ":5051 --name=${IMAGE}_" + i + "-${VERSION} ${IMAGE}"
          }
          echo "=============================== DEPLOY SUCCESSFUL =================================="
        }
      }
    }
  }
}