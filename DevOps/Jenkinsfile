pipeline {
    agent any
    
    stages {
        stage('SCM checkout') {
            steps {
                echo 'checkout completed Git'
            }
        }
        stage('Build') {
            steps {
                echo 'Build completed'
            }
        }
        stage('Test') {
            steps {
                sh 'pwd'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy completed'
            }
        }
    }
}