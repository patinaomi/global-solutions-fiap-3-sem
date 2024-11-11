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

// CRIAÇÃO DAS TABELAS

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

// 2. PROCEDURES E FUNÇÕES


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

CREATE OR REPLACE FUNCTION VALIDAR_EMAIL(p_email VARCHAR2) RETURN VARCHAR2 IS
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
CREATE OR REPLACE FUNCTION VALIDAR_TELEFONE(p_telefone VARCHAR2) RETURN VARCHAR2 IS
BEGIN
    IF LENGTH(p_telefone) < 10 THEN
        RETURN 'Inválido';
    ELSE
        RETURN 'Válido';
    END IF;
END validar_telefone;


-- 3. Função para validar nome

CREATE OR REPLACE FUNCTION VALIDAR_NOME(p_nome VARCHAR2) RETURN VARCHAR2 IS
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


// Login
SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE INSERIR_LOGIN(p_id_usuario INTEGER) AS
BEGIN
    INSERT INTO T_Login (data_hora, id_usuario)
    VALUES (CURRENT_TIMESTAMP, p_id_usuario);
    COMMIT;
END;

BEGIN
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 1); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 2); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 3); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 4); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 5); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 6); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 7); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 8); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 9); 
    INSERT INTO T_Login (data_hora, id_usuario) VALUES (CURRENT_TIMESTAMP, 10); 
    COMMIT;
END;


select * from T_Login;

--  Comodo
CREATE OR REPLACE PROCEDURE INSERIR_COMODO(
    p_id_usuario  IN INTEGER,
    p_descricao   IN VARCHAR2
)
IS
BEGIN
    INSERT INTO T_Comodo (id_usuario, descricao)
    VALUES (p_id_usuario, p_descricao);

    COMMIT;
END;

-- Inserir dados

BEGIN
    INSERIR_COMODO(1, 'Sala');
    INSERIR_COMODO(2, 'Quarto');
    INSERIR_COMODO(3, 'Cozinha');
    INSERIR_COMODO(4, 'Banheiro');
    INSERIR_COMODO(5, 'Escritório');
    INSERIR_COMODO(6, 'Garagem');
    INSERIR_COMODO(7, 'Sala de Estar');
    INSERIR_COMODO(8, 'Varanda');
    INSERIR_COMODO(9, 'Lavanderia');
    INSERIR_COMODO(10, 'Closet');
END;

select * from T_Comodo;


-- Tipo de dispositivo
CREATE OR REPLACE PROCEDURE INSERIR_TIPO_DISPOSITIVO(
    p_descricao IN VARCHAR2
)
IS
BEGIN
    INSERT INTO T_Tipo_Dispositivo (descricao)
    VALUES (p_descricao);

    COMMIT;
END;

-- Inserindo tipos de dispositivo
BEGIN
    INSERIR_TIPO_DISPOSITIVO('Iluminacao');
    INSERIR_TIPO_DISPOSITIVO('Seguranca');
    INSERIR_TIPO_DISPOSITIVO('Eletrodomestico');
    INSERIR_TIPO_DISPOSITIVO('Desktop');
    INSERIR_TIPO_DISPOSITIVO('Smartwatch');
    INSERIR_TIPO_DISPOSITIVO('Smart TV');
    INSERIR_TIPO_DISPOSITIVO('Console de Jogos');
    INSERIR_TIPO_DISPOSITIVO('Câmera de Segurança');
    INSERIR_TIPO_DISPOSITIVO('Roteador');
    INSERIR_TIPO_DISPOSITIVO('Assistente Virtual');
END;

select * from T_Tipo_Dispositivo;

-- Itens de cada Comodo

CREATE OR REPLACE PROCEDURE INSERIR_ITEM_CASA(
    p_id_comodo IN INTEGER,
    p_id_tipo_dispositivo IN INTEGER,
    p_descricao IN VARCHAR2
)
IS
BEGIN
    INSERT INTO T_Item_Casa (id_comodo, id_tipo_dispositivo, descricao)
    VALUES (p_id_comodo, p_id_tipo_dispositivo, p_descricao);

    COMMIT;
END;

-- Inserir daddos de itens de cada comodo

BEGIN
    -- Inserindo itens na casa
    INSERIR_ITEM_CASA(1, 1, 'Smartphone no quarto');
    INSERIR_ITEM_CASA(2, 2, 'Tablet na sala');
    INSERIR_ITEM_CASA(3, 3, 'Laptop no escritório');
    INSERIR_ITEM_CASA(1, 4, 'Desktop no quarto');
    INSERIR_ITEM_CASA(4, 5, 'Smartwatch no corredor');
    INSERIR_ITEM_CASA(5, 6, 'Smart TV na sala');
    INSERIR_ITEM_CASA(2, 7, 'Console de jogos na sala');
    INSERIR_ITEM_CASA(3, 8, 'Câmera de segurança na garagem');
    INSERIR_ITEM_CASA(4, 9, 'Roteador no escritório');
    INSERIR_ITEM_CASA(5, 10, 'Assistente virtual na cozinha');
END;

select * from T_Item_Casa;

-- Tabela Orçamento

CREATE OR REPLACE PROCEDURE INSERIR_ORCAMENTO(
    p_id_usuario IN INTEGER,
    p_data_hora_visita IN TIMESTAMP,
    p_valor_orcamento IN DECIMAL
)
IS
BEGIN
    INSERT INTO T_Orcamento (id_usuario, data_hora_visita, valor_orcamento)
    VALUES (p_id_usuario, p_data_hora_visita, p_valor_orcamento);

    COMMIT;
END;

-- Inserir dados Orçamento

BEGIN
    INSERIR_ORCAMENTO(1, TO_TIMESTAMP('2023-11-10 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1500);
    INSERIR_ORCAMENTO(2, TO_TIMESTAMP('2023-11-12 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 2500);
    INSERIR_ORCAMENTO(3, TO_TIMESTAMP('2023-11-13 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 3200);
    INSERIR_ORCAMENTO(4, TO_TIMESTAMP('2023-11-14 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 450);
    INSERIR_ORCAMENTO(5, TO_TIMESTAMP('2023-11-15 16:15:00', 'YYYY-MM-DD HH24:MI:SS'), 785);
    INSERIR_ORCAMENTO(1, TO_TIMESTAMP('2023-11-16 13:45:00', 'YYYY-MM-DD HH24:MI:SS'), 970);
    INSERIR_ORCAMENTO(2, TO_TIMESTAMP('2023-11-17 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 150);
    INSERIR_ORCAMENTO(3, TO_TIMESTAMP('2023-11-18 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), 120);
    INSERIR_ORCAMENTO(4, TO_TIMESTAMP('2023-11-19 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 300);
    INSERIR_ORCAMENTO(5, TO_TIMESTAMP('2023-11-20 15:45:00', 'YYYY-MM-DD HH24:MI:SS'), 480);
END;

select * from T_Orcamento;

-- Tabela Formulário Mobile

CREATE OR REPLACE PROCEDURE INSERIR_FORMULARIO(
    p_nome IN VARCHAR2,
    p_sobrenome IN VARCHAR2,
    p_telefone IN VARCHAR2,
    p_email IN VARCHAR2,
    p_mensagem IN VARCHAR2
)
IS
BEGIN
    INSERT INTO T_Formulario (nome, sobrenome, telefone, email, mensagem)
    VALUES (p_nome, p_sobrenome, p_telefone, p_email, p_mensagem);

    COMMIT;
END;

-- Inserir dados Formulário Mobile

BEGIN

    INSERIR_FORMULARIO('João', 'Silva', '11999999999', 'joao.silva@gmail.com', 'Gostaria de saber mais sobre os serviços.');
    INSERIR_FORMULARIO('Maria', 'Santos', '11888888888', 'maria.santos@gmail.com', 'Como posso agendar uma visita?');
    INSERIR_FORMULARIO('Carlos', 'Oliveira', '11777777777', 'carlos.oliveira@gmail.com', 'Por favor, envie um orçamento.');
    INSERIR_FORMULARIO('Ana', 'Souza', '11666666666', 'ana.souza@gmail.com', 'Quais são as formas de pagamento?');
    INSERIR_FORMULARIO('Paulo', 'Lima', '11555555555', 'paulo.lima@gmail.com', 'Vocês trabalham com manutenção?');
    INSERIR_FORMULARIO('Fernanda', 'Almeida', '11444444444', 'fernanda.almeida@gmail.com', 'Gostaria de falar com um consultor.');
    INSERIR_FORMULARIO('Rafael', 'Pereira', '11333333333', 'rafael.pereira@gmail.com', 'Qual o prazo para entrega?');
    INSERIR_FORMULARIO('Bruna', 'Costa', '11222222222', 'bruna.costa@gmail.com', 'Por favor, envie um catálogo.');
    INSERIR_FORMULARIO('Marcos', 'Nascimento', '11111111111', 'marcos.nascimento@gmail.com', 'Gostaria de tirar algumas dúvidas.');
    INSERIR_FORMULARIO('Juliana', 'Vieira', '11000000000', 'juliana.vieira@gmail.com', 'Qual o valor mínimo para orçamento?');
END;

select * from T_Formulario;

-- Tabela Consumo
CREATE OR REPLACE PROCEDURE INSERIR_CONSUMO(
    p_id_item_casa IN T_Consumo.id_item_casa%TYPE,
    p_consumo IN T_Consumo.consumo%TYPE,
    p_data_consumo IN T_Consumo.data_consumo%TYPE,
    p_valor IN T_Consumo.valor%TYPE
) 
IS
BEGIN
    INSERT INTO T_Consumo (id_item_casa, consumo, data_consumo, valor)
    VALUES (p_id_item_casa, p_consumo, p_data_consumo, p_valor);

    COMMIT;
END;

-- Inserir dados Tabela Consumo

BEGIN
    INSERIR_CONSUMO(1, 5, TO_DATE('2024-11-01', 'YYYY-MM-DD'), 50);
    INSERIR_CONSUMO(2, 3, TO_DATE('2024-11-02', 'YYYY-MM-DD'), 30);
    INSERIR_CONSUMO(3, 4, TO_DATE('2024-11-03', 'YYYY-MM-DD'), 40);
    INSERIR_CONSUMO(4, 2, TO_DATE('2024-11-04', 'YYYY-MM-DD'), 25);
    INSERIR_CONSUMO(5, 7, TO_DATE('2024-11-05', 'YYYY-MM-DD'), 70);
    INSERIR_CONSUMO(6, 1, TO_DATE('2024-11-06', 'YYYY-MM-DD'), 20);
    INSERIR_CONSUMO(7, 3, TO_DATE('2024-11-07', 'YYYY-MM-DD'), 35);
    INSERIR_CONSUMO(8, 6, TO_DATE('2024-11-08', 'YYYY-MM-DD'), 65);
    INSERIR_CONSUMO(9, 2, TO_DATE('2024-11-09', 'YYYY-MM-DD'), 23);
    INSERIR_CONSUMO(10, 4, TO_DATE('2024-11-10', 'YYYY-MM-DD'), 46);
END;

select * from T_Consumo;

-- Tabela Recomendação

CREATE OR REPLACE PROCEDURE INSERIR_RECOMENDACAO(
    p_id_usuario IN T_Recomendacao.id_usuario%TYPE,
    p_id_comodo IN T_Recomendacao.id_comodo%TYPE,
    p_id_item_casa IN T_Recomendacao.id_item_casa%TYPE,
    p_id_consumo IN T_Recomendacao.id_consumo%TYPE,
    p_consumo IN T_Recomendacao.consumo%TYPE,
    p_valor_previsto IN T_Recomendacao.valor_previsto%TYPE,
    p_variacao_consumo IN T_Recomendacao.variacao_consumo%TYPE,
    p_sugestao_melhoria IN T_Recomendacao.sugestao_melhoria%TYPE,
    p_data_recomendacao IN T_Recomendacao.data_recomendacao%TYPE
)
IS
BEGIN
    INSERT INTO T_Recomendacao (
        id_usuario, id_comodo, id_item_casa, id_consumo, consumo, valor_previsto, 
        variacao_consumo, sugestao_melhoria, data_recomendacao
    ) 
    VALUES (
        p_id_usuario, p_id_comodo, p_id_item_casa, p_id_consumo, p_consumo, 
        p_valor_previsto, p_variacao_consumo, p_sugestao_melhoria, p_data_recomendacao
    );

    COMMIT;
END;

-- Inserir dados na tabela Recomendações

BEGIN
    INSERIR_RECOMENDACAO(1, 1, 1, 1, 5, 150, 2, 'Substituir por um modelo mais eficiente', TO_DATE('2024-11-01', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(2, 2, 2, 2, 4, 120, 1, 'Revisar o uso do item para economia', TO_DATE('2024-11-02', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(3, 3, 3, 3, 6, 200, 1, 'Melhorar a ventilação do ambiente', TO_DATE('2024-11-03', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(4, 4, 4, 4, 3, 80, 50, 'Desligar quando não estiver em uso', TO_DATE('2024-11-04', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(5, 5, 5, 5, 7, 300, 2, 'Trocar por LED', TO_DATE('2024-11-05', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(6, 6, 6, 6, 4, 110, 3, 'Isolar térmicamente o ambiente', TO_DATE('2024-11-06', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(7, 7, 7, 7, 5, 180, 9, 'Diminuir o uso no horário de pico', TO_DATE('2024-11-07', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(8, 8, 8, 8, 6, 220, 20, 'Substituir por item mais novo', TO_DATE('2024-11-08', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(9, 9, 9, 9, 4, 100, 40, 'Ajustar potência conforme necessário', TO_DATE('2024-11-09', 'YYYY-MM-DD'));
    INSERIR_RECOMENDACAO(10, 10, 10, 10, 5, 140, 1, 'Realizar manutenção preventiva', TO_DATE('2024-11-10', 'YYYY-MM-DD'));
END;

select * from T_Recomendacao;

-- Tabela Tipo de Notificação

CREATE OR REPLACE PROCEDURE INSERIR_TIPO_NOTIFICACAO(
    p_desc_tipo_notif IN T_Tipo_Notificacao.desc_tipo_notif%TYPE
)
IS
BEGIN
    INSERT INTO T_Tipo_Notificacao (desc_tipo_notif)
    VALUES (p_desc_tipo_notif);

    COMMIT;
END;

-- Inserir dados em Tipo de notificação

BEGIN
    INSERIR_TIPO_NOTIFICACAO('Email');
    INSERIR_TIPO_NOTIFICACAO('SMS');
    INSERIR_TIPO_NOTIFICACAO('App');
    INSERIR_TIPO_NOTIFICACAO('WhatsApp');
    INSERIR_TIPO_NOTIFICACAO('Ligação Telefônica');
END;

select * from T_Tipo_Notificacao;

-- Tabela Notificação

CREATE OR REPLACE PROCEDURE INSERIR_NOTIFICACAO(
    p_id_usuario IN T_Notificacao.id_usuario%TYPE,
    p_id_tipo_notificacao IN T_Notificacao.id_tipo_notificacao%TYPE,
    p_mensagem IN T_Notificacao.mensagem%TYPE,
    p_data_envio IN T_Notificacao.data_envio%TYPE
)
IS
BEGIN
    INSERT INTO T_Notificacao (id_usuario, id_tipo_notificacao, mensagem, data_envio)
    VALUES (p_id_usuario, p_id_tipo_notificacao, p_mensagem, p_data_envio);

    COMMIT;
END;

-- Inserir dados na tabela Notificação

BEGIN
    INSERIR_NOTIFICACAO(1, 1, 'Sua conta foi atualizada.', SYSDATE);
    INSERIR_NOTIFICACAO(2, 2, 'Seu consumo de energia está elevado.', SYSDATE);
    INSERIR_NOTIFICACAO(3, 3, 'Você tem uma nova mensagem.', SYSDATE);
    INSERIR_NOTIFICACAO(6, 4, 'Parabéns, você melhorou está semana, seu consumo diminuiu.', SYSDATE);
    INSERIR_NOTIFICACAO(7, 5, 'Nova atualização disponível.', SYSDATE);
    INSERIR_NOTIFICACAO(8, 2, 'Seus dados precisam ser atualizados.', SYSDATE);
    INSERIR_NOTIFICACAO(9, 4, 'Novo evento na sua área.', SYSDATE);
    INSERIR_NOTIFICACAO(10, 3, 'Atividade suspeita detectada, hora de realizar manutenção.', SYSDATE);
END;

select * from T_Notificacao;

-- Tabela configuraçao do usuário

CREATE OR REPLACE PROCEDURE INSERIR_CONFIGURACAO_USUARIO(
    p_id_usuario IN T_Configuracao_Usuario.id_usuario%TYPE,
    p_id_tipo_notificacao IN T_Configuracao_Usuario.id_tipo_notificacao%TYPE,
    p_limite_consumo IN T_Configuracao_Usuario.limite_consumo%TYPE
)
IS
BEGIN
    INSERT INTO T_Configuracao_Usuario (id_usuario, id_tipo_notificacao, limite_consumo)
    VALUES (p_id_usuario, p_id_tipo_notificacao, p_limite_consumo);

    COMMIT;
END;

-- Inserir dados na tabela de configuração do usuário

BEGIN
    INSERIR_CONFIGURACAO_USUARIO(1, 1, 100);
    INSERIR_CONFIGURACAO_USUARIO(2, 2, 150);
    INSERIR_CONFIGURACAO_USUARIO(3, 3, 200);
    INSERIR_CONFIGURACAO_USUARIO(4, 4, 120);
    INSERIR_CONFIGURACAO_USUARIO(5, 5, 80);
    INSERIR_CONFIGURACAO_USUARIO(6, 1, 250);
    INSERIR_CONFIGURACAO_USUARIO(7, 1, 300);
    INSERIR_CONFIGURACAO_USUARIO(8, 2, 50);
    INSERIR_CONFIGURACAO_USUARIO(9, 3, 200);
    INSERIR_CONFIGURACAO_USUARIO(10, 4, 120);
END;

select * from T_Configuracao_Usuario;

-- Tabela Alertas

CREATE OR REPLACE PROCEDURE inserir_historico_alerta (
    p_id_usuario IN INTEGER,
    p_id_comodo IN INTEGER,
    p_id_item_casa IN INTEGER,
    p_data_hora IN TIMESTAMP,
    p_descricao IN VARCHAR2,
    p_tipo IN VARCHAR2
) AS
BEGIN
    INSERT INTO T_Historico_Alerta (
        id_usuario, 
        id_comodo, 
        id_item_casa, 
        data_hora, 
        descricao, 
        tipo
    ) 
    VALUES (
        p_id_usuario, 
        p_id_comodo, 
        p_id_item_casa, 
        p_data_hora, 
        p_descricao, 
        p_tipo
    );
    
    COMMIT;
END;

-- Inserir dados para alerta

BEGIN
    inserir_historico_alerta(1, 1, 1, SYSTIMESTAMP, 'Alerta de consumo alto', 'Consumo Alto');
    inserir_historico_alerta(2, 1, 2, SYSTIMESTAMP, 'Alerta de consumo moderado', 'Consumo Moderado');
    inserir_historico_alerta(3, 2, 3, SYSTIMESTAMP, 'Alerta de consumo baixo', 'Consumo Baixo');
    inserir_historico_alerta(4, 3, 4, SYSTIMESTAMP, 'Alerta de consumo alto', 'Consumo Alto');
    inserir_historico_alerta(5, 4, 5, SYSTIMESTAMP, 'Alerta de consumo moderado', 'Consumo Moderado');
    inserir_historico_alerta(6, 5, 6, SYSTIMESTAMP, 'Alerta de consumo baixo', 'Consumo Baixo');
    inserir_historico_alerta(7, 6, 7, SYSTIMESTAMP, 'Alerta de consumo alto', 'Consumo Alto');
    inserir_historico_alerta(8, 7, 8, SYSTIMESTAMP, 'Alerta de consumo moderado', 'Consumo Moderado');
    inserir_historico_alerta(9, 8, 9, SYSTIMESTAMP, 'Alerta de consumo baixo', 'Consumo Baixo');
    inserir_historico_alerta(10, 9, 10, SYSTIMESTAMP, 'Alerta de consumo alto', 'Consumo Alto');
END;

select * from T_Historico_Alerta;

-- Tabela Tipo Evento

CREATE OR REPLACE PROCEDURE inserir_tipo_evento (
    p_descricao IN VARCHAR2
) AS
BEGIN
    INSERT INTO T_Tipo_Evento (descricao) 
    VALUES (p_descricao);
    
    COMMIT;
END;

-- Inserir dados

BEGIN
    inserir_tipo_evento('Evento de Consumo Alto');
    inserir_tipo_evento('Evento de Consumo Moderado');
    inserir_tipo_evento('Evento de Consumo Baixo');
    inserir_tipo_evento('Evento de Manutenção');
    inserir_tipo_evento('Evento de Emergência');
    inserir_tipo_evento('Evento de Atualização de Sistema');
    inserir_tipo_evento('Evento de Desempenho');
    inserir_tipo_evento('Evento de Alerta de Temperatura');
    inserir_tipo_evento('Evento de Configuração');
    inserir_tipo_evento('Evento de Falha no Equipamento');
END;

select * from T_Tipo_Evento;

-- Tabela Manutenção

CREATE OR REPLACE PROCEDURE INSERIR_EVENTO_MANUTENCAO (
    p_id_usuario          IN INTEGER,
    p_id_item_casa        IN INTEGER,
    p_data_hora_evento    IN TIMESTAMP,
    p_descricao           IN VARCHAR2,
    p_id_tipo_evento      IN INTEGER
) IS
BEGIN
    -- Inserir o evento de manutenção
    INSERT INTO T_Evento_Manutencao (id_usuario, id_item_casa, data_hora_evento, descricao, id_tipo_evento)
    VALUES (p_id_usuario, p_id_item_casa, p_data_hora_evento, p_descricao, p_id_tipo_evento);
    
    -- Commit para garantir que a inserção seja realizada
    COMMIT;
    
    -- Mensagem de sucesso
    DBMS_OUTPUT.PUT_LINE('Evento de manutenção inserido com sucesso!');
EXCEPTION
    -- Caso ocorra algum erro, a transação será revertida
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro ao inserir evento de manutenção: ' || SQLERRM);
END INSERIR_EVENTO_MANUTENCAO;

-- Inserir dados

BEGIN
    INSERIR_EVENTO_MANUTENCAO(1, 2, TO_TIMESTAMP('2024-11-10 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Manutenção preventiva no item A', 1);
    INSERIR_EVENTO_MANUTENCAO(2, 3, TO_TIMESTAMP('2024-11-11 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Troca de filtro do item B', 2);
    INSERIR_EVENTO_MANUTENCAO(3, 4, TO_TIMESTAMP('2024-11-12 10:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'Verificação de sistema do item C', 3);
    INSERIR_EVENTO_MANUTENCAO(4, 5, TO_TIMESTAMP('2024-11-13 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Reparo urgente no item D', 1);
    INSERIR_EVENTO_MANUTENCAO(5, 6, TO_TIMESTAMP('2024-11-14 15:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'Manutenção preventiva no item E', 2);
    INSERIR_EVENTO_MANUTENCAO(6, 7, TO_TIMESTAMP('2024-11-15 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Troca de peças no item F', 3);
    INSERIR_EVENTO_MANUTENCAO(7, 8, TO_TIMESTAMP('2024-11-16 17:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'Limpeza do item G', 1);
    INSERIR_EVENTO_MANUTENCAO(8, 9, TO_TIMESTAMP('2024-11-17 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Ajuste no sistema do item H', 2);
    INSERIR_EVENTO_MANUTENCAO(9, 10, TO_TIMESTAMP('2024-11-18 19:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'Reparo no motor do item I', 3);
    INSERIR_EVENTO_MANUTENCAO(10, 11, TO_TIMESTAMP('2024-11-19 20:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Verificação final no item J', 1);
END;

select * from T_Evento_Manutencao;

-- Tabela Feedback

CREATE OR REPLACE PROCEDURE INSERIR_FEEDBACK(
    p_id_usuario IN T_Feedback.id_usuario%TYPE,
    p_id_recomendacao IN T_Feedback.id_recomendacao%TYPE,
    p_avaliacao IN T_Feedback.avaliacao%TYPE,
    p_comentario IN T_Feedback.comentario%TYPE
) AS
BEGIN
    INSERT INTO T_Feedback (
        id_usuario,
        id_recomendacao,
        avaliacao,
        comentario
    )
    VALUES (
        p_id_usuario,
        p_id_recomendacao,
        p_avaliacao,
        p_comentario
    );
END INSERIR_FEEDBACK;

-- Inserir dados

BEGIN
    -- Inserindo 10 registros na tabela T_Feedback
    INSERIR_FEEDBACK(1, 1, 4, 'A recomendação foi muito útil, consegui reduzir meu consumo de energia.');
    INSERIR_FEEDBACK(2, 2, 3, 'A sugestão foi razoável, mas não tive muito impacto na conta de luz.');
    INSERIR_FEEDBACK(3, 3, 5, 'A recomendação foi excelente, consegui controlar o consumo e diminuir a conta.');
    INSERIR_FEEDBACK(4, 4, 2, 'O alerta foi um pouco confuso, não consegui entender exatamente como reduzir o consumo.');
    INSERIR_FEEDBACK(5, 5, 4, 'Boa sugestão, mas acredito que poderia ser mais detalhada.');
    INSERIR_FEEDBACK(6, 6, 4, 'O alerta me ajudou bastante a ajustar minha rotina e reduzir o consumo.');
    INSERIR_FEEDBACK(7, 7, 3, 'A recomendação teve algum efeito, mas a conta ainda está alta.');
    INSERIR_FEEDBACK(8, 8, 4, 'A sugestão foi útil, mas ainda preciso entender melhor os detalhes.');
    INSERIR_FEEDBACK(9, 9, 4, 'Excelente recomendação, a conta de energia caiu significativamente.');
    INSERIR_FEEDBACK(10, 10, 3, 'A sugestão foi boa, mas acho que poderia ser mais personalizada.');
END;

select * from T_Feedback;
