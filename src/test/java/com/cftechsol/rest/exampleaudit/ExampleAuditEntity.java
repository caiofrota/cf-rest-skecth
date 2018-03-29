package com.cftechsol.rest.exampleaudit;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.cftechsol.rest.entities.GenericAuditEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Example entity to execute test.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleAuditEntity extends GenericAuditEntity<Long> {

	private static final long serialVersionUID = -4236664326786547752L;
	
	@NotNull
	private String name;

}
