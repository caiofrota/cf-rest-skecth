package com.cftechsol.rest.roles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.cftechsol.rest.entities.GenericAuditEntity;
import com.cftechsol.rest.userroles.UserRole;

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
@Table(name = "cf_rest_role", uniqueConstraints = @UniqueConstraint(columnNames = "cod", name = "cf_rest_role_u1"))
public class Role extends GenericAuditEntity<Long> {

	private static final long serialVersionUID = 5036881311288942993L;

	@Column
	@NotNull
	private String cod;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRole> permissions = new ArrayList<>();

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRole> users = new ArrayList<>();

}
