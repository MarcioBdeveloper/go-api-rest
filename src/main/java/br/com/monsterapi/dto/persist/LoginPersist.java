package br.com.monsterapi.dto.persist;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 18/08/2018
 *
 * */


@Data
@ApiModel(description = "Representação o objeto a ser persistido.")
public class LoginPersist implements Serializable{
	
	private static final long serialVersionUID = -3132025349803815895L;
	
	@NotNull
    @ApiModelProperty(value = "Email utilizado para logar na aplicação.", name = "email", required = true, position = 1)
	private String email;
	
	@NotNull
    @ApiModelProperty(value = "Senha utilizada para logar na aplicação.", name = "senha", required = true, position = 2)
	private String senha;
	
	@NotNull
    @ApiModelProperty(value = "Status: 1 - Ativo, 2 - Inativo, 3 - Bloqueado.", name = "status", required = true, position = 3)
	private Integer status;
	
	@NotNull
    @ApiModelProperty(value = "Nivel de Acesso: 1 - Super usuario, 2 - Administração, 3 - Gerência, 4 - Usuario, 5 - Medico, 6 - Suporte.", name = "nivelAcesso", required = true, position = 3)
	private Integer nivelAcesso;
}
