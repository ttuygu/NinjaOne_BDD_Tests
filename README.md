# NinjaOneRMM Application Testing

## Purpose
This repository is designed to provide am example of using various testing and reporting tools in a Java project. It includes integration with Cucumber for BDD, Selenium for browser automation, and several reporting tools to generate detailed test reports.

## Libraries Used
- **Cucumber**: For Behavior-Driven Development (BDD)
- **WebDriverManager**: For managing browser drivers
- **Selenium**: For browser automation
- **JUnit**: For unit testing
- **ExtentReports**: For generating detailed test reports
- **Log4j**: For logging
- **SLF4J**: For logging abstraction

## Running Tests
To run the tests, use the following Maven command:
```sh
mvn clean test
```
How to run scripts in a specific browser with Maven
```sh
mvn test -Dbrowser=chrome -Dcucumber.filter.tags="@login"
```

Running only the scenarios that failed in the previous run
```sh
mvn test -Dcucumber.features="@target/rerun.txt"
```

## GitHub Actions

### Ninja Ci Workflow

This `ninjaone-ci` workflow is triggered on pushes and pull requests to the main branch. It also allows to trigger the test run manually. 

The purpose of theworkflow is to ensure that the project builds successfully whenever changes are pushed to the main branch or a pull request is made to the main branch.

## Reports

Various reports are generated after running the tests. These include:

* Cucumber-JVM-Reports
* Cucumber-HTML-Reports  (when the test is run through the command line)
* ExtentReports
    * html
    * excel
    * pdf

## Logs

Logs are generated using Log4j. The log file is stored in the `Logs` directory.