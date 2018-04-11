package com.cftechsol.rest.exceptions.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class that maps an object of type validation error.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {

	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	public ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}

}
