package com.cftechsol.rest.security.jwt.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Token authentication service.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0
 * @since 1.0
 */
@Service
public class TokenAuthenticationService {

	private static final long DEF_EXPIRATIONTIME = 1000 * 60 * 60; // 1 hour
	private static final String DEF_SECRET = "CFTechSolutions";

	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";

	private static long expirationTime;
	private static String secret;

	public static void addAuthentication(HttpServletResponse res, String username,
			Collection<? extends GrantedAuthority> authorities) {
		String authoritiesString = null;
		for (GrantedAuthority authority : authorities) {
			if (authoritiesString == null) {
				authoritiesString = "";
			} else {
				authoritiesString += ",";
			}
			authoritiesString += authority.getAuthority();
		}
		String JWT = Jwts.builder().claim("authorities", authorities).setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

		String token = TOKEN_PREFIX + " " + JWT;
		res.addHeader(HEADER_STRING, token);

		try {
			res.getOutputStream().print(token);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Authentication getByToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
		String user = claims.getSubject();
		ArrayList<LinkedHashMap<?, ?>> authoritiesList = (ArrayList<LinkedHashMap<?, ?>>) claims.get("authorities");
		String authorities = null;
		for (LinkedHashMap<?, ?> authority : authoritiesList) {
			if (authorities == null) {
				authorities = "";
			} else {
				authorities += ",";
			}
			authorities += (String) authority.get("authority");
		}
		// @formatter:off
		return user != null
				? new UsernamePasswordAuthenticationToken(user, null,
						AuthorityUtils.commaSeparatedStringToAuthorityList(authorities))
				: null;
		// @formatter:on
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			return getByToken(token);
		}
		return null;
	}

	@Value("${cf.rest.jwt.expirationTime}")
	public void setExpirationTime(String expirationTimeString) {
		try {
			long expirationTime = Long.parseLong(expirationTimeString);
			TokenAuthenticationService.expirationTime = (expirationTime <= 0) ? DEF_EXPIRATIONTIME : expirationTime;
		} catch (Exception e) {
			TokenAuthenticationService.expirationTime = DEF_EXPIRATIONTIME;
		}
	}

	@Value("${cf.rest.jwt.secret}")
	public void setSecret(String secret) {
		TokenAuthenticationService.secret = (secret == null) ? DEF_SECRET : secret;
	}
}
