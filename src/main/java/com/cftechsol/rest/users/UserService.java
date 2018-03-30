package com.cftechsol.rest.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cftechsol.rest.exceptions.NonUniqueException;
import com.cftechsol.rest.services.GenericService;

/**
 * User service.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserService extends GenericService<UserRepository, User, Long> {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	/**
	 * Save an user.
	 * 
	 * @param object
	 *            User to save.
	 * @return Object saved.
	 * @throws Exception
	 */
	public User save(User object) throws Exception {
		if (this.getRepository().findByEmail(object.getEmail()) != null) {
			throw new NonUniqueException(object.getClass().getSimpleName(), new String[] { "email" },
					new String[] { object.getEmail() });
		}
		if (object.getPassword() != null) {
			object.setPassword(passwordEncoder.encode(object.getPassword()));
		}
		return this.getRepository().save(object);
	}

}
