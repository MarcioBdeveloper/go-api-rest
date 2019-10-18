# API Java

## Tecnologias utilizadas
> Spring Boot, Security, Data, Rest<br>
> RestAssured <br>
> jUnit<br>
> Swagger<br>
> Liquibase<br>
> MultiTenancy<br>

## Passos iniciais 
> 1 - Começe criando uma base de dados(MySql) com nome db_apiresttenancy (mas pode ser da sua preferencia, é só ajustar a configuração) <br>
> 2 - Inicie a aplicação<br>
> 3 - Atualize os tokens para os logins "admin@email.com" e "admin2@email.com", o liquibase esta inserindo um valor aleatorio <br>
> 4 - Para consultar o token de um login de acordo com um dos logins sitados utilizando o endpoint e Json abaixo:
POST - localhost:9000/auth

Request json<br>
{<br>
	"email" : "admin@email.com",<br>
	"senha" : "123@123"<br>
}<br>

Response<br>
{<br>
    "data": {<br>
        "token": "seu token"<br>
    },<br>
    "errors": null<br>
}<br>

> Após consultar o token Atualize a coluna token_cliente na tabela base_de_dados.<br>
> Os passos demonstrado são base para o uso dos tokens JWT e do Multi Tenancy

Para uso do token deve ser criado um atributo no header de key 'accessToken' e o value é o token que deseja usar para se autenticar na API.


swagger: http://localhost:9000/swagger-ui.html

> Para utilizar o token no swagger basta clicar no botão 'Authorize' presente no lado direito no inicio do endpoints,
> coloque o token no campo Value e clique em Authorize.

