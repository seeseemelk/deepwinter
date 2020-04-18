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
      steps {
        dir(path: 'docs') {
          sh 'make html'
        }
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'docs/build/html', reportFiles: 'index.html', reportName: 'Documentation', reportTitles: ''])

      }
    }

  }
}
