package com.cftechsol.rest.roles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.cftechsol.rest.entities.GenericAuditEntity;
import com.cftechsol.rest.permissions.Permission;
import com.cftechsol.rest.users.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Role entity.
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cod", name = "role_u1"))
public class Role extends GenericAuditEntity<Long> {

	private static final long serialVersionUID = 5036881311288942993L;

	@Column
	@NotNull
	private String cod;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE }, mappedBy = "roles")
	private Set<User> users = new HashSet<User>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "role_permission", joinColumns = {
			@JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "role_permission_fk1")) }, inverseJoinColumns = {
					@JoinColumn(name = "permission_id", foreignKey = @ForeignKey(name = "role_permission_fk2")) })
	private Set<Permission> permissions = new HashSet<Permission>();

}
