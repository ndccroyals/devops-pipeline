branchRegex = '.*'
def gitRepoNames= [
        'ndcc-service-login-1'
]
def repoRoot='https://github.com/ndccroyals/'
gitRepoNames.each{
    repo ->

<<<<<<< HEAD:multilogin/multilogin.groovy
        multibranchPipelineJob(repo+'-multi-login'
        ) {  //predefined method to create multibranch job
            displayName repo+'-multi-login'
=======
        multibranchPipelineJob(repo+'-multi-branch Login') {  //predefined method to create multibranch job
            displayName repo+'-multi-branch'
>>>>>>> 24ea2d92d385da1428d960897a4f22c6a3ee163a:multi-branch/seed-job/multibranch_login.groovy

            triggers {
                periodic(15)
            }

            branchSources {
                configure { node ->
                    def sourceList = node / sources(class: 'jenkins.branch.MultiBranchProject\$BranchSourceList')
                    sourceList << 'data' {
                        'jenkins.branch.BranchSource' {
                            source(class: 'jenkins.plugins.git.GitSCMSource') {
                                credentialsId 'ndccroyals:Royals211016'
                                remote repoRoot + repo + '.git'
                                ignoreOnPushNotifications
                                traits {
                                    'jenkins.scm.impl.trait.RegexSCMHeadFilterTrait' {
                                        regex(branchRegex)
                                    }
                                    'jenkins.plugins.git.traits.BranchDiscoveryTrait'()
                                    'jenkins.plugins.git.traits.TagDiscoveryTrait'()
                                }
                            }
                        }

                    }
                    sourceList << owner("", reference: '../..', class: 'org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject')
                }
            }
            orphanedItemStrategy {
                discardOldItems {
                    daysToKeep(-1)
                    numToKeep(-1)
                }
            }

        }

}
listView("NDCC Multibranch Login Pipeline") {
    description("NDCC Multibranch Login Pipeline")
    jobs {
<<<<<<< HEAD:multilogin/multilogin.groovy
        regex('.+-multi-login')
=======
        regex('.+-multi-branch login')
>>>>>>> 24ea2d92d385da1428d960897a4f22c6a3ee163a:multi-branch/seed-job/multibranch_login.groovy
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
<<<<<<< HEAD:multilogin/multilogin.groovy
=======
}
>>>>>>> 24ea2d92d385da1428d960897a4f22c6a3ee163a:multi-branch/seed-job/multibranch_login.groovy
