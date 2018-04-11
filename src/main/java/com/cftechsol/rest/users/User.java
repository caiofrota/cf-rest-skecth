package com.cftechsol.rest.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.cftechsol.rest.entities.GenericAuditEntity;
import com.cftechsol.rest.userroles.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User entity.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "user_u1"))
public class User extends GenericAuditEntity<Long> {

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

	@Column
	@NotNull
	private boolean enabled;

	// @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	// @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id",
	// foreignKey = @ForeignKey(name = "user_role_fk1")) }, inverseJoinColumns = {
	// @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name =
	// "user_role_fk2")) })
	// @JsonIgnore
	// private Set<Role> roles = new HashSet<Role>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRole> roles = new ArrayList<>();

}
