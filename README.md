# Monster API

## Tecnologias utilizadas
> Spring Boot, Security, Data, Rest, RestAssured, jUnit, Swagger, Liquibase, MultiTenancy

## Passos iniciais 
> 1 - Começe criando uma base de dados com nome db_monster (mas pode ser da sua preferencia, é só ajustar a configuração) 
> 2 - Atualize os tokens para os logins "admin@email.com" e "admin2@email.com", o liquibase esta inserindo um valor aleatorio 

> Para consultar o token de um login de acordo com um dos logins sitados:

> localhost:9000/auth

json.
{.
	"email" : "admin@email.com",.
	"senha" : "123@123".
}.
Response.
{.
    "data": {.
        "token": "seu token".
    },.
    "errors": null.
}.

> Atualize a coluna token_cliente na tabela base_de_dados.
> Os passos demonstrado são base para o uso dos tokens JWT e do Multi Tenancy

Para uso do token deve ser criado um header de nome 'accessToken' e o valor é o token que deseja usar.

swagger: http://localhost:9000/swagger-ui.html

