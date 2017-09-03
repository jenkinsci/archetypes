# Example project for Jenkins Pipelines Global Shared Library with Unit Tests

This project includes a sample [Global Shared Library](https://jenkins.io/doc/book/pipeline/shared-libraries/) and the project that tests its usage in pipeline scripts using 
[Jenkins Pipeline Unit](https://github.com/lesfurets/JenkinsPipelineUnit).

## Project structure

* `shared-library` directory contains the actual code for the shared library, contents of this directory should be pushed to a SCM to be used in Jenkins.
* `unit-tests` directory contains a Apache Maven project for testing the shared library usage with Jenkins Pipeline Unit framework.