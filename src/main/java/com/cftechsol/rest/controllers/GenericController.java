package com.cftechsol.rest.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cftechsol.rest.services.GenericService;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Generic Controller with common methods to accelerate the creation of
 * services.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 *
 * @param <S>
 *            Service
 * @param <E>
 *            Entity
 * @param <PK>
 *            Primary key
 */
@Getter
@NoArgsConstructor
public class GenericController<S extends GenericService<? extends JpaRepository<E, PK>, E, PK>, E, PK> {

	@Autowired
	private S service;

	private boolean secured;
	private String rolePrefix;

	public GenericController(boolean secured, String rolePrefix) {
		this.secured = secured;
		this.rolePrefix = rolePrefix;
	}

	@GetMapping
	public List<E> findAll() {
		if (secured) {
			hasAnyRole(rolePrefix + "FIND_ALL");
		}
		return service.findAll();
	}

	@GetMapping(path = "/id/{id}")
	public E findById(@PathVariable PK id) {
		if (secured) {
			hasAnyRole(rolePrefix + "FIND_BY_ID");
		}
		return service.findById(id);
	}

	@PostMapping
	public E save(@RequestBody E object) throws Exception {
		if (secured) {
			hasAnyRole(rolePrefix + "SAVE");
		}
		return service.save(object);
	}

	@DeleteMapping
	public void delete(@RequestParam PK id) throws Exception {
		if (secured) {
			hasAnyRole(rolePrefix + "DELETE");
		}
		service.delete(id);
	}

	public static void hasAnyRole(String... roles) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
			return;
		}
		long count = 0;
		for (String role : roles) {
			count++;
			if (authorities.contains(new SimpleGrantedAuthority(role))) {
				return;
			} else {
				if (count == roles.length) {
					throw new AccessDeniedException("Access is denied");
				}
			}
		}
	}

	public static void hasAllRoles(String... roles) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
			return;
		}
		for (String role : roles) {
			if (authorities.contains(new SimpleGrantedAuthority(role))) {
				return;
			} else {
				throw new AccessDeniedException("Access is denied");
			}
		}
	}

}
