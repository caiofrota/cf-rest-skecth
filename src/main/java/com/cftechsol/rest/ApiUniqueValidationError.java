package com.cftechsol.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class that maps an object of type unique validation error.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiUniqueValidationError extends ApiSubError {

	private String object;
	private String[] fields;
	private Object[] rejectedValues;
	private String message;

	ApiUniqueValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}

}
