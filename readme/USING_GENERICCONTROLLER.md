![alt text](https://cftechsol.com/wp-content/uploads/2017/12/caiofrota-logo-300x171.png)

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)

# CF Rest

### Using GenericController

GenericController<Service, Entity, PK> is a super class that maps a controller with basic routes!

* Code

```
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cftechsol.rest.controllers.GenericController;

@RestController
@RequestMapping(path = "/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController extends GenericController<ExampleService, ExampleEntity, Long> {

}
```

* Routes

```
{[/example],methods=[GET],produces=[application/json]}
{[/example/id/{id}],methods=[GET],produces=[application/json]}
{[/example],methods=[POST],produces=[application/json]}
{[/example],methods=[DELETE],produces=[application/json]}
```

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)
