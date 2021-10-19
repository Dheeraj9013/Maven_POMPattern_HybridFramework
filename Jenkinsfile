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
               
               bat "docker-compose up -d"
            }
        }
        
        stage('Run Test') {
            steps {
               
               bat "mvn clean install"
            }
        }
                
     
        
        
        
        }

 }