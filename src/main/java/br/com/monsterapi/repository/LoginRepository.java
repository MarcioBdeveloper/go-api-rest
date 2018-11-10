package br.com.monsterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.monsterapi.entities.Login;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 18/08/2018
 *
 * */


@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

	Login findByEmail(String email);
	Login findByEmailAndSenha(String email, String senha);
	
}
