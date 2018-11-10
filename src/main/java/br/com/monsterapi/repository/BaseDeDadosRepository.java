package br.com.monsterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.monsterapi.entities.BaseDeDados;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 22/09/2018
 *
 * */


@Repository
public interface BaseDeDadosRepository extends JpaRepository<BaseDeDados, Long>{

	BaseDeDados findByTokenCliente(String token);
}
