// =============================================================================
// master.dsl - Entry point for Jenkins Seed Job
// Auto-loads all groovy files from CI/ and CD/ folders
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

// --- Auto-load all CI groovy files
def ciFolder = new File(SEED_JOB_WORKSPACE + '/jenkins_seedjob/CI')
ciFolder.eachFileMatch(~/.*\.groovy/) { file ->
    evaluate(file)
}

// --- Auto-load all CD groovy files
def cdFolder = new File(SEED_JOB_WORKSPACE + '/jenkins_seedjob/CD')
cdFolder.eachFileMatch(~/.*\.groovy/) { file ->
    evaluate(file)
}
