package com.cftechsol.rest.example;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cftechsol.rest.controllers.GenericController;

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
