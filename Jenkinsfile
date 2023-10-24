pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'JAVA_HOME'
    }

    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Ayoub9LE/mef_backend.git']])
                bat 'mvn clean install'
            }
        }
        
        stage('Docker Compose Build and Up') {
            steps {
                script {
                    bat 'docker-compose build'
                    bat 'docker-compose up -d'
                }
            }
        }

        stage('Integration Tests') {
            steps {
                script {
                    bat 'mvn integration-test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Compose Down') {
            steps {
                script {
                    //sh 'docker-compose down'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DockerHubCredentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        bat "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                        bat "docker build -t ayoub9le/mef_backend:latest ."
                        bat "docker logout"
                    }
                }
            }
        }

        stage('Push Backend Docker Image to the Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DockerHubCredentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        bat "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                        bat "docker push ayoub9le/mef_backend:latest"
                        bat "docker logout"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([file(credentialsId: 'mykubeconfig', variable: 'KUBECONFIG')]) {
                        bat "kubectl apply -f deployment.yaml"
                    }
                }
            }
        }
    }
}
