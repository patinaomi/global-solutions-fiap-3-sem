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
DROP TABLE T_Consumo CASCADE CONSTRAINTS;
DROP TABLE T_Recomendacao CASCADE CONSTRAINTS;
DROP TABLE T_Configuracao_Usuario CASCADE CONSTRAINTS;
DROP TABLE T_Dados_Completos_Usuario CASCADE CONSTRAINTS;
DROP TABLE T_Json CASCADE CONSTRAINTS;

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


-- Tabela Item Casa
CREATE TABLE T_Item_Casa (
    id_item_casa INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_comodo INTEGER NOT NULL,
    id_tipo_dispositivo INTEGER NOT NULL,
    descricao VARCHAR2(50) NOT NULL,
    
    CONSTRAINT fk_comodo_item FOREIGN KEY (id_comodo) REFERENCES T_Comodo(id_comodo)
);

-- Tabela Consumo
CREATE TABLE T_Consumo (
    id_consumo INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER,
    id_comodo INTEGER,
    id_item_casa INTEGER,
    consumo DECIMAL(10, 2),
    data_consumo DATE,
    
    CONSTRAINT fk_id_usuario_consumo FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario),
    CONSTRAINT fk_id_comodo_consumo FOREIGN KEY (id_comodo) REFERENCES T_Comodo(id_comodo),
    CONSTRAINT fk_item_casa_consumo FOREIGN KEY (id_item_casa) REFERENCES T_Item_Casa(id_item_casa)
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

-- Tabela Configuração Usuario
CREATE TABLE T_Configuracao_Usuario (
    id_config INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER,
    id_tipo_notificacao INTEGER,
    limite_consumo DECIMAL(10, 2), -- Limite de consumo para alertas
    CONSTRAINT fk_usuario_config FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario)
);

// PROCEDURES E FUNÇÕES

SET SERVEROUTPUT ON;

-- Tabela Estado
CREATE OR REPLACE PROCEDURE inserir_estado(p_sigla VARCHAR2) IS
BEGIN
    INSERT INTO T_Estado (sigla) 
    VALUES (p_sigla);
EXCEPTION
    WHEN OTHERS THEN
        RAISE;
END;

-- Inserir 10 linhas na Tabela Estado
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


-- Tabela Usuario
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

-- Inserir 10 linhas a Tabela Usuario
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

--  Tabela Comodo
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

-- Inserir dados em comodo
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

-- Tabela Consumo
CREATE OR REPLACE PROCEDURE INSERIR_CONSUMO(
    p_consumo IN T_Consumo.consumo%TYPE,
    p_usuario IN T_Usuario.id_usuario%TYPE,
    p_comodo IN T_Comodo.id_comodo%TYPE,
    p_id_item_casa IN T_Consumo.id_item_casa%TYPE,
    p_data_consumo IN T_Consumo.data_consumo%TYPE
    --p_valor IN T_Consumo.valor%TYPE
) 
IS
BEGIN
    INSERT INTO T_Consumo (id_usuario, id_comodo, id_item_casa, consumo, data_consumo)
    VALUES (p_usuario, p_comodo, p_id_item_casa, p_consumo, p_data_consumo);

    COMMIT;
END;

-- Inserir dados Tabela Consumo
BEGIN
    INSERIR_CONSUMO(1, 1, 1, 5, TO_DATE('2024-11-01', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(2, 2, 2, 3, TO_DATE('2024-11-02', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(3, 3, 3, 4, TO_DATE('2024-11-03', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(4, 4, 4, 2, TO_DATE('2024-11-04', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(5, 5, 5, 7, TO_DATE('2024-11-05', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(6, 6, 6, 1, TO_DATE('2024-11-06', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(7, 7, 7, 3, TO_DATE('2024-11-07', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(8, 8, 8, 6, TO_DATE('2024-11-08', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(9, 9, 9, 2, TO_DATE('2024-11-09', 'YYYY-MM-DD'));
    INSERIR_CONSUMO(10, 10, 10, 4, TO_DATE('2024-11-10', 'YYYY-MM-DD'));
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

// Procedure para exportar dados como JSON

-- 1. Criar uma tabela temporária com os dados completos do usuário
CREATE TABLE T_Dados_Completos_Usuario AS
SELECT 
    u.id_usuario,
    u.nome,
    u.sobrenome,
    u.telefone,
    u.email,
    e.logradouro,
    e.numero,
    e.complemento,
    e.bairro,
    e.cidade,
    e.cep,
    es.sigla AS estado,
    c.id_comodo,
    c.descricao AS comodo_descricao,
    i.id_item_casa,
    i.descricao AS item_casa_descricao,
    co.consumo,
    co.data_consumo,
    -- Dados de Configurações do Usuário
    cu.limite_consumo
    
FROM T_Usuario u
JOIN T_Endereco e ON u.id_endereco = e.id_endereco
JOIN T_Estado es ON e.id_estado = es.id_estado
LEFT JOIN T_Comodo c ON u.id_usuario = c.id_usuario
LEFT JOIN T_Item_Casa i ON c.id_comodo = i.id_comodo
LEFT JOIN T_Consumo co ON co.id_consumo = i.id_item_casa
LEFT JOIN T_Configuracao_Usuario cu ON u.id_usuario = cu.id_usuario

select * from T_Dados_Completos_Usuario;

-- 2. Exportar os dados para um formato JSON

CREATE TABLE T_Json (
    id_json NUMBER GENERATED ALWAYS AS IDENTITY,
    json_data CLOB
);

CREATE OR REPLACE PROCEDURE exportar_dados AS
  -- Variável para armazenar os dados JSON
  v_json_data CLOB;
BEGIN
  -- Converte os dados da tabela T_Dados_Completos_Usuario para JSON e armazena no CLOB
  SELECT JSON_ARRAYAGG(
           JSON_OBJECT(
             'id_usuario' VALUE id_usuario,
             'nome' VALUE nome,
             'sobrenome' VALUE sobrenome,
             'telefone' VALUE telefone,
             'email' VALUE email
           )
         ) INTO v_json_data
  FROM T_Dados_Completos_Usuario;

  -- Insere o JSON gerado na tabela T_Json
  INSERT INTO T_Json (json_data)
  VALUES (v_json_data);

  -- Commit da operação
  COMMIT;
  
  DBMS_OUTPUT.PUT_LINE('Dados exportados para JSON com sucesso!');
END exportar_dados;

EXEC exportar_dados;

SELECT * FROM T_Json;

-- Salvar os dados na minha máquina
SET LINESIZE 1000
SET PAGESIZE 0
SET LONG 2000000
SET TRIMSPOOL ON

SPOOL /Users/claudiobispo/Downloads/arquivo.json

SELECT json_data
FROM T_Json;

SPOOL OFF;
