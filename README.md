# CF Rest

An API to assist rest projects in terms of handling exceptions.

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

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/caiofrota/6e65a17fd3bf100d058cb48dcc780b21) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Caio Frota** - *Initial work* - [caiofrota](https://github.com/caiofrota)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
