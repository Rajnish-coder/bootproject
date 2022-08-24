package com.zee.zee5app.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zee.zee5app.security.services.UserDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// do we need to parse the token ?
		
		try {
		String jwt = parseJwt(request);
		if(jwt!=null && jwtUtils.validateJwtToken(jwt)) {
			String username = jwtUtils.getUserNameFromJwtToken(jwt);
			// can we retrieve the user details or not?
			// 
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
			
			// after validating the token, all the user-details will be dumped to upa token.
			// @Preauthorize will check for user authorities from this token.
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			// 
		}}
		catch(Exception e) {
			logger.error(e.getMessage());
			logger.error("cannot set user authentication {}",e);
		}
			
			//
		filterChain.doFilter(request, response);// internally it will call next filter / Servlet(DS).
		}
		
		
		

	// extracting jwt token
	
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		// jwt toke n==> header(authorization header)
		
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());// 7 th place onwards we will get ur actual token
			
		}
		return null;
		
	}

}
