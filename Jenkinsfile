pipeline {

agent any 

  tools {
 	    maven 'maven 3.5'
 		jdk 'oracle jdk 1.8'
		} 

	stages{
        stage('Git Check-Out') {
            steps {
                  git branch: 'master', url: 'https://github.com/capitalterefe/Brick.git'
            }
        }

        stage('Spin-Up Selenium-Grid Containers') {
            steps {
                 sshagent(['dockerLocalHost'])  {
                    script {
    			    GRIDPARAMS = sh (script: 'ssh -tt jenkins@35.207.6.62 /opt/grid/start_grid.sh "$numberOfRunners"', returnStdout: true).split("\r?\n")
    			    println("Grid parameters: ${GRIDPARAMS}")
    			    int top = GRIDPARAMS.size()
    			    IP_HUB = "${GRIDPARAMS[top-2]}"
    			    NETWORK = "${GRIDPARAMS[top-1]}"
    			    println("Grid parameters: $IP_HUB / $NETWORK")
                    }
                }
            }
        }
        stage('Run Automated Tests') {
            steps {
                script {
                    sh "mvn -Dwebdriver.remote.url=http://${IP_HUB}:4444/wd/hub -Dcucumber.options=--tags\" $tag\" -Dwebdriver.remote.driver=chrome -Dmaven.test.failure.ignore clean verify"
               
                }
            }
        }
  }
    
    post() {
        always {
                //publish serenity Report
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/site/serenity', reportFiles: 'index.html', reportName: 'Serenity Test Report', reportTitles: ''])
                junit '**/*.xml'
                     
                // stop grid     
				sshagent(['dockerLocalHost']) {
    				sh "ssh -tt jenkins@35.207.6.62 /opt/grid/stop_grid.sh $NETWORK"

  				   } 
   				   
                    //to avoid serenity permission issue
  				    sh 'chmod -R 755 "${WORKSPACE}"/target/site/serenity/'
   				   
  				   //kick Bamboo
  				//    withCredentials([usernameColonPassword(credentialsId: 'bambooCredentials', variable: 'bambooUser')]) {
               //     sh 'curl -k -X POST -u $bambooUser  https://bamboo.gtwy/rest/api/latest/queue/CMTEST-"$PlanId"?os_authType=basic'
              //      }

  		       }
   		       
   		       
        failure {
        	sh 'echo Pipeline Failed'
        	// stop grid     
				sshagent(['dockerLocalHost']) {
    				sh "ssh -tt yeshiwase_ayele@35.207.6.62 /opt/grid/stop_grid.sh $NETWORK"
  				   } 
          // mail to: team@example.com, subject: 'The Pipeline failed :('
        		}
    }
    		
    }
