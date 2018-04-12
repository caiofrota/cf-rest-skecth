package com.cftechsol.rest.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cftechsol.rest.permissions.Permission;
import com.cftechsol.rest.permissions.PermissionService;
import com.cftechsol.rest.rolepermissions.RolePermission;
import com.cftechsol.rest.rolepermissions.RolePermissionService;
import com.cftechsol.rest.roles.Role;
import com.cftechsol.rest.roles.RoleService;
import com.cftechsol.rest.security.jwt.filters.UserCredentials;
import com.cftechsol.rest.userroles.UserRole;
import com.cftechsol.rest.userroles.UserRoleService;
import com.cftechsol.rest.users.User;
import com.cftechsol.rest.users.UserService;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Authentication test class.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTest {

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Value("${cf.rest.jwt.expirationTime}")
	private long expirationTime;
	@Value("${cf.rest.jwt.secret}")
	private String secret;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		Collection<Filter> filterCollection = webApplicationContext.getBeansOfType(Filter.class).values();
		Filter[] filters = filterCollection.toArray(new Filter[filterCollection.size()]);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(SecurityMockMvcConfigurers.springSecurity()).addFilters(filters).build();
	}

//	@Test
	public void shouldLogin() throws Exception {
		// Save admin user.
		Permission savedPermission = permissionService.save(new Permission("LOGIN", null), 1l);
		Role savedRole = roleService.save(new Role("LOGIN", null, null), 1l);
		User savedUser = userService.save(new User("shouldlogin@company.com", "password", "User Name", true, null));
		UserRole savedUserRole = userRoleService.save(new UserRole(savedUser, savedRole));
		RolePermission savedRolePermission = rolePermissionService.save(new RolePermission(savedRole, savedPermission));

		Gson gson = new Gson();
		UserCredentials user = new UserCredentials();
		user.setUsername("shouldlogin@company.com");
		user.setPassword("password");
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Origin", "*")
						.content(gson.toJson(user)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.header().exists("Authorization"));
		// @formatter:on

		userRoleService.delete(savedUserRole.getId());
		rolePermissionService.delete(savedRolePermission.getId());
		userService.delete(savedUser.getId());
		roleService.delete(savedRole.getId());
		permissionService.delete(savedPermission.getId());
	}

	@Test
	public void shouldLoginUnauthorizedWithWrongPassword() throws Exception {
		// Save admin user.
		Permission savedPermission = permissionService.save(new Permission("ADMIN", null), 1l);
		Role savedRole = roleService.save(new Role("ADMIN", null, null), 1l);
		User savedUser = userService.save(new User("admin@company.com", "password", "User Name", true, null));
		UserRole savedUserRole = userRoleService.save(new UserRole(savedUser, savedRole));
		RolePermission savedRolePermission = rolePermissionService.save(new RolePermission(savedRole, savedPermission));

		Gson gson = new Gson();
		UserCredentials user = new UserCredentials();
		user.setUsername("admin@company.com");
		user.setPassword("wrongPassword");
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Origin", "*")
						.content(gson.toJson(user)))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
		// @formatter:on

		userRoleService.delete(savedUserRole.getId());
		rolePermissionService.delete(savedRolePermission.getId());
		userService.delete(savedUser.getId());
		roleService.delete(savedRole.getId());
		permissionService.delete(savedPermission.getId());
	}

	@Test
	public void shouldGetUnauthorizedWithExpiredToken() throws Exception {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(() -> {
			return "ADMIN";
		});
		authorities.add(() -> {
			return "OTHER_ROLE";
		});
		String username = "admin@company.com";
		String token = Jwts.builder().claim("authorities", authorities).setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() - 1)).signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/example")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Origin", "*")
						.header("Authorization", "Bearer " + token))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
		// @formatter:on
	}

	@Test
	public void shouldGet500WithInvalidToken() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/example")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Origin", "*")
						.header("Authorization", "Bearer zzz"))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError());
		// @formatter:on
	}

	@Test
	public void shouldGetAccessWithToken() throws Exception {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(() -> {
			return "ADMIN";
		});
		String username = "admin@company.com";
		// @formatter:off
		String token = Jwts.builder().claim("authorities", authorities).setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/example")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Origin", "*")
						.header("Authorization", "Bearer " + token))
				.andExpect(MockMvcResultMatchers.status().isOk());
		// @formatter:on
	}
	
}
