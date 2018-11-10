package br.com.monsterapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.monsterapi.dto.persist.LoginPersist;
import br.com.monsterapi.dto.response.LoginResponse;
import br.com.monsterapi.entities.Login;
import br.com.monsterapi.enums.NivelAcesso;
import br.com.monsterapi.enums.StatusUsuario;
import br.com.monsterapi.repository.LoginRepository;


/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 20/08/2018
 *
 * */


@RunWith(SpringRunner.class)
public class LoginServiceTests {
	
	@InjectMocks
	private LoginService loginServiceMock;
	
	@Mock
	private LoginRepository loginRepository;
	
	private Login login;
	private LoginResponse loginResponse;
	private LoginPersist loginPersist;

	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(this.loginServiceMock, "loginRepository", this.loginRepository);
		
		this.login = new Login();
		this.login.setId(1L);
		this.login.setEmail("Email para login teste");
		this.login.setSenha("1234");
		this.login.setStatus(StatusUsuario.ATIVO.getId());
		this.login.setNivelAcesso(NivelAcesso.ROLE_GERENCIA.getId());
		
		this.loginResponse = new LoginResponse();
		this.loginResponse.setId(1L);
		this.loginResponse.setSenha("123");
		this.loginResponse.setEmail("Email para logi response");
		this.loginResponse.setStatus(StatusUsuario.ATIVO.getId());
		this.loginResponse.setNivelAcesso(NivelAcesso.ROLE_GERENCIA.getId());
		
		this.loginPersist = new LoginPersist();
		this.loginPersist.setEmail("UEmail para logi Persist");
		this.loginPersist.setSenha("123");
		this.loginPersist.setStatus(StatusUsuario.ATIVO.getId());
		this.loginPersist.setNivelAcesso(NivelAcesso.ROLE_GERENCIA.getId());
	}
	
	@Test
	public void salvarTest() {

    	when(this.loginRepository.save(Mockito.any(Login.class))).thenReturn(this.login);
    	
    	LoginResponse retorno = this.loginServiceMock.salvar(this.loginPersist);
    	
    	assertNotNull(retorno);
    	assertEquals(this.loginResponse.getStatus(), retorno.getStatus());
    	assertEquals(this.loginResponse.getNivelAcesso(), retorno.getNivelAcesso());
		
	}

}
