# CHALLENGE
Implementation about Vazamento de Dados challenge to GAT enterprise.

# API GAT
A API GAT é uma aplicação para cadastro de usuários e monitoramento de ativos em exposição e vulnerabilidades na rede.
Utilizando o serviço da intelx.io você pode cadastrar um usuário e manter tracks de ativos para cada usuário.

# Requisições
- Server Login: Nesta versão da aplicação apenas as requisições para cadastro ou acompanhamento de ativos para um usuário requerem o email e senha cadastrados para autenticação.

**API SEARCH**
Esse é um endpoint para fazer busca de ativos para monitoramento mantendo um track de pesquisas para investigação. Ao fazer a buscar com um usuário não existente, o endpoint automaticamente cria um usuário e guarda o track com informações de buscar (search).

*POST* /index - Cria e retorna um usuário no sistema.

- BODY PARAMS:
    email: String,
    password: String,
    term: String,
    domain: String,

- RESPONSES:
    200: Retorn Lista de Searchs da última busca ou nulo
    500: Erro interno no server

**Usuários**
Esse é um endpoint para criar e buscar usuários cadastrados no sistema.

*POST* /user - Cria e retorna um usuário no sistema.

- BODY PARAMS:
    email: String,
    password: String.

- RESPONSES:
    200: Retorna usuário ou nulo
    500: Erro interno no server

*GET* /user/{id} - Retorna um usuário cadastrado no sistema. Sem informações de password.

- PATH VARIABLE:
    id: String

- RESPONSES:
    200: Retorna usuário ou nulo
    500: Erro interno no server

*POST* /login - Retorna todas as informações de um usuário cadastrado no sistema.

- BODY PARAMS:
    email: String,
    password: String.

- RESPONSES:
    200: Retorna usuário ou nulo
    500: Erro interno no server

**Ativos**
Esse é um endpoint para buscar ativos cadastrados no sistema.

*GET* /ativo/{id} - Retorna um ativo cadastrado no sistema.

- PATH VARIABLE:
    id: String

- RESPONSES:
    200: Retorna Ativo ou nulo
    500: Erro interno no server

*GET* /ativo/user/{id} - Retorna todos os ativos de um usuário cadastrado no sistema.

- PATH VARIABLE:
    id: String

- RESPONSES:
    200: Retorna Lista de Ativos ou nulo
    500: Erro interno no server

**Tracks**
Esse é um endpoint para buscar tracks cadastrados no sistema.

*GET* /track/{id} - Retorna uma track específica de um usuário cadastrado no sistema.

- PATH VARIABLE:
    id: String

- RESPONSES:
    200: Retorna Track ou nulo
    500: Erro interno no server

*GET* /track/ativos/{id} - Retorna tracks específicas de um usuário cadastrado no sistema.

- PATH VARIABLE:
    id: String

- RESPONSES:
    200: Retorna Lista de Tracks ou nulo
    500: Erro interno no server

**Searchs**
Esse é um endpoint para buscar e retorna searchs cadastrados no sistema.

*GET* /search/{id} - Retorna uma search específica de um usuário cadastrado no sistema.

- PATH VARIABLE:
    id: String

- RESPONSES:
    200: Retorna Search ou nulo
    500: Erro interno no server

*GET* /track/ativos/{id} - Retorna uma searchs específicas de um usuário cadastrado no sistema.

- PATH VARIABLE:
    id: String

- RESPONSES:
    200: Retorna Lista de Searchs ou nulo
    500: Erro interno no server