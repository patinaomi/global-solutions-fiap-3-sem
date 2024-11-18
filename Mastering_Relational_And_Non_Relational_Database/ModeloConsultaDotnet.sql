-- Deletar as tabelas
DROP TABLE "RM553472"."T_Usuario" CASCADE CONSTRAINTS;
DROP TABLE "RM553472"."T_Endereco" CASCADE CONSTRAINTS;
DROP TABLE "RM553472"."T_Comodo" CASCADE CONSTRAINTS;
DROP TABLE "RM553472"."T_Item_Casa" CASCADE CONSTRAINTS;
DROP TABLE "RM553472"."T_Consumo" CASCADE CONSTRAINTS;

-- Consultar as tabelas
SELECT * FROM "RM553472"."T_Usuario";

SELECT * FROM "RM553472"."T_Endereco";

SELECT * FROM "RM553472"."T_Comodo";

SELECT * FROM "RM553472"."T_Item_Casa";

SELECT * FROM "RM553472"."T_Consumo";

-- Ver estrutura das tabelas
SELECT TABLE_NAME 
FROM ALL_TABLES 
WHERE OWNER = 'RM553472';

SELECT OWNER, TABLE_NAME
FROM ALL_TABLES
WHERE TABLE_NAME IN ('T_CONSUMO', 'T_Consumo');

SELECT COLUMN_NAME
FROM ALL_TAB_COLUMNS
WHERE TABLE_NAME = 'T_Consumo'
AND OWNER = 'RM55"3472';


SELECT COLUMN_NAME, DATA_TYPE
FROM ALL_TAB_COLUMNS
WHERE TABLE_NAME = 'T_Consumo' AND OWNER = 'RM553472';

-- Inserir dados

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor") 
VALUES (1, 1, 1, 1, 10, TO_TIMESTAMP('2024-11-18 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 90);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor") 
VALUES (2, 1, 1, 1, 20, TO_TIMESTAMP('2024-11-18 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 100);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor") 
VALUES (3, 1, 1, 1, 40, TO_TIMESTAMP('2024-11-18 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 70);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor") 
VALUES (4, 1, 1, 1, 50, TO_TIMESTAMP('2024-11-19 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 70);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor") 
VALUES (5, 1, 1, 1, 60, TO_TIMESTAMP('2024-11-20 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 80);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor")
VALUES (6, 1, 2, 2, 25.5, TO_TIMESTAMP('2024-11-21 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 55);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor")
VALUES (7, 1, 2, 2, 35.5, TO_TIMESTAMP('2024-11-22 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 65);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor")
VALUES (8, 1, 3, 3, 45.5, TO_TIMESTAMP('2024-11-23 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 75);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor")
VALUES (9, 1, 3, 3, 55.5, TO_TIMESTAMP('2024-11-24 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 85);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor")
VALUES (10, 1, 4, 4, 65.5, TO_TIMESTAMP('2024-11-25 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 95);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor")
VALUES (11, 1, 1, 1, 15.5, TO_TIMESTAMP('2024-11-16 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 50);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor")
VALUES (12, 1, 1, 1, 15, TO_TIMESTAMP('2024-11-16 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 50);

INSERT INTO "RM553472"."T_Consumo" ("IdConsumo", "IdUsuario", "IdComodo", "IdItemCasa", "ConsumoDiario", "DataConsumo", "Valor") 
VALUES (13, 1, 1, 2, 20, TO_TIMESTAMP('2024-11-17 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 60);

commit;

SELECT * FROM "RM553472"."T_Consumo";


SELECT "IdConsumo", "ConsumoDiario", "DataConsumo", "IdComodo", "IdItemCasa", "IdUsuario", "Valor"
FROM "T_Consumo"
WHERE "IdUsuario" = 1; 


/* Procedure usar em dotnet */

SELECT * FROM "RM553472"."T_Imagem";

SELECT * FROM user_tab_privs WHERE table_name = 'T_Imagem';


CREATE OR REPLACE PROCEDURE CadastrarImagemProcedure (
    NomeImagem IN VARCHAR2,
    LinkImagem IN VARCHAR2
)
AS
BEGIN
    INSERT INTO "RM553472"."T_Imagem" ("NomeImagem", "LinkImagem")
    VALUES (NomeImagem, LinkImagem);

    COMMIT;
END;

EXEC CadastrarImagemProcedure('Nome da Imagem', 'http://linkdaimagem.com');


commit;