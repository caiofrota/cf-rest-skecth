package com.cftechsol.rest.permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Permission repository.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findByCod(String name);

}
