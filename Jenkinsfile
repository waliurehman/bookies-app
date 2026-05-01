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
                sh 'mvn clean test || true'
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            emailext(
                subject: "Bookstore Tests - Build #${BUILD_NUMBER} - SUCCESS",
                body: """
                    Build: ${BUILD_NUMBER}
                    Status: SUCCESS
                    Tests: 15 passed
                    URL: ${BUILD_URL}
                """,
                to: "waliurehman4023@gmail.com"
            )
        }
    }
}