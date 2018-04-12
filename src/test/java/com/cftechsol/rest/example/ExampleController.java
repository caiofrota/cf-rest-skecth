package com.cftechsol.rest.example;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cftechsol.rest.controllers.GenericController;
import com.cftechsol.rest.exceptions.NonUniqueException;

/**
 * Example controller to execute test.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController extends GenericController<ExampleService, ExampleEntity, Long> {

	@GetMapping(path="/filter")
	public ExampleEntity findByName(String name) {
		return this.getService().findByName(name);
	}
	
	@GetMapping(path="/exception")
	public void exeption() throws Exception {
		throw new Exception("Example Message");
	}
	
	@GetMapping(path="/nonUniqueException")
	public void nonUniqueException() throws Exception {
		throw new NonUniqueException("Object", new String[] { "keys" }, new String[] { "values" });
	}
	
	@PostMapping(path="/validationException")
	public void validationException(@RequestBody ExampleEntity object) throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		throw new ConstraintViolationException(validator.validate(object));
	}
	
}
