package com.cftechsol.rest.example;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cftechsol.rest.controllers.GenericController;

/**
 * Controller example with security to execute test.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/admin/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleSecurityController extends GenericController<ExampleService, ExampleEntity, Long> {

	public ExampleSecurityController() {
		super(true, "EXAMPLE_");
	}
	
	@GetMapping(path="/allroles")
	public List<ExampleEntity> mustBeAllAuthorities() {
		if (secured) {
			super.hasAllRoles("EXAMPLE_FIND_ALL", "EXAMPLE_SAVE");
		}
		return super.getService().findAll();
	}
	
}
