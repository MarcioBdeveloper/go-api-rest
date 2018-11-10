package br.com.monsterapi.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.monsterapi.config.security.JwtToken;
import br.com.monsterapi.dto.response.Response;
import br.com.monsterapi.dto.security.JwtAuthenticationDto;
import br.com.monsterapi.dto.security.TokenDto;
import br.com.monsterapi.service.security.JwtUserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsService;
	
	/**
	* Gera e retorna um novo token JWT.
	*
	* @param authenticationDto
	* @param result
	* @return ResponseEntity<Response<TokenDto>>
	* @throws AuthenticationException
	*/
	@PostMapping
	public ResponseEntity<Response<TokenDto>> gerarTokenJwt(
			@Valid @RequestBody 
			JwtAuthenticationDto authenticationDto, 
			BindingResult result) throws AuthenticationException{
		
		Response<TokenDto> resposta = new Response<TokenDto>();
		
		if(result.hasErrors()) {
			log.error("Erro validando lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> resposta.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(resposta);
		}
		
		log.info("Gerando token para o email {}.", authenticationDto.getEmail());
		
		UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(authenticationDto.getEmail());
		
		String token = this.jwtToken.obterToken(userDetails);
		resposta.setData(new TokenDto(token));
		
		return ResponseEntity.ok(resposta);
	}
	
	
}
