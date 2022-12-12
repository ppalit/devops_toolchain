import groovy.transform.Field

@Field String jenkinsCredentialId = "SSH_GIT_KEY"
@Field String basePath = 'Devops'

JobConstructor[] jobList = [
        [
                "VerifyTerraform"
        ]
]

class JobConstructor {
    def jobName = ""

    private JobConstructor(String jobName) {
        this.jobName = jobName
    }
}

jobList.each { job ->
    println "[INFO] Generating view... " + basePath
    println "[INFO] Generating job... " + job.jobName
    def jobName = job.jobName

    job(jobName) {
       steps {
         shell('terraform -version')
        }
    }
}



listView(basePath) {
    recurse(true)
    jobs {
        jobList.each { job ->
            name(basePath + "/" + job.jobName)
        }
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
