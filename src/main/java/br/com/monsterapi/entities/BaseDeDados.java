package br.com.monsterapi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 22/09/2018
 *
 * */


@Data
@Entity
@Table(name = "base_de_dados")
public class BaseDeDados implements Serializable {


	private static final long serialVersionUID = -4984191980895224721L;
	
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name = "id_base_de_dados")
	private Long id;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@Column(name = "servidor", nullable = false, length = 50)
	private String servidor;
	
	@Column(name = "nome_schema", nullable = false, length = 50)
	private String nomeSchema;
	
	@Column(name = "token_cliente", nullable = false, length = 250)
	private String tokenCliente;
}
