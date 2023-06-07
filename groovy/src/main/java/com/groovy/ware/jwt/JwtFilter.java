package com.groovy.ware.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;


@Component
public class JwtFilter extends OncePerRequestFilter {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String BEARER_PREFIX = "Bearer "; 
	
	private final TokenProvider tokenProvider;
	
	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = resolveToken(request);
		
		try {
					
			if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				Authentication authentication = tokenProvider.getAuthentication(jwt);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}		
		
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			
		} catch (ExpiredJwtException e) {
		
		} catch (UnsupportedJwtException e) {
	
		} catch (IllegalArgumentException e) {
	
		} 
	
		filterChain.doFilter(request, response);
		
	
	}

	private String resolveToken(HttpServletRequest request) {
		
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(7);
		}
		
		return null;
	}



}
