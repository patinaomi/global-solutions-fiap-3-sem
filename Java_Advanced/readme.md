# Global Solutions

Examine e desenvolva uma solução tecnológica que
contribua de maneira significativa e que colabore de forma impactante e viável para a
melhoria dos processos de energia sustentável. A solução Web a ser desenvolvida deve focar
nos meios sustentáveis mencionados, focando nas áreas que podem ser impactadas pela
transição energética. A aplicação deve ser uma API RESTful, construída com os frameworks
Spring/Spring Boot.

## Índice

1. [Sobre o Projeto](#sobre-o-projeto)
2. [Integrantes](#integrantes)
3. [Cronograma de Desenvolvimento](#cronograma-de-desenvolvimento)
4. [Atualização Sprint 2](#atualização-sprint-2)
5. [Como Rodar o Projeto](#como-rodar-o-projeto)
6. [Pré-requisitos](#pré-requisitos)
7. [Modelo Relacional (DER)](#modelo-relacional-der)
8. [Diagrama de Classes](#diagrama-de-classes)
9. [Documentação da API](#documentação-da-api)
10. [Video](#video)

---

## Sobre o Projeto

Este projeto consiste em uma aplicação de gerenciamento para uma clínica odontológica. Ele permite que dentistas, pacientes e clínicas gerenciem consultas, feedbacks, sinistros, formulários detalhados e muito mais. A aplicação utiliza tecnologias como Java, Spring Boot, e um banco de dados relacional para facilitar o gerenciamento eficiente das operações diárias da clínica.

[:arrow_up: voltar para o índice :arrow_up:](#índice)

**Funcionalidades principais:**
- Cadastro de pacientes, dentistas e clínicas.
- Marcação e controle de consultas.
- Gerenciamento de formulários detalhados de pacientes.
- Recepção de feedbacks e controle de sinistros.

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## Atualização Sprint 2
Nesta sprint, foram implementadas melhorias significativas para a experiência do usuário e a escalabilidade do sistema. As principais adições incluem:

-   **Implementação de HATEOAS**: Para facilitar a navegação entre recursos e melhorar a usabilidade da API, foram adicionados links HATEOAS (Hypermedia as the Engine of Application State) nos endpoints. Isso permite que os clientes da API naveguem por diferentes recursos sem precisar conhecer todos os endpoints de antemão.
    
-   **Envio de E-mail com MailSender do Spring Boot**: Ao cadastrar um novo usuário, o sistema agora envia um e-mail de boas-vindas utilizando o MailSender do Spring Boot. Esta funcionalidade melhora a comunicação com os usuários e garante que eles estejam informados sobre o cadastro com sucesso.
    

Essas implementações visam não apenas melhorar a usabilidade e a experiência do usuário, mas também aumentar a modularidade e a manutenibilidade do código.
## Como Rodar o Projeto

Para rodar a aplicação Java Spring Boot, siga as instruções abaixo:
 #### Pré-requisitos 
 - **Java 17** ou superior instalado 
 - - **Maven** instalado 
 - - **Banco de Dados Oracle** configurado e em execução 
 - - **IDE** (como IntelliJ IDEA ou Eclipse) para editar e executar o projeto (opcional) 
 #### Passos para rodar a aplicação 
 1. **Clonar o repositório**
  
```sh
    git clone https://github.com/patinaomi/lexus-tech
    cd lexus-tech
```
2. **Configurar o banco de dados**
No arquivo `application.properties` ou `application.yml` (localizado em `src/main/resources`), configure os detalhes do banco de dados, como a URL, nome de usuário e senha:

```sh
    spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521/orcl
    spring.datasource.username=seu-usuario
    spring.datasource.password=sua-senha
    spring.datasource.driver-class
    name=oracle.jdbc.OracleDriver
```

3. **Instalar dependências**
Execute o comando Maven para baixar as dependências necessárias:
```sh
    mvn clean install
```

4. **Executar a aplicação**
Com as dependências instaladas, rode a aplicação com:
```sh
    mvn spring-boot:run
```
5.  **Acessar a aplicação**
    
    -   A aplicação estará disponível em: `http://localhost:8080`
    -   A documentação Swagger estará disponível em: `http://localhost:8080/swagger-ui.html`

#### Observações

-   Certifique-se de que o banco de dados está em execução antes de iniciar a aplicação.
-   Para testes, você pode utilizar o **Postman** ou acessar diretamente o **Swagger UI** para testar os endpoints.

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## Modelo Relacional (DER)
![Modelo Relacional](https://github.com/patinaomi/lexus-tech/blob/main/Mastering_Relational_And_Non_Relational_Database/Relational_1.png)

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## Diagrama de Classes
![Diagrama de Classes](diagrama-de-classes.png)

![Domains](domains.png)

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## Documentação da API
Foi realizada a documentação da API utilizando **Swagger**, o que facilita a visualização e teste de todos os endpoints disponíveis no sistema. Para acessar a documentação completa, basta visitar o link [Swagger](http://localhost:8080/swagger-ui/index.html#/) quando o projeto estiver em execução.

Além disso, o projeto conta com um arquivo de exportação do Postman contendo todas as requisições para teste dos endpoints da API. Esse arquivo pode ser importado diretamente no Postman, facilitando a realização de testes e a validação das funcionalidades disponíveis. Basta acessar o arquivo [por este link](https://github.com/patinaomi/delfos-machine/blob/main/JAVA%20ADVANCED/sprint-2/Challenge%20Odontoprev.postman_collection.json) e importar no Postman para ter acesso a todas as operações configuradas.

#### Cliente

- **GET /clientes**: Lista todos os clientes.
- **POST /clientes/criar**: Cria um novo cliente.
- **GET /clientes/{id}**: Retorna os detalhes de um cliente específico pelo ID.
- **PUT /clientes/{id}**: Atualiza as informações de um cliente.
- **PATCH /clientes/{id}/**: Atualiza parcialmente um dado do cliente.
- **DELETE /clientes/{id}**: Remove um cliente.

#### Consulta

- **GET /consultas**: Lista todas as consultas.
- **POST /consultas/criar**: Cria uma nova consulta.
- **GET /consultas/{id}**: Retorna os detalhes de uma consulta específica pelo ID.
- **PUT /consultas/{id}**: Atualiza as informações de uma consulta.
- **PATCH /consultas/{id}**: Atualiza o dado parcial da consulta.
- **DELETE /consultas/{id}**: Remove uma consulta.

#### Dentista

- **GET /dentistas**: Lista todos os dentistas.
- **POST /dentistas/criar**: Cria um novo dentista.
- **GET /dentistas/{id}**: Retorna os detalhes de um dentista específico pelo ID.
- **PUT /dentistas/{id}**: Atualiza as informações de um dentista.
- **PATCH /dentistas/{id}**: Atualiza um dado parcial do dentista.
- **DELETE /dentistas/{id}**: Remove um dentista.

## Feedback

- **GET /feedbacks**: Lista todos os feedbacks.
- **POST /feedbacks/criar**: Cria um novo feedback.
- **GET /feedbacks/{id}**: Retorna os detalhes de um feedback específico pelo ID.
- **PUT /feedbacks/{id}**: Atualiza as informações de um feedback.
- **PATCH /feedbacks/{id}**: Atualiza um dado parcial do feedback.
- **DELETE /feedbacks/{id}**: Remove um feedback.

## Formulário Detalhado

- **GET /formularios**: Lista todos os formulários detalhados.
- **POST /formularios/criar**: Cria um novo formulário detalhado.
- **GET /formularios/{id}**: Retorna os detalhes de um formulário específico pelo ID.
- **PUT /formularios/{id}**: Atualiza as informações de um formulário detalhado.
- **PATCH /formularios/{id}**: Atualiza um dado no formulário.
- **DELETE /formularios/{id}**: Remove um formulário detalhado.

## Sinistro

- **GET /sinistros**: Lista todos os sinistros.
- **POST /sinistros/criar**: Cria um novo sinistro.
- **GET /sinistros/{id}**: Retorna os detalhes de um sinistro específico pelo ID.
- **PUT /sinistros/{id}**: Atualiza as informações de um sinistro.
- **PATCH /sinistros/{id}**: Atualiza um dado parcial de um sinistro.
- **DELETE /sinistros/{id}**: Remove um sinistro.

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## Video
Também disponibilizamos um vídeo no YouTube demonstrando nossa solução e explicando as principais funcionalidades do projeto. Você pode assisti-lo através do seguinte link: [Link do Video no Youtube](https://youtu.be/4rk6KTjp8mM)

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## 🧑‍🤝‍🧑 Equipe

| <h3>Claudio Bispo</h3><img src="https://avatars.githubusercontent.com/u/110735259?v=4" width=180px> <h6>RM553472</h6> <a href="https://github.com/Claudio-Silva-Bispo"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/claudiosbispo"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/_claudiobispo/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|<h3>Patricia Naomi</h3> <img src="https://avatars.githubusercontent.com/u/132932532?v=4" width=180px><h6>RM552981</h6> <a href="https://github.com/patinaomi"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/patinaomi/"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/naomipati/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|
|--|--|


[:arrow_up: voltar para o índice :arrow_up:](#índice)
