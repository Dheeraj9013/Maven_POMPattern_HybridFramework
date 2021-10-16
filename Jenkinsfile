pipeline { 
agent any 
    stages { 
        
        stage ('Build') { 
            steps{
                echo "Building"

            }
        }
        
        stage('Deploy to QA') {
            steps{
                echo "Deploy to QA"
            }
        }
        
        stage('Test On QA'){
            steps{
                echo "Regression Test On QA"
            }
        }
        
        stage('deploy on stage'){
            steps{
                echo "Deploy to stage"
            }
        }
        stage('Test on stage'){
            steps{
                echo "sanity Test to stage"
            }
        }
        stage('deploy on prod'){
            steps{
                echo "Deploy to prod"
            }
        }
        
        
        stage('Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "mvn clean install"
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'build', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
        
    }

 }