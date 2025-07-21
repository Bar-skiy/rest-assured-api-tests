pipeline {
    agent any

    tools {
        maven 'Maven_3.9.6' // ← имя из Global Tool Configuration
    }

    environment {
        MAVEN_OPTS = '-Dfile.encoding=UTF-8'
    }

    stages {
        stage('Check Java Version') {
            steps {
                sh 'java -version'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
