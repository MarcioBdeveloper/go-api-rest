# Monster API

## Tecnologias utilizadas
> Spring Boot, Security, Data, Rest, RestAssured, jUnit, Swagger, Liquibase, MultiTenancy

## Passos iniciais 
> 1 - Começe criando uma base de dados com nome db_monster (mas pode ser da sua preferencia, é só ajustar a configuração) 
> 2 - Inicie a aplicação
> 3 - Atualize os tokens para os logins "admin@email.com" e "admin2@email.com", o liquibase esta inserindo um valor aleatorio 
> 4 - Para consultar o token de um login de acordo com um dos logins sitados:
localhost:9000/auth

json<br>
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

> Após consultar o token Atualize a coluna token_cliente na tabela base_de_dados.
> Os passos demonstrado são base para o uso dos tokens JWT e do Multi Tenancy

Para uso do token deve ser criado um header de nome 'accessToken' e o valor é o token que deseja usar.

swagger: http://localhost:9000/swagger-ui.html

