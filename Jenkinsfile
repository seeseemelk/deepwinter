pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './gradlew jar'
        archiveArtifacts '*.jar'
      }
    }

    stage('Test') {
      steps {
        sh './gradlew check'
        junit 'build/test-results/test/*.xml'
      }
    }

    stage('Documentation') {
      parallel {
        stage('Manual') {
          steps {
            dir(path: 'docs') {
              sh 'make html'
            }

            script {
              publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'docs/build/html', reportFiles: 'index.html', reportName: 'Documentation', reportTitles: ''])
            }

          }
        }

        stage('Javadoc') {
          steps {
            sh './gradlew javadoc'
            script {
              publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/docs/javadoc', reportFiles: 'index.html', reportName: 'Javadoc', reportTitles: ''])
            }

          }
        }

      }
    }

  }
}