package com.groovy.ware.jwt;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.dto.TokenDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {

	private static final String AUTHORITIES_KEY = "auth"; 
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;
	private static final String BEARER_TYPE = "bearer";
	
	private final Key key;
	
	private final UserDetailsService userDetailsService;

	
	public TokenProvider(@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.userDetailsService = userDetailsService;
	}
	
	public TokenDto generateToken(EmployeeDto employee) {

		Claims claims = Jwts.claims().setSubject(employee.getEmpId());
		// 권한도 claims에 담기
		List<String> roles = employee.getAuths().stream().map(auth -> auth.getAuth().getAuthName())
				.collect(Collectors.toList());

		claims.put(AUTHORITIES_KEY, roles);
		
		long now = new Date().getTime();
		Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
		

		String accessToken = Jwts.builder()
				.setClaims(claims)
				.setExpiration(accessTokenExpiresIn)
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
		
		return new TokenDto(BEARER_TYPE, employee.getEmpName(), accessToken, accessTokenExpiresIn.getTime());
	}

}
