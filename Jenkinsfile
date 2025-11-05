pipeline {
    agent any

    environment {
        IMAGE_NAME = 'api-spring'
        CONTAINER_NAME = 'api_spring'
        GIT_REPO = 'https://github.com/VitorMalacarne/SpringBoot_sistema-monitoramento.git'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "${GIT_REPO}"
            }
        }

        stage('Clean old containers/images') {
            steps {
                sh '''
                docker rm -f ${CONTAINER_NAME} || true
                docker rmi -f ${IMAGE_NAME}:latest || true
                '''
            }
        }

        stage('Build image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:latest .'
            }
        }

        stage('Run container') {
            steps {
                sh 'docker run -d --name ${CONTAINER_NAME} -p 8080:8080 ${IMAGE_NAME}:latest'
            }
        }
    }
}
