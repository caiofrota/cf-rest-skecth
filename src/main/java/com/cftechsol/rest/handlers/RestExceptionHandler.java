package com.cftechsol.rest.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cftechsol.rest.exceptions.api.ApiError;

/**
 * Handler to get generic exceptions.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception e) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}

}
