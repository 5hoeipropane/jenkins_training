def updateGithubStatus(state){
    withCredentials([usernamePassword( credentialsId: 'github_dryrun_training', usernameVariable: 'USER', passwordVariable: 'PASSWORD')]) {
        String buildUrl = "${BUILD_URL}"
        String buildUrlD = buildUrl.replaceAll( 'localhost', 'doppio-tech.com' )
        sh """
            curl --location 'https://api.GitHub.com/repos/${USER}/jenkins_training/statuses/${GIT_COMMIT}?=null' \
                    --header 'Content-Type: application/json' \
                    --header 'Authorization: Bearer ${PASSWORD}' \
                    --data '{"state": "${state}","context": "Dryrun", "description": "Jenkins", "target_url": "${buildUrlD}"}'
        """
    }
}