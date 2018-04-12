![alt text](https://cftechsol.com/wp-content/uploads/2017/12/caiofrota-logo-300x171.png)

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)

# CF Rest

### Using GenericEntity and GenericAuditEntity

GenericEntity<PK> is a super class that maps an entity with Id field!

GenericAuditEntity<PK> is a GenericEntity with audit fields! (created by, updated by, created on, updated on) 

#### Example of use exception handler

* Example GenericEntity

```
import javax.persistence.Entity;
import com.cftechsol.rest.entities.GenericEntity;
import lombok.Data;

/**
 * ExampleEntity
 *   Long id (Mapped by GenericEntity)
 *   String name
 */
@Entity
@Data
public class ExampleEntity extends GenericEntity<Long> {
	private String name;
}
```

* Example GenericAuditEntity

```
import javax.persistence.Entity;
import com.cftechsol.rest.entities.GenericAuditEntity;
import lombok.Data;

/**
 * ExampleEntity
 *   Long id (Mapped by GenericEntity)
 *   String name
 *   Long createBy (Mapped by GenericAuditEntity)
 *   Date createOn (Mapped by GenericAuditEntity)
 *   Long updatedBy (Mapped by GenericAuditEntity)
 *   Date updatedOn (Mapped by GenericAuditEntity)
 */
@Entity
@Data
public class ExampleEntity extends GenericAuditEntity<Long> {
	private String name;
}
```

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)
