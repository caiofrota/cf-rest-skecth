![alt text](https://cftechsol.com/wp-content/uploads/2017/12/caiofrota-logo-300x171.png)

# CF Rest

### Exception Handler

#### Example of use exception handler

* Code

```
@Controller
@RequestMapping(path = "/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController {
	@GetMapping(path="/hello")
	public void getException() throws Exception {
		throw new Exception("Exception test!");
	}
}
```

* Request

```
curl http://localhost:8080/example/hello
```

* Response

```
Status: 500
Body:
{
  "status": "INTERNAL_SERVER_ERROR",
  "timestamp": "2018-04-11 10:44:01"
  "message": "Exception test!",
  "errors": null
}
```
