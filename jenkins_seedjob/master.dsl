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
def workspace = WORKSPACE
new File(workspace + '/jenkins_seedjob/CI').eachFileMatch(~/.*\.groovy/) { file ->
    evaluate(file)
}

// --- Auto-load all CD groovy files
new File(workspace + '/jenkins_seedjob/CD').eachFileMatch(~/.*\.groovy/) { file ->
    evaluate(file)
}
