pipeline {
    agent any

    tools {
        maven 'Maven 3.9.10' // Hoặc tên cấu hình Maven trong Jenkins
        jdk 'JDK 21'         // Tên JDK nếu bạn đã cấu hình
    }

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/vdprogramm/lib_be.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                bat './deploy.bat'  // Nếu có script deploy
            }
        }
    }
}
