package br.com.monsterapi.rest;

import static br.com.monsterapi.utils.ConstantesPaths.LOGINS;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.monsterapi.dto.persist.LoginPersist;
import br.com.monsterapi.dto.response.LoginResponse;
import br.com.monsterapi.dto.response.page.PageResponse;
import br.com.monsterapi.dto.update.LoginUpdate;
import br.com.monsterapi.service.LoginService;
import br.com.monsterapi.utils.ConstantesTags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 18/08/2018
 *
 * */

@RestController
@Api(value = LOGINS, produces = MediaType.APPLICATION_JSON_VALUE, tags = {ConstantesTags.TAG_LOGIN})
@RequestMapping(value = LOGINS, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginRestController {

	
	@Autowired
	private LoginService loginService;
	
	@PreAuthorize("hasAnyRole('SUPERUSUARIO', 'ADMINISTRACAO', 'GERENCIA')")
	@ApiOperation(value = "Cadastro de login. ", notes = "Endpoint para cadastro de login", response = LoginResponse.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> salvarLogin( 
			@Valid 
			@RequestBody 
			LoginPersist loginPersist){
		
		LoginResponse retorno = this.loginService.salvar(loginPersist);
		
		return ResponseEntity.ok(retorno);
	}
	
	@PreAuthorize("hasAnyRole('SUPERUSUARIO', 'ADMINISTRACAO', 'GERENCIA')")
	@ApiOperation(value = "Consulta um login. ", notes = "Endpoint para consulta de login por id.", response = LoginResponse.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarLogin(
			@PathVariable("id") 
			Long id){
		
		LoginResponse retorno = this.loginService.buscarLogin(id);
		
		return ResponseEntity.ok(retorno);
	}
	
	@PreAuthorize("hasAnyRole('SUPERUSUARIO', 'ADMINISTRACAO', 'GERENCIA')")
	@ApiOperation(value = "Listar logins. ", notes = "Endpoint para listagem de logins.", response = PageResponse.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listarLogin(
			Pageable pageable){
		
		PageResponse retorno = this.loginService.listarLogin(pageable);
		
		return ResponseEntity.ok(retorno);
	}
	
	@PreAuthorize("hasAnyRole('SUPERUSUARIO', 'ADMINISTRACAO', 'GERENCIA')")
	@ApiOperation(value = "Alterar login. ", notes = "Endpoint para alterar login da API.", response = LoginUpdate.class)
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> alterarLogin(
			@Valid 
			@RequestBody 
			LoginUpdate loginUpdate){
		
		LoginResponse retorno = this.loginService.alterarLogin(loginUpdate);
		
		return ResponseEntity.ok(retorno);
		
	}

}
