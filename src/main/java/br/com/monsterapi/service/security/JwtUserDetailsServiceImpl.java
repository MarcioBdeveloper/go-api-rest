package br.com.monsterapi.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.monsterapi.entities.Login;
import br.com.monsterapi.entities.security.JwtUserFactory;
import br.com.monsterapi.service.LoginService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login = this.loginService.bucarPorEmail(username);
		if(login != null) {
			return JwtUserFactory.create(login);
		}
		throw new UsernameNotFoundException("Login não encontrado.");
	}

}
