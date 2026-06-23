// =============================================================================
// CI/spring-boot-realworld.groovy - CI pipeline for spring-boot-realworld
//
// Job will be created at:  CI/spring-boot-realworld
// =============================================================================

pipelineJob('CD/spring-boot-sample-app') {
    displayName('spring-boot-sample-app.groovy- CD')
    description('Continuous Integration pipeline for spring-boot-sample-app.groovy')

    // --- Discard old builds
    logRotator {
        numToKeep(5)
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
