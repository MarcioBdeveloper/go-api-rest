package br.com.monsterapi.enums;

import lombok.Getter;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 18/08/2018
 *
 * */


public enum NivelAcesso {

	ROLE_SUPERUSUARIO(1, "ROLE_SUPERUSUARIO"),
	ROLE_ADMINISTRACAO(2, "ROLE_ADMINISTRACAO"),
	ROLE_GERENCIA(3, "ROLE_GERENCIA"),
	ROLE_USUARIO(4, "ROLE_USUARIO"),
	ROLE_MEDICO(5, "ROLE_MEDICO"),
	ROLE_SUPORTE(6, "ROLE_SUPORTE");
	
	@Getter
	private Integer id;
	@Getter
	private String descricao;
	
	NivelAcesso(Integer id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public NivelAcesso convertToEntityAttribute(String descricao) {
		String descriacaoUP = descricao.toUpperCase();
		switch (descriacaoUP) {
		case "SUPERUSUARIO":
			return NivelAcesso.ROLE_SUPERUSUARIO;
		case "ADMINISTRACAO":
			return NivelAcesso.ROLE_ADMINISTRACAO;
		case "GERENCIA":
			return NivelAcesso.ROLE_GERENCIA;
		case "USUARIO":
			return NivelAcesso.ROLE_USUARIO;
		case "MEDICO":
			return NivelAcesso.ROLE_MEDICO;
		case "SUPORTE":
			return NivelAcesso.ROLE_SUPORTE;
		default:
			throw new IllegalArgumentException("Error" + descricao);
		}

	}
	
	public static String convertToEntityAttributeString(Integer id) {

		switch (id) {
		case 1:
			return NivelAcesso.ROLE_SUPERUSUARIO.descricao;
		case 2:
			return NivelAcesso.ROLE_ADMINISTRACAO.descricao;
		case 3:
			return NivelAcesso.ROLE_GERENCIA.descricao;
		case 4:
			return NivelAcesso.ROLE_USUARIO.descricao;
		case 5:
			return NivelAcesso.ROLE_MEDICO.descricao;
		case 6:
			return NivelAcesso.ROLE_SUPORTE.descricao;
		default:
			throw new IllegalArgumentException("Error" + id);
		}

	}
}
