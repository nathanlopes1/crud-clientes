# Sistema de Clientes e Pedidos

Sistema CRUD desenvolvido em Java com integração ao banco de dados MySQL e API externa ViaCEP.

## Tecnologias Utilizadas
- Java
- Maven
- Hibernate/JPA
- MySQL
- API ViaCEP

## Como Rodar o Projeto

### Pré-requisitos
- Java JDK instalado
- MySQL instalado
- IntelliJ IDEA

### Configuração do Banco de Dados
1. Instale o MySQL
2. Crie o banco de dados:
```sql
CREATE DATABASE crud_clientes;

### Configuração do .env
Crie um arquivo .env na raiz do projeto com:

DB_HOST=localhost
DB_PORT=3306
DB_NAME=crud_clientes
DB_USER=root
DB_PASSWORD=sua_senha

### Executando o Projeto
1. Clone o repositório
2. Abra no IntelliJ IDEA
3. Aguarde o Maven baixar as dependências
4. Execute a classe Main.java

## Funcionalidades
- Cadastrar, listar, atualizar e deletar Clientes
- Cadastrar, listar, atualizar e deletar Pedidos
- Busca automática de cidade e estado pelo CEP via ViaCEP
- Dados salvos no banco MySQL