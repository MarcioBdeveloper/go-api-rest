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
 * 18/08/2018
 *
 * */


@Data
@Entity
@Table(name = "login")
public class Login implements Serializable {
	

	private static final long serialVersionUID = -1874310370109518177L;
	
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name = "id_login")
	private Long id;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@Column(name = "senha", nullable = false, length = 100)
	private String senha;
	
    @Column(name = "status", columnDefinition = "smallint", nullable = false)
	private Integer status;
    
    @Column(name = "nivel_acesso", columnDefinition = "smallint", nullable = false)
	private Integer nivelAcesso;
}
