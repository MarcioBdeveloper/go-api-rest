package br.com.monsterapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.monsterapi.converters.GenericConvert;
import br.com.monsterapi.dto.persist.LoginPersist;
import br.com.monsterapi.dto.response.LoginResponse;
import br.com.monsterapi.dto.response.page.PageResponse;
import br.com.monsterapi.dto.update.LoginUpdate;
import br.com.monsterapi.entities.Login;
import br.com.monsterapi.repository.LoginRepository;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 18/08/2018
 * */


@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public LoginResponse salvar(LoginPersist loginPersist) {
		Login login =  GenericConvert.convertModelMapper(loginPersist, Login.class);
		return GenericConvert.convertModelMapper(this.loginRepository.save(login), LoginResponse.class);
	}
	
	public LoginResponse buscarLogin(Long id) {
		return GenericConvert.convertModelMapper(this.loginRepository.findById(id).get(), LoginResponse.class) ;
	}
	
	public PageResponse<Login> listarLogin(Pageable pageable){
		
		Page<Login> logins = this.loginRepository.findAll(pageable);
		
		return new PageResponse<>(logins.getContent() , logins.getTotalElements(), logins.getNumber(), logins.getTotalPages());
	}
	
	public void deletarLogin(Login login) {
		this.loginRepository.delete(login);
	}
	
	public LoginResponse alterarLogin(LoginUpdate loginUpdate) {
		Login login = this.loginRepository.getOne(loginUpdate.getId());
		if(login != null) {
			login = this.loginRepository.save(GenericConvert.convertModelMapper(loginUpdate , Login.class));
		}
		return GenericConvert.convertModelMapper(login, LoginResponse.class);
	}
	
	public Login bucarPorEmailEsenha(String email, String senha) {
		return this.loginRepository.findByEmailAndSenha(email, senha);
	}
	
	public Login bucarPorEmail(String email) {
		return this.loginRepository.findByEmail(email);
	}
	
}
