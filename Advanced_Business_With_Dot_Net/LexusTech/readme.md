<p align="center"> <img src ="https://github.com/patinaomi/lexus-tech/blob/main/Assets/lexus.png" width = 190px> </p>

# Projeto de previsão de consumo de energia residencial

## Dados dos alunos

1. Claudio Silva Bispo
```bash
    RM 553472
```

2. Patricia Naomi Yamagishi
```bash
    RM 552981
```

## Link do vídeo geral explicando o projeto
```bash
    https://youtu.be/JoY1wFdp5v4
```

## Vídeo navegando na plataforma web
```bash
    https://youtu.be/C01bLjsOcRY
```

## Vídeo com beta no uso da IA
```bash
    https://youtu.be/jtjY-xqsUqQ
```

## Objetivo

Este projeto tem como objetivo desenvolver um modelo de Inteligência Artificial (IA) capaz de analisar e prever o consumo de energia em uma residência, utilizando dados históricos sobre o uso de diferentes ambientes e aparelhos. A partir dessa análise, o modelo identificará os ambientes e os itens que mais consomem energia, e será capaz de estimar o valor da próxima conta de luz com base nesses consumos.

A visualização dessas informações será proporcionada por meio de uma aplicação desenvolvida em .NET. O usuário poderá interagir com a aplicação de forma simples e intuitiva: ele poderá enviar fotos de cada ambiente de sua residência por exmeplo, ou cadastrar as informações de forma manual através dos nossos formlários. A IA (na matéria de IOT), então, realizará a identificação dos itens presentes nas imagens e, por meio de um processo de reconhecimento de objetos, exibirá um pop-up interativo com o consumo estimado de cada item, tanto em quantidade quanto em valor previsto para o consumo de energia.

Também teremos o Dashboard e uma sessão contendo um mapa com um resumo de cada gasto e consumo, de forma mais simplificada e detalhista.

Essa abordagem inovadora visa não apenas fornecer uma previsão precisa do consumo de energia, mas também permitir que o usuário visualize de forma prática e direta os impactos do uso de aparelhos em sua conta de luz, incentivando uma gestão mais eficiente e consciente do consumo de energia.

Nosso objetivo é levar as informações diretamente ao cliente, sem que ele precise investigar por conta própria as razões para o consumo de energia elevado em determinados horários ou dias. Queremos evitar surpresas desagradáveis, como contas altas ou falhas inesperadas de equipamentos. Estamos desenvolvendo uma solução integrada e abrangente, que atende o cliente de diversas maneiras: seja por meio de análises enviadas por e-mail, notificações com sugestões personalizadas, ou por nossos dados, que apontarão claramente o foco do problema. Todas essas informações serão coletadas por um painel instalado no quadro de energia geral, conectado a cada ambiente e dispositivo da residência.

# Informações adicionais

No readme geral do repositório, contém as informações gerais explicando nosso projeto em detalhes.

```bash
    https://github.com/patinaomi/lexus-tech
```

# Explicação simples do projeto com .NET

**Link com vídeo do prótotipo da nossa aplicação**

```bash
    Inserir o link aqui mas também está dentro do projeto na aba Vídeo
``` 

1. Será interessante para você entender melhor nossa aplicação.

## Tecnologias Utilizadas
- ASP.NET Core
- Banco de dados Oracle
- Razor
Projeto MVC, apenas com as Models, Views e Controlles gerenciado back e front.

## Configuração e Execução

### Pré-requisitos

- .NET SDK

## Execução

1. Restaure as dependências:
```bash
    dotnet restore
```

2. Compile e execute a aplicação:
```bash
    dotnet run
```

3. Build:
```bash
    dotnet build
```

4. Execução assistida:
```bash
    dotnet watch run
```

## Instalações

Instale o pacote NuGet para Oracle:
```bash
    dotnet add package Oracle.ManagedDataAccess.Core
    dotnet add package Oracle.EntityFrameworkCore
```

## Migrations

**Depois de criar as Models e Controller, precisamos criar as Migrations**

1. Instalar a ferramenta dotnet-ef
```bash
    dotnet tool install --global dotnet-ef
```

2. Verificar se a instalação foi bem-sucedida
```bash
    dotnet ef
```

3. Se ainda não tiver o Microsoft.EntityFrameworkCore.Design, que é necessário para rodar migrações, execute também:
```bash
    dotnet add package Microsoft.EntityFrameworkCore.Design
```

4. Gere as migrações:
```bash
    dotnet ef migrations add Inicio
```
**Sempre mudar o nome final, ao cadastrar uma nova tabela**

5. Aplique as migrações:
```bash
    dotnet ef database update
```

5. Se precisar listar as Migrations
```bash
    dotnet ef migrations list
```

6. Remova a Migration se houver duplicados
```bash
    dotnet ef migrations remove
```

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

## Processo Json

```bash
    dotnet add package Newtonsoft.Json
``` 

**Chamar este processo nos componentes que eu preciso usar**
```bash
    @using Newtonsoft.Json
``` 


# Processo IOT

1. Por meio de formulários, estamos simulando e aplicando modelos que nossa solução busca atender. Ao cadastrar informações como usuário, endereço, ambiente, equipamento e consumo, utilizamos IA para analisar cada detalhe, identificar pontos críticos e projetar a conta de luz do cliente.

2. Com o cadastro de imagens (observando que, nesta simulação na disciplina de .NET, o Oracle não aceita imagens), avaliamos quais equipamentos eletrônicos estão presentes e em qual ambiente estão alocados.

3. Através do cadastro de alertas, o cliente receberá notificações por e-mail apontando os equipamentos e ambientes que necessitam de atenção, além de sugestões de substituição de equipamentos, acompanhamento diário do valor projetado da conta de energia e outras informações relevantes.

4. Utilizando uma API desenvolvida em Flask, enviaremos dados ao banco de informações, incluindo recomendações, sugestões e insights que alimentarão painéis, fornecendo insumos importantes para o cliente.

# Processo Oracle

**Procedures**

Instalar este pacote

```bash
    dotnet add package Oracle.ManagedDataAccess
```


## Configurar o Program.cs

```bash
    using Microsoft.AspNetCore.Builder;
    using Microsoft.Extensions.DependencyInjection;
    using Microsoft.Extensions.Hosting;
    using Microsoft.EntityFrameworkCore;
    using Microsoft.AspNetCore.Authentication.Cookies;

    var builder = WebApplication.CreateBuilder(args);

    // Add services to the container.
    builder.Services.AddControllersWithViews();

    builder.Services.AddDbContext<ApplicationDbContext>(options => options.UseOracle(builder.Configuration.GetConnectionString("Oracle")));

    // Configurar autenticação com cookies
    builder.Services.AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)
        .AddCookie(options =>
        {
            options.LoginPath = "/Login/Logar";
        });

    var app = builder.Build();

    app.Urls.Add("http://0.0.0.0:5121");

    // Aplica as migrações pendentes e cria o banco se necessário. Melhor do que eu esperar ter a tabela.
    using (var scope = app.Services.CreateScope())
    {
        var context = scope.ServiceProvider.GetRequiredService<ApplicationDbContext>();
        // Aplica migrações e cria o banco se não existir
        context.Database.Migrate();  
    }


    // Configure the HTTP request pipeline.
    if (!app.Environment.IsDevelopment())
    {
        app.UseExceptionHandler("/Home/Error");
        app.UseHsts();
    }

    app.UseHttpsRedirection();
    app.UseStaticFiles();

    app.UseRouting();

    app.UseAuthentication();
    app.UseAuthorization();

    app.MapControllerRoute(
        name: "default",
        pattern: "{controller=Home}/{action=Index}/{id?}");

    app.Run();
```

# Executar a aplicação e testar
```bash
    dotnet build
```

```bash
    dotnet run
```
