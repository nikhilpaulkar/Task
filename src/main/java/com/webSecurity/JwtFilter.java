package com.webSecurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.serviceImpl.UsersServiceImpl;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
@Component
public class JwtFilter extends OncePerRequestFilter
{
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
   @Autowired
   private UsersServiceImpl userServiceImpl;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		  //get  token
		final  String requestToken=request.getHeader("Authorization");
		//Berer 47698njik
		

		String email=null;
		String token=null;
		if(requestToken!=null && requestToken.startsWith("Bearer "))
		{
			 token= requestToken.substring(7);
			 
		try
        {
			 email=this.jwtTokenUtil.getEmailFromToken(token);
        }
		catch(IllegalArgumentException e)
		{
			System.out.println("unable to get token");
		}
		catch(ExpiredJwtException e)
		{
			System.out.println("jwt token has expired");
		}
		catch(MalformedJwtException e)
		{
			System.out.println("invalid jwt");
		}
		}else
		{
			System.out.println("jwt token does not begin with bearer");
		}

		// token get now validate token
		if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userdetails =this.userServiceImpl.loadUserByUsername(email);

			if(this.jwtTokenUtil.validateToken(token, userdetails))
			{
				UsernamePasswordAuthenticationToken usernamepasswordautheticationtoken= new UsernamePasswordAuthenticationToken(userdetails,token,null);
				usernamepasswordautheticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamepasswordautheticationtoken);
			}
		}
		filterChain.doFilter(request, response);
		{
		}
		}

	}


