pipeline {
    agent any

    tools {
        jdk 'jdk21'
    }

    options {
        skipDefaultCheckout(true)
    }

    environment {
        STUDENT_NAME = 'Kozhanov Abdualim'
        IMAGE_NAME   = 'kozhanov-abdualim-student-api'
        IMAGE_TAG    = 'v1.1-kozhanov-abdualim'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Pipeline started by student: ${STUDENT_NAME}"
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build -x test --no-daemon'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Verify') {
            steps {
                sh "docker image inspect ${IMAGE_NAME}:${IMAGE_TAG} --format 'Image exists: {{.RepoTags}}, size: {{.Size}} bytes'"
                echo "Build verified for student: ${STUDENT_NAME}"
            }
        }
    }
}
