# TASK 2 - Test Automation

> Attached is the project as a zip file.
Please use it and go through the following requirements:
> 1	Write a test to verify that the Catalog is empty on creation.
> 2	Add a Document and write a test to verify the Catalog is not empty.
> 3 Write a test to verify that retrieving documents returns the added documents.
> 4 Identify and write at least 2 other possible tests
> 5 Generate test reports
> 6 Use Docker for test automation execution


## "How to" guide

Run tests:
```sh
mvn clean test
```
Generate test report:
```sh
mvn clean test site
```
You can find generated _surefire-report.html_ file in the _target/site_ folder.

Build Docker image from Dockerfile:
```sh
docker build -t java-code-test .
```
Execute tests in Docker:
```sh
docker run java-code-test
```
