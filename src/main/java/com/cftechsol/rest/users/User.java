package com.cftechsol.rest.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.cftechsol.rest.entities.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User entity.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "user_u1"))
public class User extends GenericEntity<Long> {

	private static final long serialVersionUID = 1626969229170663509L;

	@Column
	@NotNull
	@Email
	private String email;

	@Column
	@NotNull
	@JsonIgnore
	private String password;

	@Column
	@NotNull
	private String name;

}
