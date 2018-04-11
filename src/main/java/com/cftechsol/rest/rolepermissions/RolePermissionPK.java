package com.cftechsol.rest.rolepermissions;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Caio Frota
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RolePermissionPK implements Serializable {
	
	private static final long serialVersionUID = -4564260097737679737L;

	@Column(name = "role_id")
	private Long roleId;
	
	@Column(name = "permission_id")
	private Long permissionId;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        RolePermissionPK that = (RolePermissionPK) o;
        return Objects.equals(roleId, that.roleId) && 
               Objects.equals(roleId, that.roleId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleId);
    }
	
}
