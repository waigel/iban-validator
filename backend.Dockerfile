FROM openjdk:17-jdk-alpine
MAINTAINER "Johannes Waigel"
WORKDIR app

ARG GIT_BRANCH="local"
ARG GIT_COMMIT="ffffff"

ENV GIT_BRANCH=${GIT_BRANCH}
ENV GIT_COMMIT=${GIT_COMMIT}

LABEL BRANCH=${GIT_BRANCH}
LABEL COMMIT=${GIT_COMMIT}

COPY build/libs/ .
CMD ["java","-jar","backend-0.0.1-SNAPSHOT.jar"]