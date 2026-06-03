pipeline {
    agent any
     environment{
  ACR_LOGIN_SERVER = 'devopsproject2.azurecr.io'
  IMAGE_NAME = 'uniportal'
  TAG = 'latest'
  }

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
         stage('Login to ACR') {
       steps {
         withCredentials([usernamePassword(
             credentialsId: 'acr-creds',
             usernameVariable: 'ACR_USERNAME',
             passwordVariable: 'ACR_PASSWORD'
         )]) {
             sh "echo \$ACR_PASSWORD | docker login \$ACR_LOGIN_SERVER -u \$ACR_USERNAME --password-stdin"
           }
          }
         }
		 
	stage('Tag Image') {
        steps {
         sh '''
           docker tag ${IMAGE_NAME}:${TAG} \
           $ACR_LOGIN_SERVER/${IMAGE_NAME}:${TAG}
         '''
          }
        }
    stage('Push Image to ACR'){
	      steps{
	        sh 'docker push $ACR_LOGIN_SERVER/${IMAGE_NAME}:${TAG}'
	    }
	   }
    }

    }











