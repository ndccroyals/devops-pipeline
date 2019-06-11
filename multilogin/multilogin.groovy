branchRegex = '.*'
def gitRepoNames= [
        'ndcc-service-login-1'
]
def repoRoot='https://github.com/ndccroyals/'
gitRepoNames.each{
    repo ->
        multibranchPipelineJob(repo+'-multi-branch Login') {  //predefined method to create multibranch job
            displayName repo+'-multi-branch'
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
        regex('.+-multi-branch')
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
}
