package com.cftechsol.rest.exceptions;

import lombok.Getter;

/**
 * Non unique exception.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0
 * @since 1.0
 */
@Getter
public class NonUniqueException extends Exception {

	private static final long serialVersionUID = 8913908467686536761L;
	
	private String object;
	private String[] keys;
	private String[] values;

	public NonUniqueException(String object, String[] keys, String[] values) {
		super("must be unique");
		this.object = object;
		this.keys = keys;
		this.values = values;
	}

}
