package br.com.monsterapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.monsterapi.converters.LocalDateTimeConverter;
import lombok.Data;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 18/08/2018
 *
 * */


@Data
@Entity
@Table(name = "log_acesso")
public class LogAcesso {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name = "id_log_acesso")
	private Long id;
	
	@Column(name = "data_acesso", nullable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataAcesso;
	
	@ManyToOne
	@JoinColumn(name="fk_id_login", referencedColumnName = "id_login")
	private Login login;
	
}
