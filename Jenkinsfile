pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            emailext(
                subject: "Bookstore Tests - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}",
                body: "Build: ${BUILD_NUMBER}\nStatus: ${currentBuild.currentResult}\nURL: ${BUILD_URL}",
                to: "waliurehman4023@gmail.com"
            )
        }
    }
}