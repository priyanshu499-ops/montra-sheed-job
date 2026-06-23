pipelineJob('CD/spring-boot-realworld3') {
    displayName('spring-boot-realworld3 - CD')
    description('CD pipeline for spring-boot-realworld3')
    logRotator { numToKeep(10) }
    parameters {
        stringParam('IMAGE_TAG', 'latest', 'Docker image tag to deploy')
        choiceParam('ENVIRONMENT', ['staging', 'production'], 'Target deployment environment')
    }
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
            scriptPath('Jenkinsfile.cd')
            lightweight(true)
        }
    }
}
