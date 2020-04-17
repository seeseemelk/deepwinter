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

  }
}