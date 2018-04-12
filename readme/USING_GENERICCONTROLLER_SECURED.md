![alt text](https://cftechsol.com/wp-content/uploads/2017/12/caiofrota-logo-300x171.png)

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)

# CF Rest

### Using GenericController secured

GenericController<Service, Entity, PK> is a super class that maps a controller with basic routes!

- You must pass Bearer token in the "Authorization" header, see [Using Authentication](/USING_AUTHENTICATION.md)
- To map secured routes, you must put in the path /admin/**
- If you want to control with roles, you must call super constructor passing true and a role prefix

* Code

```
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cftechsol.rest.controllers.GenericController;

@RestController
@RequestMapping(path = "/admin/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController extends GenericController<ExampleService, ExampleEntity, Long> {
	public ExampleController() {
		super(true, "EXAMPLE_");
		// Only if you want to control by roles! 
	}
	
	@GetMapping(path = "myroute")
	public void myRoute() {
		if (secured) {
			hasAnyRole("MY_ROLE", "OTHER_ROLE");
			// User must be one or more of these roles or admin!
		}
		// Your code ...
	}
	
	@GetMapping(path = "otherroute")
	public void otherRoute() {
		if (secured) {
			hasAllRoles("MY_ROLE", "OTHER_ROLE");
			// User must be all of these roles or admin!
		}
		// Your code ...
	}
}
```

* Routes

```
{[/admin/example],methods=[GET],produces=[application/json],roles=[ADMIN,EXAMPLE_FIND_ALL]}
{[/admin/example/id/{id}],methods=[GET],produces=[application/json],roles=[ADMIN,EXAMPLE_FIND_BY_ID]}
{[/admin/example],methods=[POST],produces=[application/json],roles=[ADMIN,EXAMPLE_SAVE]}
{[/admin/example],methods=[DELETE],produces=[application/json],roles=[ADMIN,EXAMPLE_DELETE]}
{[/admin/example/myroute],methods=[GET],produces=[application/json],roles=[ADMIN,EXAMPLE_MY_ROLE,EXAMPLE_OTHER_ROLE]}
{[/admin/example/otherroute],methods=[GET],produces=[application/json],roles=[ADMIN,EXAMPLE_MY_ROLE,EXAMPLE_OTHER_ROLE]}
```

You'll get 401 when the token is expired.
You'll get 403 when the "Authorization" is not present.
You'll get 500 when the token is invalid.

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)
