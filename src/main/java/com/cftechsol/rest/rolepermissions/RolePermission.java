package com.cftechsol.rest.rolepermissions;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.cftechsol.rest.entities.GenericAuditEntity;
import com.cftechsol.rest.permissions.Permission;
import com.cftechsol.rest.roles.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Role permission entity.
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
@Table(name = "role_permission")
public class RolePermission extends GenericAuditEntity<RolePermissionPK> {
	
	private static final long serialVersionUID = -4108166483863035209L;

	@EmbeddedId
	private RolePermissionPK id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("roleId")
	@JoinColumn(foreignKey = @ForeignKey(name = "role_permission_fk1"))
	private Role role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("permissionId")
	@JoinColumn(foreignKey = @ForeignKey(name = "role_permission_fk2"))
	private Permission permission;
	
	public RolePermission(Role role, Permission permission) {
		this.role = role;
		this.permission = permission;
		this.id = new RolePermissionPK(role.getId(), permission.getId());
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        RolePermission that = (RolePermission) o;
        return Objects.equals(getId(), that.getId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
