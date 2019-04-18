node {
   def mvnHome
   def gradleHome
	
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git'https://github.com/rakeshkumargedam/StepCombinations.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
      gradleHome = tool 'gradle'	   
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('gradle build') {
        if (isUnix()){
		sh "'${gradleHome}/bin/gradle clean build'"
		} else {
		   bat(/"${gradleHome}\bin\gradle" clean build/)
		   }
		   }
		   
     // junit '**/target/surefire-reports/TEST-*.xml'
     // archive 'target/*.jar'
  // }
   
}
