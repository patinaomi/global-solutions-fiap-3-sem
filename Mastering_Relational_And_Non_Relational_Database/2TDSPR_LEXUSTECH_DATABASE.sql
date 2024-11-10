SET SERVEROUTPUT ON;

-- Nome dos Integrantes: 
-- Claudio Silva Bispo RM553472
-- Patricia Naomi Yamagishi RM552981

-- 1. Modelagem de Banco de Dados Relacional

-- Deletar tabelas caso já existam
DROP TABLE T_Estado CASCADE CONSTRAINTS;
DROP TABLE T_Endereco CASCADE CONSTRAINTS;
DROP TABLE T_Usuario CASCADE CONSTRAINTS;
DROP TABLE T_Login CASCADE CONSTRAINTS;
DROP TABLE T_Comodo CASCADE CONSTRAINTS;
DROP TABLE T_Item_Casa CASCADE CONSTRAINTS;
DROP TABLE T_Tipo_Dispositivo CASCADE CONSTRAINTS;
DROP TABLE T_Tipo_Notificacao CASCADE CONSTRAINTS;
DROP TABLE T_Notificacao CASCADE CONSTRAINTS;
DROP TABLE T_Orcamento CASCADE CONSTRAINTS;
DROP TABLE T_Consumo CASCADE CONSTRAINTS;
DROP TABLE T_Recomendacao CASCADE CONSTRAINTS;
DROP TABLE T_Configuracao_Usuario CASCADE CONSTRAINTS;
DROP TABLE T_Historico_Alerta CASCADE CONSTRAINTS;
DROP TABLE T_Tipo_Evento CASCADE CONSTRAINTS;
DROP TABLE T_Evento_Manutencao CASCADE CONSTRAINTS;
DROP TABLE T_Formulario CASCADE CONSTRAINTS;
DROP TABLE T_Feedback CASCADE CONSTRAINTS; -- nova tabela

-- Tabela Estado
CREATE TABLE T_Estado (
    id_estado INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    sigla VARCHAR2(2) NOT NULL
);

-- Tabela Endereco
CREATE TABLE T_Endereco (
    id_endereco INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    logradouro VARCHAR2(100) NOT NULL,
    numero VARCHAR2(10),
    complemento VARCHAR2(50),
    bairro VARCHAR2(50),
    cidade VARCHAR2(50) NOT NULL,
    cep VARCHAR2(10) NOT NULL,
    id_estado INTEGER NOT NULL,
    
    CONSTRAINT fk_estado FOREIGN KEY (id_estado) REFERENCES T_Estado(id_estado)
);

-- Tabela Usuario
CREATE TABLE T_Usuario (
    id_usuario INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    nome VARCHAR2(50) NOT NULL,
    sobrenome VARCHAR2(50) NOT NULL,
    telefone VARCHAR2(15),
    email VARCHAR2(100) NOT NULL,
    senha VARCHAR2(100) NOT NULL,
    id_endereco INTEGER NOT NULL,
    
    CONSTRAINT fk_endereco_global FOREIGN KEY (id_endereco) REFERENCES T_Endereco(id_endereco)
);

-- Tabela Login
CREATE TABLE T_Login (
    id_login INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    data_hora TIMESTAMP NOT NULL,
    id_usuario INTEGER NOT NULL,
    CONSTRAINT fk_usuario_login FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario)
);

-- Tabela Comodo
CREATE TABLE T_Comodo (
    id_comodo INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER,
    descricao VARCHAR2(50) NOT NULL,
    
    CONSTRAINT fk_usuario_comodo FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario)
);

-- Tabela Tipo Dispositivo
CREATE TABLE T_Tipo_Dispositivo (
    id_tipo_dispositivo INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    descricao VARCHAR2(50) NOT NULL
);

-- Tabela Item Casa
CREATE TABLE T_Item_Casa (
    id_item_casa INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_comodo INTEGER NOT NULL,
    id_tipo_dispositivo INTEGER NOT NULL,
    descricao VARCHAR2(50) NOT NULL,
    
    CONSTRAINT fk_comodo_item FOREIGN KEY (id_comodo) REFERENCES T_Comodo(id_comodo),
    CONSTRAINT fk_tipo_dispositivo FOREIGN KEY (id_tipo_dispositivo) REFERENCES T_Tipo_Dispositivo(id_tipo_dispositivo)
);

-- Tabela Orçamento
CREATE TABLE T_Orcamento (
    id_orcamento INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    data_hora_visita TIMESTAMP NOT NULL,
    valor_orcamento DECIMAL(10, 2) NOT NULL,
    
    CONSTRAINT fk_usuario_orcamento FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario)
);

-- Tabela Formulario (esse formulario vai ser armazenado no Firebase)
CREATE TABLE T_Formulario (
    id_formulario INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    nome VARCHAR2(50) NOT NULL,
    sobrenome VARCHAR2(50),
    telefone VARCHAR2(15),
    email VARCHAR2(100) NOT NULL,
    mensagem VARCHAR2(500)
);

-- Tabela Consumo
CREATE TABLE T_Consumo (
    id_consumo INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_item_casa INTEGER,
    consumo DECIMAL(10, 2), -- consumo diário do item
    data_consumo DATE,
    valor INTEGER,
    
    CONSTRAINT fk_item_casa FOREIGN KEY (id_item_casa) REFERENCES T_Item_Casa(id_item_casa)
);

-- Tabela Recomendacão
CREATE TABLE T_Recomendacao (
    id_recomendacao INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    id_comodo INTEGER NOT NULL,
    id_item_casa INTEGER,
    id_consumo INTEGER,
    consumo DECIMAL(10, 2), -- amperagem do modelo treinado
    valor_previsto DECIMAL(10, 2),
    variacao_consumo DECIMAL(10, 2),
    sugestao_melhoria VARCHAR2(255),
    data_recomendacao DATE,
    
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario),
    CONSTRAINT fk_id_comodo FOREIGN KEY (id_comodo) REFERENCES T_Comodo(id_comodo),
    CONSTRAINT id_item_casa FOREIGN KEY (id_item_casa) REFERENCES T_Item_Casa(id_item_casa),
    CONSTRAINT fk_id_consumo FOREIGN KEY (id_consumo) REFERENCES T_Consumo(id_consumo)
);

-- Tabela Tipo Notificação
CREATE TABLE T_Tipo_Notificacao (
    id_tipo_notificacao INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    desc_tipo_notif VARCHAR2(50) -- Preferências de notificação (ex.: email, app, sms)
);

-- Tabela Notificações
CREATE TABLE T_Notificacao (
    id_notificacao INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    id_tipo_notificacao INTEGER NOT NULL,
    mensagem VARCHAR2(250),
    data_envio DATE,

    CONSTRAINT fk_id_usuario_notificacao FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario),
    CONSTRAINT fk_id_tipo_notificacao FOREIGN KEY (id_tipo_notificacao) REFERENCES T_Tipo_Notificacao(id_tipo_notificacao)
);

-- Tabela Configuração Usuario
CREATE TABLE T_Configuracao_Usuario (
    id_config INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER,
    id_tipo_notificacao INTEGER,
    limite_consumo DECIMAL(10, 2), -- Limite de consumo para alertas
    CONSTRAINT fk_usuario_config FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario),
    CONSTRAINT fk_tipo_notificacao FOREIGN KEY (id_tipo_notificacao) REFERENCES T_Tipo_Notificacao(id_tipo_notificacao)
);

-- Tabela Histórico Alerta
CREATE TABLE T_Historico_Alerta (
    id_alerta INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER,
    id_comodo INTEGER,
    id_item_casa INTEGER,
    data_hora TIMESTAMP,
    descricao VARCHAR2(255),
    tipo VARCHAR2(50), -- tipo de alerta (ex.: consumo alto)
    CONSTRAINT fk_usuario_alerta FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario),
    CONSTRAINT fk_comodo_alerta FOREIGN KEY (id_comodo) REFERENCES T_Comodo(id_comodo),
    CONSTRAINT fk_item_casa_alerta FOREIGN KEY (id_item_casa) REFERENCES T_Item_Casa(id_item_casa)
);

-- Tabela Tipo Evento

CREATE TABLE T_Tipo_Evento (
    id_tipo_evento INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    descricao VARCHAR2(255)
);

-- Tabela Evento Manutenção
CREATE TABLE T_Evento_Manutencao (
    id_evento_manutencao INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER,
    id_item_casa INTEGER,
    data_hora_evento TIMESTAMP,
    descricao VARCHAR2(255),
    id_tipo_evento INTEGER, -- Manutenção periodica, eventual, emergencial
    
    CONSTRAINT fk_usuario_manutencao FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario),
    CONSTRAINT fk_item_casa_manutencao FOREIGN KEY (id_item_casa) REFERENCES T_Item_Casa(id_item_casa),
    CONSTRAINT fk_id_tipo_evento FOREIGN KEY (id_tipo_evento) REFERENCES T_Tipo_Evento(id_tipo_evento)
);

-- Tabela de Feedback das recomendações

-- Tabela Feedback
CREATE TABLE T_Feedback (
    id_feedback INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    id_recomendacao INTEGER NOT NULL, -- sobre os alertas enviados para cada cliente sobre o aumento do consumo. 
    avaliacao DECIMAL(2, 1) NOT NULL,
    comentario VARCHAR2(250), -- quero saber se a sugestão ou alerta foi útil. Também avaliar se estamos conseguindo saber o valor da conta de luz.
    
    CONSTRAINT fk_id_usuario_feedback FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario),
    CONSTRAINT fk_id_recomendacao FOREIGN KEY (id_recomendacao) REFERENCES T_Recomendacao(id_recomendacao)
);

-- 2. Procedures e Funções
SET SERVEROUTPUT ON;

-- Criar procedures para realizar os inserts no banco de dados.

// Cadastrar dados na Tabela Estado

CREATE OR REPLACE PROCEDURE inserir_estado(p_sigla VARCHAR2) IS
BEGIN
    INSERT INTO T_Estado (sigla) 
    VALUES (p_sigla);
EXCEPTION
    WHEN OTHERS THEN
        RAISE;
END;


-- Inserir 10 linhas

EXEC inserir_estado('SP');
EXEC inserir_estado('RJ');
EXEC inserir_estado('MG');
EXEC inserir_estado('PR');
EXEC inserir_estado('RS');
EXEC inserir_estado('BA');
EXEC inserir_estado('PE');
EXEC inserir_estado('CE');
EXEC inserir_estado('SC');
EXEC inserir_estado('GO');

-- Consultar

select * from T_Estado;


-- função que valida se a sigla fornecida está entre as 27 siglas válidas dos estados do Brasil.

CREATE OR REPLACE FUNCTION validar_sigla(p_sigla VARCHAR2) RETURN BOOLEAN IS
    v_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM (SELECT 'AC' AS sigla FROM DUAL UNION ALL
          SELECT 'AL' FROM DUAL UNION ALL
          SELECT 'AP' FROM DUAL UNION ALL
          SELECT 'AM' FROM DUAL UNION ALL
          SELECT 'BA' FROM DUAL UNION ALL
          SELECT 'CE' FROM DUAL UNION ALL
          SELECT 'DF' FROM DUAL UNION ALL
          SELECT 'ES' FROM DUAL UNION ALL
          SELECT 'GO' FROM DUAL UNION ALL
          SELECT 'MA' FROM DUAL UNION ALL
          SELECT 'MT' FROM DUAL UNION ALL
          SELECT 'MS' FROM DUAL UNION ALL
          SELECT 'MG' FROM DUAL UNION ALL
          SELECT 'PA' FROM DUAL UNION ALL
          SELECT 'PB' FROM DUAL UNION ALL
          SELECT 'PR' FROM DUAL UNION ALL
          SELECT 'PE' FROM DUAL UNION ALL
          SELECT 'PI' FROM DUAL UNION ALL
          SELECT 'RJ' FROM DUAL UNION ALL
          SELECT 'RN' FROM DUAL UNION ALL
          SELECT 'RS' FROM DUAL UNION ALL
          SELECT 'RO' FROM DUAL UNION ALL
          SELECT 'RR' FROM DUAL UNION ALL
          SELECT 'SC' FROM DUAL UNION ALL
          SELECT 'SP' FROM DUAL UNION ALL
          SELECT 'SE' FROM DUAL UNION ALL
          SELECT 'TO' FROM DUAL) t
    WHERE t.sigla = p_sigla;

    IF v_count > 0 THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END;

-- Teste
SET SERVEROUTPUT ON;
DECLARE
    v_resultado BOOLEAN;
    v_sigla T_Estado.sigla%TYPE;
BEGIN
    FOR r IN (SELECT sigla FROM T_Estado) LOOP
        v_sigla := r.sigla;
        v_resultado := validar_sigla(v_sigla);
        
        DBMS_OUTPUT.PUT_LINE('Sigla: ' || v_sigla || ' - ' || 
                             CASE 
                                 WHEN v_resultado THEN 'Válida' 
                                 ELSE 'Inválida' 
                             END);
    END LOOP;
END;

-- validar se a sigla tem exatamente duas letras maiúsculas

CREATE OR REPLACE FUNCTION validar_duas_maiusculas(p_sigla VARCHAR2) 
RETURN BOOLEAN 
IS
BEGIN
    -- Verifica se a sigla contém exatamente 2 letras maiúsculas
    IF REGEXP_LIKE(p_sigla, '^[A-Z]{2}$') THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END;

-- Teste 

DECLARE
    v_resultado BOOLEAN;
    v_sigla T_Estado.sigla%TYPE;
BEGIN
    FOR r IN (SELECT sigla FROM T_Estado) LOOP
        v_sigla := r.sigla;

        v_resultado := validar_duas_maiusculas(v_sigla);
        DBMS_OUTPUT.PUT_LINE('Sigla: ' || v_sigla || ' - Maiúsculas: ' || 
                             CASE 
                                 WHEN v_resultado THEN 'Válida' 
                                 ELSE 'Inválida' 
                             END);
    END LOOP;
END;


// Procedure para Cadastrar um usuário

CREATE OR REPLACE PROCEDURE cadastrar_usuario(
    p_nome          IN VARCHAR2,
    p_sobrenome     IN VARCHAR2,
    p_telefone      IN VARCHAR2,
    p_email         IN VARCHAR2,
    p_senha         IN VARCHAR2,
    p_logradouro    IN VARCHAR2,
    p_numero        IN VARCHAR2,
    p_complemento   IN VARCHAR2,
    p_bairro        IN VARCHAR2,
    p_cidade        IN VARCHAR2,
    p_cep           IN VARCHAR2,
    p_sigla_estado  IN VARCHAR2
) IS
    v_id_estado     T_Estado.id_estado%TYPE;
    v_id_endereco   T_Endereco.id_endereco%TYPE;
BEGIN

    -- Verifica se o estado existe
    SELECT id_estado INTO v_id_estado
    FROM T_Estado
    WHERE sigla = p_sigla_estado;
    
    -- Insere o endereço
    INSERT INTO T_Endereco (logradouro, numero, complemento, bairro, cidade, cep, id_estado)
    VALUES (p_logradouro, p_numero, p_complemento, p_bairro, p_cidade, p_cep, v_id_estado)
    RETURNING id_endereco INTO v_id_endereco;
    
    -- Insere o usuário
    INSERT INTO T_Usuario (nome, sobrenome, telefone, email, senha, id_endereco)
    VALUES (p_nome, p_sobrenome, p_telefone, p_email, p_senha, v_id_endereco);
    
    COMMIT;
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END;

-- Testar
BEGIN
    cadastrar_usuario(
        p_nome          => 'João',
        p_sobrenome     => 'Silva',
        p_telefone      => '11987654321',
        p_email         => 'joao.silva@email.com',
        p_senha         => 'senha123',
        p_logradouro    => 'Rua das Flores',
        p_numero        => '123',
        p_complemento   => 'Apto 101',
        p_bairro        => 'Centro',
        p_cidade        => 'São Paulo',
        p_cep           => '01000000',
        p_sigla_estado  => 'SP'
    );
END;

select * from T_Usuario;
select * from T_Endereco;
select * from T_Estado;

-- Inserir 10 linhas

BEGIN
    -- Cadastro 1
    cadastrar_usuario(
        p_nome          => 'João',
        p_sobrenome     => 'Silva',
        p_telefone      => '11987654321',
        p_email         => 'joao.silva@email.com',
        p_senha         => 'senha123',
        p_logradouro    => 'Rua das Flores',
        p_numero        => '123',
        p_complemento   => 'Apto 101',
        p_bairro        => 'Centro',
        p_cidade        => 'São Paulo',
        p_cep           => '01000000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 2
    cadastrar_usuario(
        p_nome          => 'Maria',
        p_sobrenome     => 'Oliveira',
        p_telefone      => '11976543210',
        p_email         => 'maria.oliveira@email.com',
        p_senha         => 'senha456',
        p_logradouro    => 'Av. Paulista',
        p_numero        => '200',
        p_complemento   => 'Sala 1501',
        p_bairro        => 'Bela Vista',
        p_cidade        => 'São Paulo',
        p_cep           => '01310000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 3
    cadastrar_usuario(
        p_nome          => 'Carlos',
        p_sobrenome     => 'Almeida',
        p_telefone      => '11987654322',
        p_email         => 'carlos.almeida@email.com',
        p_senha         => 'senha789',
        p_logradouro    => 'Rua da Consolação',
        p_numero        => '456',
        p_complemento   => 'Apto 502',
        p_bairro        => 'República',
        p_cidade        => 'São Paulo',
        p_cep           => '01301000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 4
    cadastrar_usuario(
        p_nome          => 'Ana',
        p_sobrenome     => 'Costa',
        p_telefone      => '11987654323',
        p_email         => 'ana.costa@email.com',
        p_senha         => 'senha101',
        p_logradouro    => 'Rua dos Três Irmãos',
        p_numero        => '789',
        p_complemento   => '',
        p_bairro        => 'Vila Progredior',
        p_cidade        => 'São Paulo',
        p_cep           => '02040000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 5
    cadastrar_usuario(
        p_nome          => 'Pedro',
        p_sobrenome     => 'Souza',
        p_telefone      => '11987654324',
        p_email         => 'pedro.souza@email.com',
        p_senha         => 'senha202',
        p_logradouro    => 'Rua das Palmeiras',
        p_numero        => '100',
        p_complemento   => 'Bloco A',
        p_bairro        => 'Vila Progredior',
        p_cidade        => 'São Paulo',
        p_cep           => '02050000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 6
    cadastrar_usuario(
        p_nome          => 'Lucia',
        p_sobrenome     => 'Mendes',
        p_telefone      => '11987654325',
        p_email         => 'lucia.mendes@email.com',
        p_senha         => 'senha303',
        p_logradouro    => 'Rua do Comércio',
        p_numero        => '312',
        p_complemento   => 'Apto 302',
        p_bairro        => 'Liberdade',
        p_cidade        => 'São Paulo',
        p_cep           => '01520000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 7
    cadastrar_usuario(
        p_nome          => 'Ricardo',
        p_sobrenome     => 'Lima',
        p_telefone      => '11987654326',
        p_email         => 'ricardo.lima@email.com',
        p_senha         => 'senha404',
        p_logradouro    => 'Rua São Bento',
        p_numero        => '25',
        p_complemento   => '',
        p_bairro        => 'Sé',
        p_cidade        => 'São Paulo',
        p_cep           => '01026000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 8
    cadastrar_usuario(
        p_nome          => 'Patricia',
        p_sobrenome     => 'Martins',
        p_telefone      => '11987654327',
        p_email         => 'patricia.martins@email.com',
        p_senha         => 'senha505',
        p_logradouro    => 'Av. dos Bandeirantes',
        p_numero        => '8500',
        p_complemento   => '',
        p_bairro        => 'Itaim Bibi',
        p_cidade        => 'São Paulo',
        p_cep           => '04503000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 9
    cadastrar_usuario(
        p_nome          => 'Juliana',
        p_sobrenome     => 'Pereira',
        p_telefone      => '11987654328',
        p_email         => 'juliana.pereira@email.com',
        p_senha         => 'senha606',
        p_logradouro    => 'Rua das Laranjeiras',
        p_numero        => '89',
        p_complemento   => 'Casa 2',
        p_bairro        => 'Vila Mariana',
        p_cidade        => 'São Paulo',
        p_cep           => '04103000',
        p_sigla_estado  => 'SP'
    );

    -- Cadastro 10
    cadastrar_usuario(
        p_nome          => 'Fernando',
        p_sobrenome     => 'Silveira',
        p_telefone      => '11987654329',
        p_email         => 'fernando.silveira@email.com',
        p_senha         => 'senha707',
        p_logradouro    => 'Rua dos Jacarandás',
        p_numero        => '555',
        p_complemento   => 'Apto 305',
        p_bairro        => 'Pinheiros',
        p_cidade        => 'São Paulo',
        p_cep           => '05428000',
        p_sigla_estado  => 'SP'
    );
END;

-- Consultar

SELECT * FROM T_Usuario;
SELECT * FROM T_Endereco;
SELECT * FROM T_Estado;

-- Três funções para validar os dados, será e-mail, telefone e nome que não deve conter números

-- 1. Função para validar e-mail

CREATE OR REPLACE FUNCTION validar_email(p_email VARCHAR2) RETURN VARCHAR2 IS
BEGIN
    IF p_email NOT LIKE '%@%' THEN
        RETURN 'Inválido';
    ELSIF p_email NOT LIKE '%.%' THEN
        RETURN 'Inválido';
    ELSE
        RETURN 'Válido';
    END IF;
END validar_email;


-- 2. Função para validar telefone
CREATE OR REPLACE FUNCTION validar_telefone(p_telefone VARCHAR2) RETURN VARCHAR2 IS
BEGIN
    IF LENGTH(p_telefone) < 10 THEN
        RETURN 'Inválido';
    ELSE
        RETURN 'Válido';
    END IF;
END validar_telefone;


-- 3. Função para validar nome

CREATE OR REPLACE FUNCTION validar_nome(p_nome VARCHAR2) RETURN VARCHAR2 IS
BEGIN
    IF REGEXP_LIKE(p_nome, '[0-9]') THEN
        RETURN 'Inválido';
    ELSE
        RETURN 'Válido';
    END IF;
END validar_nome;


-- Testar
SET SERVEROUTPUT ON;
DECLARE
    v_email VARCHAR2(100);
    v_telefone VARCHAR2(15);
    v_nome VARCHAR2(50);
    v_sobrenome VARCHAR2(50);
    v_status_email VARCHAR2(10);
    v_status_telefone VARCHAR2(10);
    v_status_nome VARCHAR2(10);
BEGIN
    FOR r IN (SELECT id_usuario, nome, sobrenome, telefone, email FROM T_Usuario) LOOP
        v_nome := r.nome;
        v_sobrenome := r.sobrenome;
        v_telefone := r.telefone;
        v_email := r.email;

        -- Validando e atribuindo status
        v_status_email := validar_email(v_email);
        v_status_telefone := validar_telefone(v_telefone);
        v_status_nome := validar_nome(v_nome);

        -- Imprimindo resultados
        DBMS_OUTPUT.PUT_LINE('Usuário ID ' || r.id_usuario || ':');
        DBMS_OUTPUT.PUT_LINE('Nome: ' || v_nome || ' - Status: ' || v_status_nome);
        DBMS_OUTPUT.PUT_LINE('Sobrenome: ' || v_sobrenome);
        DBMS_OUTPUT.PUT_LINE('Telefone: ' || v_telefone || ' - Status: ' || v_status_telefone);
        DBMS_OUTPUT.PUT_LINE('Email: ' || v_email || ' - Status: ' || v_status_email);
        DBMS_OUTPUT.PUT_LINE('-----------------------------');
    END LOOP;
END;


