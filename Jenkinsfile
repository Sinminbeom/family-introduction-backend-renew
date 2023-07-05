pipeline {
    agent any
    tools {
        gradle 'gradle-7.6'
    }
    environment {
        TAG = "dev"
        GIT_URI= "https://github.com/Sinminbeom/family-introduction-backend-renew.git"
        PROJECT_NAME = "family-introduction-backend-renew"
        DOCKER_TAG = "1.0.${currentBuild.number}"
        REMOTE_PATH= "infra_glue/${PROJECT_NAME}"
        REMOTE_SERVICE_PATH= "${REMOTE_PATH}"


    }
    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'BRANCH', type: 'PT_BRANCH', sortMode: 'DESCENDING_SMART', listSize: '5'
    }
    stages {
        stage('git clone') {
            steps {
                git branch: "${params.BRANCH}",
                // credentialsId: "${GIT_CREDENTIAL_ID}",
                url: "${GIT_URI}"
            }
        }
        stage('build') {
            steps {
                sh '''
                    echo ./gradlew build
                    chmod +x gradlew
                    ./gradlew build
                '''
            }
        }
        stage('Docker build') {
            steps {
                sh "docker build -f ./deploy/${TAG}/Dockerfile -t ${PROJECT_NAME}:${DOCKER_TAG} ."

                sh "sed -i 's/<PROJECT_NAME>/${PROJECT_NAME}/g' ./deploy/${TAG}/docker-compose.yml"
                sh "sed -i 's/<TAG>/${TAG}/g' ./deploy/${TAG}/docker-compose.yml"
                sh "sed -i 's/<BUILD_VER>/${DOCKER_TAG}/g' ./deploy/${TAG}/docker-compose.yml"

            }
        }
        stage('Ssh transfer and docker restart') {
            steps{
                sshPublisher(publishers: [sshPublisherDesc(configName: 'aws',
                transfers: [
                    sshTransfer(cleanRemote: false, excludes: '',
                    execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
                    remoteDirectory: "${REMOTE_PATH}",
                    remoteDirectorySDF: false,
                    removePrefix: "deploy/${TAG}",
                    sourceFiles: "deploy/${TAG}/docker-compose.yml") ,

                    sshTransfer(cleanRemote: false, excludes: '',
                    execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
                    execCommand: """
                        cd ${REMOTE_SERVICE_PATH}
                        echo \"docker-compose down\" >> jenkins.log
                        docker-compose down

                        echo \"docker-compose up -d\" >> jenkins.log
                        docker-compose up -d

                        echo \"depoly end\" >> jenkins.log

                        """)
                    ],
                    usePromotionTimestamp: false,
                    useWorkspaceInPromotion: false, verbose: false)])
            }
        }



    }
}