pipeline{
	agent any
	stages{
		stage('Build Backend'){
			steps{
				bat 'mvn clean package -DskipTests=true'
			}
		}
		stage('Unit Tests'){
			steps{
				bat 'mvn test'
			}
		}
		stage('SonarQube-Analysis'){
			environment{
			    scannerHome=tool 'SONAR_SCANNER'
			}
			steps{
				withSonarQubeEnv('SONAR_LOCAL'){
					bat	"${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBackEnd-Analysis -Dsonar.host.url=http://localhost:9000 -Dsonar.login=033f07daba0dd2c599643aa04cd647c607eb49f4 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/model**,**/src/test/**,**Application.java"  
				}
			}
		}
		stage('Quality Gate'){
			steps{
				sleep(10)
				timeout(time:1, unit:'MINUTES'){
					waitForQualityGate abortPipeline:true 
				}
			}
		}
		stage('Deploy Backend'){
			steps{
				deploy adapters: [tomcat8(credentialsId: 'TomCatLogin', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
			}
		}
		stage('API Tests'){
			steps{
				dir('api-tests'){
					git 'https://github.com/AndersonJPereira/tasks-api-test.git'
					bat 'mvn clean test'			    
				}
			}
		}
		stage('Deploy Frontend'){
			steps{
				dir('tasks-frontend'){
					git 'https://github.com/AndersonJPereira/tasks-frontend.git'
					bat 'mvn clean package'	
					deploy adapters: [tomcat8(credentialsId: 'TomCatLogin', path: '', url: 'http://localhost:8001')], contextPath: 'tasks', war: 'target/tasks.war'		    
				}
			}
		}
	}
}


