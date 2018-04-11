package com.cftechsol.rest.exceptions.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class that maps an object of type unique validation error.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Getter
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
