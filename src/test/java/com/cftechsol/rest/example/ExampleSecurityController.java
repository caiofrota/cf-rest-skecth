package com.cftechsol.rest.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cftechsol.rest.controllers.GenericController;

@RestController
@RequestMapping(path = "/admin/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleSecurityController extends GenericController<ExampleService, ExampleEntity, Long> {

	public ExampleSecurityController() {
		super(true, "EXAMPLE_");
	}
	
}
