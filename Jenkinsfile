pipeline {
    agent any

    stages {
        stage('git checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/maniishab/uniportal.git'
            }

        }

        stage ('build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage ("build the image") {
            steps {
                sh 'docker build -t uniportal:latest .'
            }
        }
    }

    }











