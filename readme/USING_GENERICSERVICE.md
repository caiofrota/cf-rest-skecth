![alt text](https://cftechsol.com/wp-content/uploads/2017/12/caiofrota-logo-300x171.png)

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)

# CF Rest

### Using GenericService

GenericService<Repository, Entity, PK> is a super class that maps a service with basic methods!

* List<Entity> findAll()
* Entity findById(PK id)
* Entity save(Entity object)
* AuditEntity save(AuditEntity object, long user)
* void delete(PK id)
* Repository getRepository()

```
import org.springframework.stereotype.Service;
import com.cftechsol.rest.services.GenericService;

@Service
public class ExampleService extends GenericService<ExampleRepository, ExampleEntity, Long> {

}
```

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)
