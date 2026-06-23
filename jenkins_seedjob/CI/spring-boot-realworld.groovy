// =============================================================================
// CI/spring-boot-realworld.groovy - CI pipeline for spring-boot-realworld
//
// Job will be created at:  CI/spring-boot-realworld
// =============================================================================

pipelineJob('CI/spring-boot-realworld') {
    displayName('spring-boot-realworld - CI')
    description('Continuous Integration pipeline for spring-boot-realworld')

    // --- Discard old builds
    logRotator {
        numToKeep(10)
    }

    // --- SCM Polling trigger (every 5 min)
    triggers {
        scm('H/5 * * * *')
    }

    // --- Pipeline definition
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/priyanshu499-ops/spring-boot-realworld-example-app.git')
                        credentials('github-token')
                    }
                    branch('master')
                }
            }
            scriptPath('Jenkinsfile')
            lightweight(true)
        }
    }
}
