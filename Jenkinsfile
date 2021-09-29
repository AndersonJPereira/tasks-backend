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
	}
}
