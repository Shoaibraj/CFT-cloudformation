pipeline {
    agent { 
        label 'node1' 
        }
    stages {
        stage('git clone') {
            steps {
                echo 'clone repositery consist of build packages'
                git branch: 'main', url: 'https://github.com/Shoaibraj/CFT-cloudformation.git'
                  }
             }
        stage('aws cli installation') {
            steps {
                sh '''
                echo "installing aws cli"
                sudo apt update
                sudo apt install awscli -y 
                '''
            }
        }     
        stage('cloudformation stack') {
            steps {
                sh '''
                cd /home/ubuntu/workspace/project-1
                '''
            }
        }
        stage('CFT Running on cloud') 
        {
            steps {
            sh '''
            echo "CFT Run"
            cd /home/ubuntu/workspace/project-1
            aws cloudformation create-stack --stack-name mystacknew --template-body file://public.yaml --parameters ParameterKey=KeyPairName,ParameterValue=node.js --region us-east-1
            '''
            }
        }
    }
}