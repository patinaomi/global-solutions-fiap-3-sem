

# Projeto de Deploy com Docker e Azure

## Índice

1. [Descrição do Projeto](descrição-do-projeto)
2. [Link para o Vídeo](link-para-o-vídeo)
3. [Pré-Requisitos](pré-requisitos)
4. [Deployment na Nuvem](deployment-na-nuvem)
5. [Documentação da API](documentação-da-api)
6. [Guia Rápido de Comandos Importantes](guia-rápido-de-comandos-importantes)
7. [Integrantes](integrantes)


## 📄 Descrição do Projeto
Este projeto consiste em uma aplicação configurada para deployment em uma máquina virtual no Microsoft Azure. Utilizamos Docker para orquestrar contêineres de serviços necessários, incluindo uma aplicação em Java, um serviço em .NET e um banco de dados Oracle. Tudo foi configurado para funcionar de forma integrada com Docker Compose.

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## 🎬 Link para o Vídeo

Disponibilizamos um vídeo no YouTube demonstrando todo o processo. [Veja o vídeo no YouTube](https://youtu.be/dfWFMGRLgt0)

[:arrow_up: voltar para o índice :arrow_up:](#índice)


## 🛠️ Pré-requisitos

 - Conta no Microsoft Azure para criar e gerenciar a máquina virtual.
 - Docker instalado e configurado na máquina virtual. 
 - Git para clonar o repositório.

## ☁️ Deployment na Nuvem
### 1. Criação da Máquina Virtual no Azure

No portal do Azure, crie uma nova máquina virtual:

* **Sistema Operacional:** Ubuntu Server 20.04 LTS.
* **Tamanho da Máquina:** Standard B1ms (baixo custo).
Habilite o monitoramento de integridade, desempenho e dependências de rede.
Após a criação, conecte-se à máquina virtual via SSH e instale o Docker utilizando os comandos oficiais para Linux.

#### Instalação e Configuração do Docker
Após instalar o Docker, verifique a instalação com o comando:

```sh
    docker --version
```

Instale o Docker Compose na máquina virtual para permitir o deployment dos contêineres com o arquivo .yml deste projeto.


### 2. Docker e Docker Compose
#### Arquivo Dockerfile
Os Dockerfiles do projeto configuram os serviços em Java e .NET, com as dependências necessárias, além de um banco de dados Oracle.

#### Arquivo Docker Compose
O docker-compose.yml permite a execução simultânea dos serviços e define as variáveis de ambiente e portas para acesso externo.


### 3. Instruções de Deploy
Para realizar o deploy dos contêineres:

**Clone este repositório na VM:**

    git clone https://github.com/patinaomi/lexus-tech
    cd lexus-tech

Execute o Docker Compose para construir e iniciar os serviços:

    docker-compose up --build -d

Verifique se os contêineres estão em execução:

    docker-compose ps

### 4. Testes
Para testar a aplicação, use as portas definidas no docker-compose.yml para acessar os serviços:

**Aplicação Java:** Porta 8080
**Aplicação .NET:** Porta 5000
**Banco de Dados Oracle:** Porta 1521

Verifique que cada serviço responde corretamente e que a comunicação entre eles funciona como esperado.

### 5. Links dos Dockerfiles
[Dockerfile Java](https://github.com/patinaomi/lexus-tech/blob/main/Java_Advanced/global/Dockerfile)

```
# Etapa base: Ambiente de build
FROM ubuntu:latest AS base

# Define o diretório de trabalho
WORKDIR /app

# Copia o código-fonte para o container
COPY . .

# Instala dependências necessárias
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven && \
    apt-get clean

# Compila o projeto sem rodar os testes
RUN mvn clean install -DskipTests

# Etapa final: Imagem otimizada com JRE
FROM openjdk:17-slim AS runtime

# Define o diretório de trabalho
WORKDIR /app

# Expõe a porta 8080
EXPOSE 8080

# Copia o arquivo JAR gerado na etapa anterior
COPY --from=base /app/target/global-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de entrada para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
```
    

[Dockerfile .NET](https://github.com/patinaomi/lexus-tech/blob/main/Advanced_Business_With_Dot_Net/LexusTech/Dockerfile)

```

FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
WORKDIR /app
EXPOSE 5121

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src
COPY ["LexusTech.csproj", "./"]
RUN dotnet restore "./LexusTech.csproj"

COPY . .
WORKDIR "/src/"
RUN dotnet build "LexusTech.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "LexusTech.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "LexusTech.dll"]
```

[Docker Compose](https://github.com/patinaomi/lexus-tech/blob/main/docker-compose.yml)

```
services:
  java-service:
    container_name: java-service
    build:
      context: Java_Advanced/lexus-tech/challenge
      dockerfile: Dockerfile
    ports:
      - "8080:8080"

  dotnet-service:
    container_name: dotnet-service
    build:
      context: Advanced_Business_With_DotNet/lexus-tech/LexusTech
      dockerfile: Dockerfile
    ports:
      - "5000:80"
      
   ```

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## 📄 Documentação da API

### Documentação via Swagger
Foi realizada a documentação da API utilizando **Swagger**, o que facilita a visualização e teste de todos os endpoints disponíveis no sistema. Para acessar a documentação completa, basta visitar o link [Swagger](http://localhost:8080/swagger-ui/index.html#/) quando o projeto estiver em execução.

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## 📝 Guia Rápido de Comandos Importantes
**Clonar o Repositório:**

    git clone https://github.com/patinaomi/delfos-machine
    cd delfos-machine

**Instalar Docker e Docker Compose:**

    sudo apt update
    sudo apt install -y ca-certificates curl gnupg lsb-release
    sudo mkdir -p /etc/apt/keyrings
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
    sudo apt update
    sudo apt install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

**Configurar Docker Compose:**

    sudo curl -L "https://github.com/docker/compose/releases/download/v2.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose

**Renomear Pastas para Organização:**


    mv "Advanced Business With .NET" Advanced_Business_With_DotNet
    mv "JAVA ADVANCED" Java_Advanced

**Build das Imagens Docker:**

    cd Advanced_Business_With_DotNet/sprint-2/DelfosMachine
    docker build -t dotnet-service .
    cd Java_Advanced/sprint-2/challenge
    docker build -t java-service .

**Configuração e Deployment com Docker Compose:**

    docker-compose up --build -d
    docker-compose ps

**Teste a API:**

    curl http://<IP_DA_VM>:8080/clientes

**Anotações Finais:**

Verifique se a configuração de rede da VM permite acesso às portas necessárias.
Após os testes, lembre-se de desligar a VM ou alterar o tamanho para economizar recursos.

[:arrow_up: voltar para o índice :arrow_up:](#índice)
## 🧑‍🤝‍🧑 Equipe

| <h3>Claudio Bispo</h3><img src="https://avatars.githubusercontent.com/u/110735259?v=4" width=180px> <h6>RM553472</h6> <a href="https://github.com/Claudio-Silva-Bispo"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/claudiosbispo"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/_claudiobispo/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|<h3>Patricia Naomi</h3> <img src="https://avatars.githubusercontent.com/u/132932532?v=4" width=180px><h6>RM552981</h6> <a href="https://github.com/patinaomi"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/patinaomi/"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/naomipati/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|
|--|--|


[:arrow_up: voltar para o índice :arrow_up:](#índice)
