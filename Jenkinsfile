pipeline {
    agent {
        dockerfile true
    }
    stages {
        stage('Run Provar Tests') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"       
                bat "xvfb-run ant -f ANT/build.xml -v"
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: 'ANT/Results/*.xml'
            cleanWs notFailBuild: true /* cleans up the workspace */
        }
        success {
            echo "Success: Good job!"
        }        
        failure {            
            echo 'Failure: Something went wrong with the Provar ANT build. Printing environment for debugging'            
            bat 'printenv'
            echo 'Printing hosts'
            bat 'cat /etc/hosts'
            echo 'Searching for provar directories/files in the system...'
            bat 'find / -name "provar*"'
            echo 'Finding chrome drivers'
            bat "find / -name '*chromedriver*'"
        }        
    }   
}