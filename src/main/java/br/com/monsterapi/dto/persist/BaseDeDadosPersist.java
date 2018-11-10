package br.com.monsterapi.dto.persist;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 22/09/2018
 *
 * */


@Data
@ApiModel(description = "Representação o objeto a ser persistido.")
public class BaseDeDadosPersist implements Serializable{
	
	private static final long serialVersionUID = -3132025349803815895L;

	@NotNull
	@ApiModelProperty(value = "Email utilizado para logar na aplicação.", name = "email", position = 1)
	private String email;
	
	@NotNull
	@ApiModelProperty(value = "Servidor onde esta a base do cliente.", name = "servidor", position = 2)
	private String servidor;
	
	@NotNull
	@ApiModelProperty(value = "Nome do schema de dados do cliente.", name = "nomeSchema", position = 3)
	private String nomeSchema;
	
	@NotNull
	@ApiModelProperty(value = "Token de acesso do cliente", name = "tokenCliente", position = 4)
	private String tokenCliente;
}
