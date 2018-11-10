package br.com.monsterapi.config.security;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.monsterapi.config.ApplicationConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";
	
	@Autowired
    private ApplicationConfig YAMLConfig;
	
	
	/**
	* Obtém o username (email) contido no token JWT.
	*
	* @param token
	* @return String
	*/
	public String getUsernameFromToken(String token) {
		String userName;
		try {
			Claims claims  = getClaimsFromToken(token);
			userName = claims.getSubject();
		} catch (Exception e) {
			userName = null;
		}
		return userName;
	}
	
	/**
	* Retorna a data de expiração de um token JWT.
	*
	* @param token
	* @return Date
	*/
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			Claims claims  = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	
	/**
	* Cria um novo token (refresh).
	*
	* @param token
	* @return String
	*/
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, LocalDate.now());
			refreshedToken = gerarToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
	
	
	/**
	* Gera um novo token JWT contendo os dados (claims) fornecidos.
	* 
	* @param claims
	* @return String
	*/
	private String gerarToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, this.YAMLConfig.getJwt().getSecret()).compact();
	}
	
	

	/**
	* Realiza o parse do token JWT para extrair as informações contidas no
	* corpo dele.
	*
	* @param token
	* @return Claims
	*/
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(this.YAMLConfig.getJwt().getSecret()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	/**
	* Retorna um novo token JWT com base nos dados do usuários.
	*
	* @param userDetails
	* @return String
	*/
	public String obterToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		
		return gerarToken(claims);
	}
	

}
