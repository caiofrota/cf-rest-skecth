package com.cftechsol.rest.handlers;

import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cftechsol.rest.exceptions.api.ApiError;

/**
 * Handler to get custom exceptions.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Order(Ordered.LOWEST_PRECEDENCE - 1000)
@ControllerAdvice
public class RestValidationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.addValidationErrors(e.getConstraintViolations());
		return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}
	
}
