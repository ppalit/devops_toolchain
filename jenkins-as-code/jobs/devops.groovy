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

job('Terraform Verify') {
       steps {
         shell('terraform -version')
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
