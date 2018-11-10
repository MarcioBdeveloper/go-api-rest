package br.com.monsterapi.config.security;

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
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.monsterapi.config.multitenancy.TenantLocalStorage;
import br.com.monsterapi.dto.response.BaseDeDadosResponse;
import br.com.monsterapi.service.BaseDeDadosService;
import br.com.monsterapi.service.security.JwtUserDetailsServiceImpl;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

	private static final String AUTH_HEADER = "accessToken";
	
	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsService;
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired 
	private BaseDeDadosService baseDeDadosService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader(AUTH_HEADER);
		if (token != null) {
			String username = this.jwtToken.getUsernameFromToken(token);
			
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
				UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			TenantLocalStorage.setTenantName("db_monster");
			BaseDeDadosResponse base = this.baseDeDadosService.buscarBaseDeDados(token);
	        TenantLocalStorage.setTenantName(base.getNomeSchema());
		}
		filterChain.doFilter(request, response);
	}

}
