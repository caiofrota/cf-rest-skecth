package com.cftechsol.rest.permissions;

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
import com.cftechsol.rest.rolepermissions.RolePermission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Permission entity.
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cod", name = "permission_u1"))
public class Permission extends GenericAuditEntity<Long> {

	private static final long serialVersionUID = -514529907411338526L;

	@Column
	@NotNull
	private String cod;

	@OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RolePermission> roles = new ArrayList<>();

}
