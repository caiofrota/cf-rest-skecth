package com.cftechsol.rest.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.cftechsol.rest.entities.GenericEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity superclass that maps an entity with audit fields.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 *
 * @param <PK>
 *            Primary Key
 */
@MappedSuperclass
@Getter
@Setter
public class GenericAuditEntity<PK> extends GenericEntity<PK> {

	private static final long serialVersionUID = 2127537069480551984L;

	@Column(name = "created_by")
	private long createdBy;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_by")
	private long updatedBy;

	@Column(name = "updated_on")
	private Date updatedOn;
	
}
