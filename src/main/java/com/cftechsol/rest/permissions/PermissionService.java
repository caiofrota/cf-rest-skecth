package com.cftechsol.rest.permissions;

import org.springframework.stereotype.Service;

import com.cftechsol.rest.exceptions.NonUniqueException;
import com.cftechsol.rest.services.GenericService;

/**
 * Permission service.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Service
public class PermissionService extends GenericService<PermissionRepository, Permission, Long> {

	/**
	 * Save a permission.
	 * 
	 * @param object
	 *            Permission to save.
	 * @return Object saved.
	 * @throws Exception
	 */
	public Permission save(Permission object) throws Exception {
		if (this.getRepository().findByCod(object.getCod()) != null) {
			throw new NonUniqueException(object.getClass().getSimpleName(), new String[] { "cod" },
					new String[] { object.getCod() });
		}
		return super.save(object);
	}

	/**
	 * Save a permission.
	 * 
	 * @param object
	 *            Permission to save.
	 * @param id
	 *            User modification.
	 * @return Object saved.
	 * @throws Exception
	 */
	public Permission save(Permission object, long id) throws Exception {
		if (this.getRepository().findByCod(object.getCod()) != null) {
			throw new NonUniqueException(object.getClass().getSimpleName(), new String[] { "cod" },
					new String[] { object.getCod() });
		}
		return super.save(object, id);
	}

}
