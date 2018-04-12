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
  "timestamp":{
    "dayOfMonth": 11,
    "dayOfWeek": "WEDNESDAY",
    "dayOfYear": 101,
    "month": "APRIL",
    "monthValue": 4,
    "year": 2018,
    "hour": 22,
    "minute": 36,
    "nano": 306000000,
    "second": 7,
    "chronology":{
      "id": "ISO",
      "calendarType": "iso8601"
    }
  },
  "message": "Exception test!",
  "errors": null
}
```
