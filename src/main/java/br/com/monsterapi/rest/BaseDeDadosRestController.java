package br.com.monsterapi.rest;

import static br.com.monsterapi.utils.ConstantesPaths.BASE_DE_DADOS;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.monsterapi.dto.persist.BaseDeDadosPersist;
import br.com.monsterapi.dto.response.BaseDeDadosResponse;
import br.com.monsterapi.dto.response.page.PageResponse;
import br.com.monsterapi.service.BaseDeDadosService;
import br.com.monsterapi.utils.ConstantesTags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 22/09/2018
 *
 * */

@RestController
@Api(value = BASE_DE_DADOS, produces = MediaType.APPLICATION_JSON_VALUE, tags = {ConstantesTags.TAG_BASE_DE_DADOS})
@RequestMapping(value = BASE_DE_DADOS, produces = MediaType.APPLICATION_JSON_VALUE)
public class BaseDeDadosRestController {

	private static final String AUTH_HEADER = "accessToken";
	
	@Autowired
	private BaseDeDadosService baseDeDadosService;
	
	@PreAuthorize("hasAnyRole('SUPERUSUARIO')")
	@ApiOperation(value = "Cadastro de base de dados. ", notes = "Endpoint para cadastro de base de dados", response = BaseDeDadosResponse.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> salvarLogin(
			@Valid 
			@RequestBody 
			BaseDeDadosPersist baseDeDadosPersist){
		
		BaseDeDadosResponse retorno = this.baseDeDadosService.salvar(baseDeDadosPersist);
		
		return ResponseEntity.ok(retorno);
	}
	
	@PreAuthorize("hasAnyRole('SUPERUSUARIO')")
	@ApiOperation(value = "Consulta uma base de dados. ", notes = "Endpoint para consulta de base de dados por token.", response = BaseDeDadosResponse.class)
	@RequestMapping(value = "/busca-por-token", method = RequestMethod.GET)
	public ResponseEntity<?> consultarBaseDeDados(
			HttpServletRequest request){
		
		BaseDeDadosResponse retorno = this.baseDeDadosService.buscarBaseDeDados(request.getHeader(AUTH_HEADER));
		
		return ResponseEntity.ok(retorno);
	}
	
	@PreAuthorize("hasAnyRole('SUPERUSUARIO')")
	@ApiOperation(value = "Consulta todas as bases de dados. ", notes = "Endpoint para consulta todas as bases de dados.", response = PageResponse.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> consultarTodasBasesDeDados(
			Pageable pageable){

		PageResponse retorno = this.baseDeDadosService.listarBasesDeDados(pageable);
		
		return ResponseEntity.ok(retorno);
	}
	

	


}
