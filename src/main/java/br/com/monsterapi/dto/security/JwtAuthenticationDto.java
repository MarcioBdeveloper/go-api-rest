package br.com.monsterapi.dto.security;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@SuppressWarnings("deprecation")
public class JwtAuthenticationDto {

	@NotEmpty(message = "Email não pode ser vazio.")
	@Email(message = "Email inválido.")
	private String email;
	@NotEmpty(message = "Senha não pode ser vazia.")
	private String senha;
	
}
