package br.com.monsterapi.dto.response;

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
@ApiModel(description = "Representação o objeto de resposta.")
public class BaseDeDadosResponse {

	@ApiModelProperty(value = "Codigo de identificação do Login na base de dados.", name = "id", position = 1)
	private Long id;
	
	@ApiModelProperty(value = "Email utilizado para logar na aplicação.", name = "email", position = 2)
	private String email;
	
	@ApiModelProperty(value = "Servidor onde esta a base do cliente.", name = "servidor", position = 3)
	private String servidor;
	
	@ApiModelProperty(value = "Nome do schema de dados do cliente.", name = "nomeSchema", position = 4)
	private String nomeSchema;
	
	@ApiModelProperty(value = "Token de acesso do cliente", name = "tokenCliente", position = 5)
	private String tokenCliente;
}
