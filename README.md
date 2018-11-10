# Monster API
##Spring Boot, Security, Data, Rest, RestAssured, jUnit, Swagger, Liquibase, MultiTenancy
###Tudo isso junto e misturado numa gambiarra monster kkkkkkkkk e muito Go Horse.

#1 - Começe criando uma base de dados com nome db_monster (mas pode ser da sua preferencia, é só ajustar a configuração) 
#2 - Atualize os tokens para os logins "admin@email.com" e "admin2@email.com", o liquibase esta inserindo um valor aleatorio 

#Para consultar o token de um login de acordo com um dos logins sitados:
#localhost:8080/auth
json
{
	"email" : "admin@email.com",
	"senha" : "123@123"
}
Response
{
    "data": {
        "token": "seu token"
    },
    "errors": null
}
#Atualize a coluna token_cliente na tabela base_de_dados.
#Os passos demonstrado são base para o uso dos tokens JWT e do Multi Tenancy

swagger: http://localhost:8080/swagger-ui.html

