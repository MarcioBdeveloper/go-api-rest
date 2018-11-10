package br.com.monsterapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.monsterapi.converters.GenericConvert;
import br.com.monsterapi.dto.persist.BaseDeDadosPersist;
import br.com.monsterapi.dto.response.BaseDeDadosResponse;
import br.com.monsterapi.dto.response.page.PageResponse;
import br.com.monsterapi.entities.BaseDeDados;
import br.com.monsterapi.repository.BaseDeDadosRepository;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 22/09/2018
 * */


@Service
public class BaseDeDadosService {

	@Autowired
	private BaseDeDadosRepository baseDeDadosRepository;
	
	public BaseDeDadosResponse salvar(BaseDeDadosPersist baseDeDadosPersist) {
		BaseDeDados baseDeDados =  GenericConvert.convertModelMapper(baseDeDadosPersist, BaseDeDados.class);
		return GenericConvert.convertModelMapper(this.baseDeDadosRepository.save(baseDeDados), BaseDeDadosResponse.class);
	}
	
	public BaseDeDadosResponse buscarBaseDeDados(String token) {
		return GenericConvert.convertModelMapper(this.baseDeDadosRepository.findByTokenCliente(token), BaseDeDadosResponse.class);
	}
	
	public PageResponse<BaseDeDados> listarBasesDeDados(Pageable pageable){
		 Page<BaseDeDados> bases =  this.baseDeDadosRepository.findAll(pageable);
		return new PageResponse<>(bases.getContent() , bases.getTotalElements(),bases.getNumber(), bases.getTotalPages());
	}
	

	
}
