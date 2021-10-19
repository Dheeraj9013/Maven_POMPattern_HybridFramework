pipeline { 
agent any 
	
    stages { 
        
        stage ('Build Jar') { 
            steps{
                bat "mvn clean package -DskipTests"

            }
        }
        
        stage('docker-grid') {
            steps {
               bat "docker network create selenoid"
               bat "docker-compose up -d"
            }
        }
                
     
        
        
        
        }

 }