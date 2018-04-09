package com.cftechsol.rest.roles;

import org.springframework.stereotype.Service;

import com.cftechsol.rest.exceptions.NonUniqueException;
import com.cftechsol.rest.services.GenericService;

/**
 * Role service.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Service
public class RoleService extends GenericService<RoleRepository, Role, Long> {

	/**
	 * Save a role.
	 * 
	 * @param object
	 *            Role to save.
	 * @return Object saved.
	 * @throws Exception
	 */
	public Role save(Role object) throws Exception {
		if (this.getRepository().findByCod(object.getCod()) != null) {
			throw new NonUniqueException(object.getClass().getSimpleName(), new String[] { "cod" },
					new String[] { object.getCod() });
		}
		return super.save(object);
	}

	/**
	 * Save a role.
	 * 
	 * @param object
	 *            Role to save.
	 * @param id
	 *            User modification.
	 * @return Object saved.
	 * @throws Exception
	 */
	public Role save(Role object, long id) throws Exception {
		if (this.getRepository().findByCod(object.getCod()) != null) {
			throw new NonUniqueException(object.getClass().getSimpleName(), new String[] { "cod" },
					new String[] { object.getCod() });
		}
		return super.save(object, id);
	}

}
