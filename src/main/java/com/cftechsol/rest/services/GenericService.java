package com.cftechsol.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import lombok.Getter;

/**
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0
 * @since 1.0
 *
 * @param <R>
 *            extends JpaRepository<E, PK>
 * @param <E>
 *            Entity
 * @param <PK>
 *            Primary key
 */
@Getter
public class GenericService<R extends JpaRepository<E, PK>, E, PK> {

	@Autowired
	private R repository;

	/**
	 * Find all objects from database.
	 * 
	 * @return List of objects.
	 */
	public List<E> findAll() {
		return this.repository.findAll();
	}

	/**
	 * Find one object by primary key.
	 * 
	 * @param id
	 *            Primary key.
	 * @return Entity.
	 */
	public E findById(PK id) {
		return this.repository.findById(id).get();
	}

	/**
	 * Save an object.
	 * 
	 * @param object
	 *            Object to save.
	 * @return Object saved.
	 * @throws Exception
	 */
	public E save(E object) throws Exception {
		return this.repository.save(object);
	}

	/**
	 * Delete one object by primary key.
	 * 
	 * @param id
	 *            Object primary key to delete.
	 * @throws Exception
	 */
	public void delete(PK id) throws Exception {
		this.repository.deleteById(id);
	}

}
