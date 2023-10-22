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


        stage('Unit Test') {
                    steps {
                        script {
                            // Run unit tests and generate test reports in JUnit format
                            bat 'mvn test surefire-report:report'
                        }
                    }
                    post {
                        always {
                            // Archive the test results
                            junit '**/target/surefire-reports/*.xml'
                        }
                    }
         }
        stage('Build Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DockerHubCredentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        bat "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                        bat "docker build -t ayoub9le/mef_backend:latest ."
                        // Ensure to logout from Docker after performing the Docker operations
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
                        // You may not need to pull the image before pushing if you're building it fresh
                        bat "docker pull ayoub9le/mef_backend:latest"
                        bat "docker push ayoub9le/mef_backend:latest"
                        bat "docker logout"
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Set up kubectl credentials from Jenkins secret
                    withCredentials([file(credentialsId: mycubeconfig, variable: 'KUBECONFIG')]) {
                        // Apply Kubernetes configurations
                        sh "kubectl apply -f ${deployment.yaml}"
                    }
                }
            }
        }
    }
}
