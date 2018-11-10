package br.com.monsterapi.entities.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.monsterapi.entities.Login;
import br.com.monsterapi.enums.NivelAcesso;

public class JwtUserFactory {

	
	private JwtUserFactory() {
		
	}
	
	/**
	* Converte e gera um LoginSecurity com base nos dados de um login.
	*
	* @param login
	* @return LoginSecurity
	*/
	public static LoginSecurity create(Login login) {
		return new LoginSecurity(
				login.getId(), login.getEmail(), login.getSenha(), 
				mapToGrandAuthorities(NivelAcesso.convertToEntityAttributeString(login.getNivelAcesso())));
	}
	
	/**
	* Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	*
	* @param nivelAcesso
	* @return List<GrantedAuthority>
	*/
	private static List<GrantedAuthority> mapToGrandAuthorities(String nivelAcesso){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(nivelAcesso));
		return authorities;
	}
	
}
