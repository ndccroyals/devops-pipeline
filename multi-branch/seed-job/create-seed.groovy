job('Seed-Job') {
    description('Seed-Job')
    environmentVariables {
        keepBuildVariables(true)
        keepSystemVariables(true)
    }
    scm {
        git {
            remote {
                url("https://github.com/ndccroyals/devops-pipeline.git")
                credentials("ndccroyals:Royals211016")
            }
            branch('master')
        }
    }
    triggers {
        scm('H/30 * * * *')
        cron('H/60 H/24 * * *')
    }
    concurrentBuild(false)

    steps {
        dsl {
            external "multi-branch/*.groovy"
            removeAction('DELETE')
            removeViewAction('DELETE')
            ignoreExisting(false)
        }
    }
}
