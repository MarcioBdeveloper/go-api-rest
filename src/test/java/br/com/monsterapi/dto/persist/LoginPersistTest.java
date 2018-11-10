package br.com.monsterapi.dto.persist;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.monsterapi.enums.NivelAcesso;
import br.com.monsterapi.enums.StatusUsuario;
import junit.framework.TestCase;

/***
 * 
 * Created by Márcio Barbosa - email: marciobarbosamobile@gmail.com
 * 20/08/2018
 *
 * */


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginPersistTest {

	@Autowired
    private LocalValidatorFactoryBean validator;

	 @Test
     public void testAtributosValidos() {

		LoginPersist loginPersist = new LoginPersist();
		loginPersist.setEmail("TEste");
		loginPersist.setSenha("TesteSenha");
		loginPersist.setNivelAcesso(NivelAcesso.ROLE_ADMINISTRACAO.getId());
		loginPersist.setStatus(StatusUsuario.ATIVO.getId());

		Set<ConstraintViolation<LoginPersist>> violations = validator.validate(loginPersist);
		TestCase.assertTrue(violations.size() == 0);
          
     }
	 
	 @Test
     public void testAtributosInvalidos() {

		LoginPersist loginPersist = new LoginPersist();
		loginPersist.setEmail(null);
		loginPersist.setSenha(null);
		loginPersist.setNivelAcesso(null);
		loginPersist.setStatus(null);

		Set<ConstraintViolation<LoginPersist>> violations = validator.validate(loginPersist);
		TestCase.assertTrue(violations.size() == 4);
          
     }

	
}
