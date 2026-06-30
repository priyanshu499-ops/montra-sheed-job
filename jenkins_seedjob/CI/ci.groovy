def ciJobs = [
    'app1': [
        url        : 'https://github.com/k3mahesh/java-app-gradle.git',
        credentials: 'k3mahesh-cred',
        branch     : 'main',
        scriptPath : 'Jenkinsfile'
    ]
]

ciJobs.each { jobName, config ->
    pipelineJob("CI/${jobName}") {
        displayName("${jobName} — CI")
        description("Continuous Integration pipeline for ${jobName}")
        logRotator {
            numToKeep(10)
        }
        parameters {
            stringParam('BRANCH', 'main', 'Branch to build')
            stringParam('IMAGE_TAG', 'latest', 'Docker image tag')
        }
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(config.url)
                            credentials(config.credentials)
                        }
                        branch(config.branch)
                    }
                }
                scriptPath(config.scriptPath)
                lightweight(true)
            }
        }
    }
}
