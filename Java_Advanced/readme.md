<p align="center"> <img src ="https://github.com/patinaomi/lexus-tech/blob/main/Assets/lexus.png" width = 190px> </p>

# 🌐  Global Solutions

## 🦆 Índice

1. [Sobre o Projeto](#%EF%B8%8F-sobre-o-projeto)
2. [Estrutura do Projeto](#estrutura-do-projeto)
3. [Como Rodar o Projeto](#%EF%B8%8F-como-rodar-o-projeto)
4. [Deploy na Nuvem](#%EF%B8%8F-deploy-na-nuvem)
5. [Modelo Relacional (DER)](#-modelo-relacional-der)
6. [Protótipo do Projeto](#-protótipo-do-projeto)
7. [Documentação da API](#-documentação-da-api)
8. [Videos](#-videos)
9. [Equipe](#-equipe)

##  🗂️ Sobre o Projeto

Nosso projeto tem como objetivo desenvolver um sistema integrado que auxilie os usuários no gerenciamento de informações relacionadas aos itens de sua casa e ao consumo de energia elétrica. A solução é composta por um aplicativo mobile, um backend em Java e um dashboard para análise de dados. A ideia é unir tecnologia, inovação e sustentabilidade para oferecer uma experiência interativa e eficiente.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

##  ䷦Estrutura do Projeto
### Aplicativo Mobile

Desenvolvido em Kotlin, o aplicativo permitirá que o usuário:

 - Cadastre itens de sua casa e associe-os aos cômodos.
 -  Gerencie informações pessoais, como dados cadastrais. 
 - Visualize informações personalizadas e organize sua casa de forma prática.

### Backend em Java

O backend em Java Spring será responsável por:

 - Oferecer suporte ao aplicativo mobile, gerenciando a persistência e a
   recuperação de dados. 
  - Processar as informações dos itens e cômodos
   cadastrados. 
   - Fornecer APIs robustas para garantir a integração com o aplicativo mobile. 

### Dashboard para Análise de Dados

Desenvolvido em .NET, o dashboard será utilizado para:
* Exibir insights detalhados sobre os dados capturados pelo sistema.
* Permitir uma análise mais aprofundada de padrões de uso e informações geradas.
* Oferecer gráficos e relatórios visuais que auxiliem na tomada de decisões.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## ⁉️ Como Rodar o Projeto

Para rodar a aplicação Java Spring Boot, siga as instruções abaixo:
 #### Pré-requisitos 
 - **Java 17** ou superior instalado 
 - **Maven** instalado 
 - **Banco de Dados Oracle** configurado e em execução 
 - **IDE** (como IntelliJ IDEA ou Eclipse) para editar e executar o projeto (opcional) 
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

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## ☁️ Deploy na Nuvem
A imagem Docker da aplicação já está publicada no **Docker Hub** e pode ser utilizada diretamente em qualquer ambiente configurado com Docker.

Link no DockerHub: https://hub.docker.com/r/patinaomi/lexus-tech-backend

#### 1. Pré-requisitos

-   Docker instalado no ambiente (local ou nuvem).
-   Acesso à internet para puxar a imagem do Docker Hub.

#### 2. Puxando a Imagem do Docker Hub

Para utilizar a aplicação, basta executar o comando abaixo para puxar a imagem:

`docker pull patinaomi/lexus-tech-backend` 

#### 3. Executando o Container

Execute o container utilizando o comando:


`docker run -d -p 8080:8080 --name lexus-tech-backend patinaomi/lexus-tech-backend:latest` 

-   **Porta 8080:** A aplicação será exposta nesta porta. Certifique-se de que o firewall ou regras de segurança permitem o acesso a ela.

#### 4. Acessando a Aplicação

Após executar o container, a aplicação estará acessível pelo endereço:
`http://<ip-da-vm-ou-local>:8080`

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 💡 Modelo Relacional (DER)
![Modelo Relacional](https://github.com/patinaomi/lexus-tech/blob/main/Mastering_Relational_And_Non_Relational_Database/Relational_1.png)

**Observação:** As tabelas **`T_Consumo`** e **`T_Recomendacao`** não serão utilizadas no backend Java, pois são exclusivas para o projeto desenvolvido em .NET, que será responsável por funcionalidades de análise de dados e geração de recomendações no dashboard.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 📱 Protótipo do Projeto

O protótipo do aplicativo mobile foi desenvolvido no **Figma** com o objetivo de oferecer uma visão clara e interativa das funcionalidades planejadas para o projeto. Ele serve como uma referência visual para consulta e validação das principais telas e fluxos do sistema, garantindo uma experiência intuitiva e eficiente para os usuários finais.

O protótipo no Figma está disponível para consulta, permitindo um entendimento mais profundo da proposta do aplicativo mobile e seu papel no projeto como um todo. Para acessar o protótipo, basta visitar o link: [Figma](https://www.figma.com/design/YK4fNmcuOfYzl5iIFSJpU3/Lexus-Tech?node-id=0-1&t=wj0WjUptisQxLwNp-1) 

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 👩🏻‍🏫 Documentação da API
Foi realizada a documentação da API utilizando **Swagger**, o que facilita a visualização e teste de todos os endpoints disponíveis no sistema. Para acessar a documentação completa, basta visitar o link [Swagger](http://localhost:8080/swagger-ui/index.html#/) quando o projeto estiver em execução.

Além disso, o projeto conta com um arquivo de exportação do Postman contendo todas as requisições para teste dos endpoints da API. Esse arquivo pode ser importado diretamente no Postman, facilitando a realização de testes e a validação das funcionalidades disponíveis. Basta acessar o arquivo [por este link](https://github.com/patinaomi/delfos-machine/blob/main/JAVA%20ADVANCED/sprint-2/Challenge%20Odontoprev.postman_collection.json) e importar no Postman para ter acesso a todas as operações configuradas.

#### Usuário

- **GET /usuarios**: Lista todos os usuários cadastrados.
- **POST /usuarios/create**: Cria um novo usuário com base nos dados fornecidos.
- **GET /usuarios/{id}**: Retorna os detalhes de um usuário específico pelo ID.
- **PUT /usuarios/{id}**: Atualiza todas as informações de um usuário existente.
- **PATCH /usuarios/{id}**: Atualiza parcialmente as informações de um usuário.
- **DELETE /usuarios/{id}**: Remove um usuário específico pelo ID.

#### Autenticação

- **POST /auth/login**: Autentica o usuário com base no e-mail e senha.
- **POST /auth/validate-user**: Valida o usuário com base no e-mail e data de nascimento.
- **PUT /auth/update-password**: Atualiza a senha de um usuário com base no ID e na nova senha fornecidos.
- **PUT /auth/validate-email**: Valida a existência de um e-mail no sistema.

#### Comodo

- **GET /comodos**: Lista todos os cômodo.
- **POST /comodos/criar**: Cria um novo cômodo.
- **GET /comodos/{id}**: Retorna os detalhes de um cômodo específico pelo ID.
- **PUT /comodos/{id}**: Atualiza as informações de um cômodo.
- **DELETE /comodos/{id}**: Remove um cômodo.

#### Item Casa

- **GET /itenscasa**: Lista todos os itens.
- **POST /itenscasa/create**: Cria um novo item da casa.
- **GET /itenscasa/{id}**: Retorna os detalhes de item da casa específico pelo ID.
- **PUT /itenscasa/{id}**: Atualiza um item da casa.
- **DELETE /itenscasa/{id}**: Remove um item da casa.

#### Login

- **POST /login/authenticate**: Autentica o usuário com base no e-mail e senha e registra o login no sistema.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 📹 Videos
Também disponibilizamos um vídeo no YouTube demonstrando nossa solução e explicando as principais funcionalidades do projeto. Você pode assisti-lo através do seguinte link: [Link do Video no Youtube](https://youtu.be/4rk6KTjp8mM)

**Video Pitch:** [Link do Video no Youtube](https://youtu.be/4rk6KTjp8mM)

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 🧑‍🤝‍🧑 Equipe

| <h3>Claudio Bispo</h3><img src="https://avatars.githubusercontent.com/u/110735259?v=4" width=180px> <h6>RM553472</h6> <a href="https://github.com/Claudio-Silva-Bispo"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/claudiosbispo"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/_claudiobispo/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|<h3>Patricia Naomi</h3> <img src="https://avatars.githubusercontent.com/u/132932532?v=4" width=180px><h6>RM552981</h6> <a href="https://github.com/patinaomi"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/patinaomi/"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/naomipati/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|
|--|--|


[:arrow_up: voltar para o índice :arrow_up:](#-índice)
