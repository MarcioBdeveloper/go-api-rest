package br.com.monsterapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 19/08/2018
 *
 * */

@Data
@ApiModel(description = "Representação o objeto de requisição.")
public class LoginRequest {
	
	@ApiModelProperty(value = "Codigo de identificação do Login na base de dados.", name = "id", position = 1)
	private Long id;
	
	@ApiModelProperty(value = "Usuario utilizado para logar na aplicação.", name = "usuario", position = 2)
	private String usuario;
	
	@ApiModelProperty(value = "Senha utilizada para logar na aplicação.", name = "senha", position = 3)
	private String senha;
	
	@ApiModelProperty(value = "Status: 1 - Ativo, 2 - Inativo, 3 - Bloqueado.", name = "status", position = 4)
	private Integer status;
	
	@ApiModelProperty(value = "Nivel de Acesso: 1 - Super usuario, 2 - Administração, 3 - Gerência, 4 - Usuario, 5 - Medico, 6 - Suporte.", name = "nivelAcesso", position = 5)
	private Integer nivelAcesso;

}
