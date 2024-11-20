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
