pipeline {
    agent { label "dotnet"}
    triggers { 
        pollSCM('* * * * * ')
        }
    parameters {
        string(name: 'dotnet', defaultValue: 'dotnet build', description: 'this is for dotnet selection') 
        choice(name: 'branch', choices: ['master', 'branch1'], description: 'this is for branch selection')
  } 
    stages {
      stage ('vcs') {
            steps {
                git branch: "${params.branch}",
                  url: 'https://github.com/anji1649github/dotnetcore-docs-hello-world.git'
            }
        }
      stage ('dotnet build') {
              steps {
                    sh "${params.dotnet}"
                    sh 'dotnet publish'
                }

             }
       stage ('archiveArtifacts') {
              steps {
                   archiveArtifacts artifacts: '**/publish/*'
                   }
                 
                  
                }
         }

  }