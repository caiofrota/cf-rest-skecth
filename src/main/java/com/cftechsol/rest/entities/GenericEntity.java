package com.cftechsol.rest.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity superclass that maps an entity with Id field.
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
public class GenericEntity<PK> implements Serializable {

	private static final long serialVersionUID = -3539533419572137852L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private PK id;

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (this == o) return true;
		 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        GenericEntity<PK> that = (GenericEntity<PK>) o;
        return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

}
