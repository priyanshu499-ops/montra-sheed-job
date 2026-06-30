def cdJobs = [
    'app1': [
        url        : 'https://github.com/your-org/app1.git',
        credentials: 'github-credentials-id',
        branch     : 'main',
        scriptPath : 'Jenkinsfile.cd'
    ],
    'app2': [
        url        : 'https://github.com/your-org/app2.git',
        credentials: 'github-credentials-id',
        branch     : 'main',
        scriptPath : 'Jenkinsfile.cd'
    ]
]

cdJobs.each { jobName, config ->
    pipelineJob("CD/${jobName}") {
        displayName("${jobName} — CD")
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
