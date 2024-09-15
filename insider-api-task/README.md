<div align="center">
  <a href="https://useinsider.com/">
    <img src="assets/insider-logo.png" alt="Logo" width="300" height="125">
  </a>
</div>

<h3 align="center">Insider QA Engineer API Task</h3>

<p align="center">Written with RestAssured (Java)</p>


## Built With

* [RestAssured](https://rest-assured.io)
* [TestNG](https://testng.org/doc/)
* [Lombok](https://projectlombok.org)

## How to Test
Just use the following commands for running tests with `maven`.

```sh
    mvn clean install -DskipTests=true
    mvn test -DsuiteXMLFile=src/test/resources/suites/regression.xml
```
