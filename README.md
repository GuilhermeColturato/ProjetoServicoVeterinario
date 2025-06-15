# Desenvolvimento de um Sistema de Gerenciamento de Serviços Veterinários com Autenticação JWT

**Descrição:**
Conjunto de APIs RESTful que compõem um sistema de gerenciamento de serviços veterinários. O sistema será composto por quatro
APIs principais, com autenticação baseada em JWT (JSON Web Token) para garantir segurança no acesso às rotas protegidas.

## Para rodar o sistema
1. Clonar este repositório
2. Ter instalado o MongoDB Compass e criar a conexão "servet"
3. Compilar e executar cada API:
   
    • Primeira para executar: Auth API

    • Segunda para executar: Pessoa API
  
    • Terceira para executar: Animal API
  
    • Quarta para executar: Servico API
  

## Endpoints disponíveis:

- Auth API: localhost:8081/auth/login

- Pessoa API: localhost:8082/pessoa

- Animal API: localhost:8083/animal

- Servico API: localhost:8084/servico


## Como autenticar no postman
- Fazer um post da Auth API para gerar o token
- Vá em Body -> raw
- Mude de text para JSON e insira as informações
  
{

  "login": "admin@gmail.com",
  
  "senha": "123456"
  
}

- Fazer os posts das outras respectivas apis utilizando o token gerado:
  
- Vá em Authorization;
  
- Type: Bearer Token;
  
- Inserir Token;
  
- Vá em Body -> raw;
  
Mude de text para JSON e insira as respectivas informações de cada API:

  • Pessoa API - nome, cpf, telefone, endereço.
  
  • Animal API - nome, especie, raca, idade, pessoaId.
  
  • Servico API - tipo, data, valor, status e animalID.







