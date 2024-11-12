
# Processo do Docker

## Criar Dockerfile
**Na raiz do projeto, criar este script**

```bash
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

## Criar Docker Compose
**Vai executar o front, back e banco de dados**

```bash
    version: '3.8'
    services:
    meuapp:
        build:
        context: .
        dockerfile: Dockerfile
        platform: linux/arm64 
        ports:
        - "8080:5121"
        environment:
        - ASPNETCORE_ENVIRONMENT=Production
        - ConnectionStrings__Oracle=Data Source=(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=oracle.fiap.com.br)(PORT=1521))) (CONNECT_DATA=(SERVER=DEDICATED)(SID=ORCL)));User Id=RM553472;Password=100593;
        depends_on:
        - oracle
        networks:
        - oracle-net

    oracle:
        image: oracleinanutshell/oracle-xe-11g
        environment:
        - ORACLE_PWD=oracle
        ports:
        - "1521:1521"
        shm_size: '1g'
        networks:
        - oracle-net

    networks:
    oracle-net:
        driver: bridge
``` 

## Executar o processo

**Abrir aplicação do Docker Desktop**

**No terminal, executar este comando**

```bash
    docker-compose up --build
```

**Realizar o teste**

```bash
    http://localhost:8080/
```