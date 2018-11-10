package br.com.monsterapi.integration;

import java.util.Base64;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.monsterapi.entities.Login;
import io.restassured.RestAssured;
import io.restassured.http.Header;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginRestControllerIntegrationTest {
	
	private String clientBasicAuthCredentials;
	
	@LocalServerPort
	private int port;
	
	private Login login;
	
	@Before
	public void setup() {
		RestAssured.port = this.port;
        RestAssured.basePath = "/api";
        this.clientBasicAuthCredentials = Base64.getEncoder().encodeToString("my_client_username:my_client_password".getBytes());
        
        this.login = new Login();
        this.login.setId(1L);
        this.login.setEmail("admin@email.com");
        this.login.setSenha("1234");
        this.login.setNivelAcesso(1);
        this.login.setStatus(1);
	}
	
	@After
	public void exit() {
		RestAssured.reset();
	}
	
	@Test
    public void salvarLoginIntegrationTest() {
		RestAssured.given()
		.header(new Header("Authorization", "Basic " + this.clientBasicAuthCredentials))
		.when().body(this.login)
		.get("/logins")
		.then()
		.statusCode(200);
    }


  
}
