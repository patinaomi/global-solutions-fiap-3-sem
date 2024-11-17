# Estrutura do Banco de Dados

## Tabela: Estado
Armazena informações sobre os estados.

| Campo       | Tipo         | Descrição                                   |
|-------------|--------------|---------------------------------------------|
| id_estado   | INTEGER      | Chave primária, gerada automaticamente.     |
| sigla       | VARCHAR2(2)  | Sigla do estado (ex.: SP, RJ).              |


## Tabela: Endereco
Armazena os endereços completos, incluindo informações sobre o estado.

| Campo        | Tipo            | Descrição                                    |
|--------------|-----------------|----------------------------------------------|
| id_endereco  | INTEGER         | Chave primária, gerada automaticamente.      |
| logradouro   | VARCHAR2(100)   | Nome da rua ou avenida.                      |
| numero       | VARCHAR2(10)    | Número do imóvel (opcional).                 |
| complemento  | VARCHAR2(50)    | Complemento do endereço (opcional).          |
| bairro       | VARCHAR2(50)    | Bairro do endereço.                          |
| cidade       | VARCHAR2(50)    | Nome da cidade.                              |
| cep          | VARCHAR2(10)    | Código de Endereçamento Postal.              |
| id_estado    | INTEGER         | Chave estrangeira referenciando T_Estado.    |


## Tabela: Usuario
Armazena informações dos usuários, incluindo dados pessoais e de contato.

| Campo       | Tipo            | Descrição                                    |
|-------------|-----------------|----------------------------------------------|
| id_usuario  | INTEGER         | Chave primária, gerada automaticamente.      |
| nome        | VARCHAR2(50)    | Nome do usuário.                             |
| sobrenome   | VARCHAR2(50)    | Sobrenome do usuário.                        |
| telefone    | VARCHAR2(15)    | Telefone de contato (opcional).              |
| email       | VARCHAR2(100)   | Endereço de email do usuário.                |
| senha       | VARCHAR2(100)   | Senha para login do usuário.                 |
| id_endereco | INTEGER         | Chave estrangeira referenciando T_Endereco.  |


## Tabela: Login
Registra os acessos dos usuários ao sistema.

| Campo       | Tipo        | Descrição                                    |
|-------------|-------------|----------------------------------------------|
| id_login    | INTEGER     | Chave primária, gerada automaticamente.      |
| data_hora   | TIMESTAMP   | Data e hora do login.                        |
| id_usuario  | INTEGER     | Chave estrangeira referenciando T_Usuario.   |



## Tabela: Comodo
Armazena informações sobre os cômodos associados aos usuários.

| Campo       | Tipo        | Descrição                                    |
|-------------|-------------|----------------------------------------------|
| id_comodo   | INTEGER     | Chave primária, gerada automaticamente.      |
| id_usuario  | INTEGER     | Chave estrangeira referenciando T_Usuario.   |
| descricao   | VARCHAR2    | Nome ou descrição do cômodo.                 |



## Tabela: Item Casa
Armazena os itens específicos em cada cômodo.

| Campo              | Tipo        | Descrição                                      |
|--------------------|-------------|------------------------------------------------|
| id_item_casa       | INTEGER     | Chave primária, gerada automaticamente.        |
| id_comodo          | INTEGER     | Chave estrangeira para o cômodo.               |
| descricao          | VARCHAR2    | Descrição do item (ex.: lâmpada, ventilador).  |


## Tabela: Consumo
Armazena os dados de consumo diário de cada item em casa.

| Campo        | Tipo        | Descrição                                      |
|--------------|-------------|------------------------------------------------|
| id_consumo   | INTEGER     | Chave primária, gerada automaticamente.        |
| id_item_casa | INTEGER     | Chave estrangeira para o item da casa.         |
| consumo      | DECIMAL(10, 2) | Consumo diário do item.                     |
| data_consumo | DATE        | Data do registro de consumo.                   |

## Tabela: Recomendacao
Armazena as recomendações relacionadas ao consumo de itens em diferentes cômodos da casa.

| Campo              | Tipo            | Descrição                                                   |
|--------------------|-----------------|-------------------------------------------------------------|
| id_recomendacao    | INTEGER         | Chave primária, gerada automaticamente.                     |
| id_usuario         | INTEGER         | Chave estrangeira para o usuário relacionado.               |
| id_comodo          | INTEGER         | Chave estrangeira para o cômodo onde a recomendação se aplica. |
| id_item_casa       | INTEGER         | Chave estrangeira para o item de casa.                      |
| id_consumo         | INTEGER         | Chave estrangeira para o consumo relacionado.               |
| consumo            | DECIMAL(10, 2)  | Amperagem do modelo treinado para o consumo do item.        |
| valor_previsto     | DECIMAL(10, 2)  | Valor previsto para o consumo do item.                      |
| variacao_consumo  | DECIMAL(10, 2)  | Variação do consumo em comparação ao valor previsto.         |
| sugestao_melhoria | VARCHAR2(255)   | Sugestão de melhoria baseada na análise de consumo.          |
| data_recomendacao | DATE            | Data da recomendação.                                       |




