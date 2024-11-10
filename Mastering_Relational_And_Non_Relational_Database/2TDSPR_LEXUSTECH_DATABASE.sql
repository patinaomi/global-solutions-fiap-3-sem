SET SERVEROUTPUT ON;
-- Nome dos Integrantes: 
-- Claudio Silva Bispo RM553472
-- Patricia Naomi Yamagishi RM552981

-- Deletar tabelas caso já existam
DROP TABLE T_Estado CASCADE CONSTRAINT;
DROP TABLE T_Endereco CASCADE CONSTRAINT;
DROP TABLE T_Usuario CASCADE CONSTRAINT;
DROP TABLE T_Login CASCADE CONSTRAINT;
DROP TABLE T_Comodo CASCADE CONSTRAINT;
DROP TABLE T_Item_Casa CASCADE CONSTRAINT;
DROP TABLE T_Tipo_Dispositivo CASCADE CONSTRAINT;
DROP TABLE T_Tipo_Notificacao CASCADE CONSTRAINT;
DROP TABLE T_Orcamento CASCADE CONSTRAINT;
DROP TABLE T_Consumo CASCADE CONSTRAINT;
DROP TABLE T_Modelo_Treinado CASCADE CONSTRAINT;
DROP TABLE T_Configuracao_Usuario CASCADE CONSTRAINT;
DROP TABLE T_Historico_Alerta CASCADE CONSTRAINT;
DROP TABLE T_Evento_Manutencao CASCADE CONSTRAINT;
DROP TABLE T_Formulario CASCADE CONSTRAINT;
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
    CONSTRAINT fk_endereco FOREIGN KEY (id_endereco) REFERENCES T_Endereco(id_endereco)
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
    amperagem DECIMAL(10, 2), -- consumo diário do item
    data DATE,
    CONSTRAINT fk_item_casa_consumo FOREIGN KEY (id_item_casa) REFERENCES T_Item_Casa(id_item_casa)
);

-- Tabela Modelo Treinado -- Na verdade vai ser a recomendação aqui.
CREATE TABLE T_Recomendacao (
    id_recomendacao INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_item_casa INTEGER,
    amperagem DECIMAL(10, 2), -- amperagem do modelo treinado
    valor_previsto DECIMAL(10, 2),
    variacao_consumo DECIMAL(10, 2),
    sugestao_melhoria VARCHAR2(255),
    CONSTRAINT fk_item_casa_modelo FOREIGN KEY (id_item_casa) REFERENCES T_Item_Casa(id_item_casa)
);

-- Tabela Tipo Notificação
CREATE TABLE T_Tipo_Notificacao (
    id_tipo_notificacao INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    desc_tipo_notif VARCHAR2(50) -- Preferências de notificação (ex.: email, app, sms)
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

-- Tabela Evento Manutenção
CREATE TABLE T_Evento_Manutencao (
    id_evento_manutencao INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER,
    id_item_casa INTEGER,
    data_hora_evento TIMESTAMP,
    descricao VARCHAR2(255),
    id_tipo_evento INTEGER, -- Chave estrangeira para Tipo de Evento, se necessário
    CONSTRAINT fk_usuario_manutencao FOREIGN KEY (id_usuario) REFERENCES T_Usuario(id_usuario),
    CONSTRAINT fk_item_casa_manutencao FOREIGN KEY (id_item_casa) REFERENCES T_Item_Casa(id_item_casa)
);

-- Tabela de Feedback das recomendações

-- Tabela Feedback
CREATE TABLE T_Feedback (
    id_feedback INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    id_recomendacao INTEGER NOT NULL, -- sobre os alertas enviados para cada cliente sobre o aumento do consumo. 
    avaliacao DECIMAL(2, 1) NOT NULL,
    comentario VARCHAR2(250), -- quero saber se a sugestão ou alerta foi útil. Também avaliar se estamos conseguindo saber o valor da conta de luz.
    
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    CONSTRAINT fk_id_recomendacao FOREIGN KEY (id_recomendacao) REFERENCES Recomendacao(id_recomendacao),
);
