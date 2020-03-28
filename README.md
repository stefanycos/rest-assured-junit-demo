# Rest Assured with JUnit and Extent Reports Demo

Demo of rest assured using JUnit 4 and Extend Reports to generate a designed report result.

### Requirements
* Maven
* JDK 1.8

#### Configuration
* In application.properties set the path where the report result will be placed.

#### Running
* mvn test -Dtest=AppSuiteTests

#### Results
A report like below will be generated (in report directory you can find the full results)

![Resport Result](https://github.com/stefanycos/rest-assured-junit-demo/blob/master/report/report-sample.png?raw=true)
For generating this report was used Extent Reporting Framework, for details see: https://extentreports.com/.
