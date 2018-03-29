![alt text](https://cftechsol.com/wp-content/uploads/2017/12/caiofrota-logo-300x171.png)

[![Build](https://img.shields.io/travis/caiofrota/cf-rest.svg)](#)
[![Coverage](https://codecov.io/gh/caiofrota/cf-rest/branch/master/graph/badge.svg)](#)
[![GitHub issues](https://img.shields.io/github/issues/caiofrota/cf-rest.svg)](https://github.com/caiofrota/cf-rest/issues)
[![Fork](https://img.shields.io/github/forks/caiofrota/cf-rest.svg)](#)
[![Stars](https://img.shields.io/github/stars/caiofrota/cf-rest.svg)](#)
[![License](https://img.shields.io/github/license/caiofrota/cf-rest.svg)](#)
[![Total Downloads](https://img.shields.io/github/downloads/caiofrota/cf-rest/total.svg)](https://github.com/caiofrota/cf-rest/releases)
[![Slack Chat](https://img.shields.io/badge/chat-slack-green.svg)](https://cftechsol.slack.com)
[![Website](https://img.shields.io/badge/website-cftechsol.com-green.svg)](https://cftechsol.com)

# CF Rest

An API to assist rest projects in terms of handling exceptions.

Version 1.0.0 still in progress.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See [deployment](#deployment) for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

* [Maven](https://maven.apache.org/) - Dependency Management
* [Project Lombok](https://projectlombok.org/) - Reducing Boilerplate Code

### Installing

Clone this repository

```
git clone https://github.com/caiofrota/cf-rest.git
```

## Deployment

*pom.xml*

* Add the dependency and the repository in your pom.xml

```
  <dependencies>
    <dependency>
    <groupId>com.cftechsol</groupId>
    <artifactId>cf-rest</artifactId>
    <version>1.0.0</version>
  </dependency>
  
  <repositories>
    <repository>
      <id>cftechsol.com</id>
      <url>https://maven2.cftechsol.com</url>
    </repository>
  </repositories>
```

*Application.java*

* Add "com.cftechsol" to @ComponentScan

```
@SpringBootApplication
@ComponentScan(basePackages = { "com.yourpackage", "com.cftechsol" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

### Running example

```
@Controller
@RequestMapping(path = "/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {
  
  @GetMapping(path="/hello")
	public void getException() throws Exception {
		throw new Exception("Exception test!");
	}
  
}
```

### Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://projects.spring.io/spring-boot/) - Inversion Of Control (IOC) - Boot, Data and Test
* [Project Lombok](https://projectlombok.org/) - Reducing Boilerplate Code
* [H2 Database](http://www.h2database.com) - Java SQL Database - To run the tests
* [GSON](https://github.com/google/gson) - Google JSON library for Java
* [JaCoCo](http://www.eclemma.org/jacoco/) - Code Coverage Library for Java

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/caiofrota/6e65a17fd3bf100d058cb48dcc780b21) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Caio Frota** - *Initial work* - [caiofrota](https://github.com/caiofrota)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
