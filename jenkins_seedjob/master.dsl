// =============================================================================
// master.dsl - Entry point for Jenkins Seed Job
// Loads all CI and CD pipeline job definitions
// =============================================================================

// --- CI Folder
folder('CI') {
    displayName('CI')
    description('Continuous Integration pipelines')
}

// --- CD Folder
folder('CD') {
    displayName('CD')
    description('Continuous Delivery pipelines')
}

// --- Load all CI job definitions
def ciJobs = [
    'spring-boot-realworld'
]

ciJobs.each { jobName ->
    pipelineJob("CI/${jobName}") {
        displayName("${jobName} - CI")
        description("Continuous Integration pipeline for ${jobName}")

        logRotator {
            numToKeep(10)
        }

        triggers {
            scm('H/5 * * * *')
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
                scriptPath('Jenkinsfile')
                lightweight(true)
            }
        }
    }
}

// --- Load all CD job definitions
def cdJobs = [
    'spring-boot-realworld'
]

cdJobs.each { jobName ->
    pipelineJob("CD/${jobName}") {
        displayName("${jobName} - CD")
        description("Continuous Delivery pipeline for ${jobName}")

        logRotator {
            numToKeep(10)
        }

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
}
