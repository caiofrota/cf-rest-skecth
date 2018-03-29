package com.cftechsol.rest.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cftechsol.rest.exceptions.NonUniqueException;
import com.cftechsol.rest.exceptions.api.ApiError;
import com.cftechsol.rest.exceptions.api.ApiUniqueValidationError;

/**
 * Handler to get MySQL exceptions.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Order(Ordered.LOWEST_PRECEDENCE - 1000)
@ControllerAdvice
public class RestCustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NonUniqueException.class)
	protected ResponseEntity<Object> handleUniqueConstraintViolation(NonUniqueException e) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.addSubError(new ApiUniqueValidationError(e.getObject(), e.getKeys(), e.getValues(), e.getMessage()));
		return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}

}
