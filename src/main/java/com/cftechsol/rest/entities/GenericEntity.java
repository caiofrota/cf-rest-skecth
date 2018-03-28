package com.cftechsol.rest.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity superclass that maps an entity with Id field.
 * 
 * @author Caio Frota <contact@cftechsol.com>
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
	public boolean equals(Object object) {
		// Checks if the object is null or not of the same type.
		if (object == null || !(object instanceof GenericEntity)) {
			return false;
		}

		// Checks if the objets are identical.
		if (this == object) {
			return true;
		}

		// Checks if the Ids are equals.
		GenericEntity<PK> other = (GenericEntity<PK>) object;
		if (getId() != null && getId().equals(other.getId())) {
			return true;
		}

		// Are not equals.
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

}
