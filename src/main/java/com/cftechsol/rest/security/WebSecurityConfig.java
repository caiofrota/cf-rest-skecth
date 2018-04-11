package com.cftechsol.rest.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cftechsol.rest.security.jwt.filters.JWTAuthenticationFilter;
import com.cftechsol.rest.security.jwt.filters.JWTLoginFilter;

/**
 * Web security configuration.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// @formatter:off
	private String USER_BY_USERNAME_QUERY =
			"select u.email    username " + 
			"     , u.password password " +
			"     , u.enabled  enabled  " + 
			"  from user u              " +
			" where u.email = ?         ";
	// @formatter:on

	// @formatter:off
	private String AUTHORITIES_BY_USERNAME_QUERY =
			"select u.email username                    " +
			"     , p.cod   authority                   " +
			"  from permission      p                   " +
			"     , role_permission rp                  " +
			"     , role            r                   " +
			"     , user_role       ur                  " +
			"     , user            u                   " +
			" where rp.permission_id = p.id             " +
			"   and r.id             = rp.permission_id " +
			"   and ur.role_id       = r.id             " +
			"   and u.id             = ur.user_id       " +
			"   and u.email          = ?                ";
	// @formatter:on

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// @formatter:off
		httpSecurity.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers("/admin/**").authenticated().and()
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder)
				.usersByUsernameQuery(USER_BY_USERNAME_QUERY)
				.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
		// @formatter:on
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
