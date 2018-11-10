package br.com.monsterapi.enums;

import lombok.Getter;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 18/08/2018
 *
 * */

public enum StatusUsuario {

	ATIVO(1, "Ativo"),
	INATIVO(2, "Inativo"),
	BLOQUEADO(3, "Bloqueado");
	
	@Getter
	private Integer id;
	@Getter
	private String descricao;
	
	StatusUsuario(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public StatusUsuario convertToEntityAttribute(String descricao) {
		String descriacaoUP = descricao.toUpperCase();
		switch (descriacaoUP) {
		case "ATIVO":
			return StatusUsuario.ATIVO;
		case "INATIVO":
			return StatusUsuario.INATIVO;
		case "BLOQUEADO":
			return StatusUsuario.BLOQUEADO;
		default:
			throw new IllegalArgumentException("Error" + descricao);
		}
	}
	

}
