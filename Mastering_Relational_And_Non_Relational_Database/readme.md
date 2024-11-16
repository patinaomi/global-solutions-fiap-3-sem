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

### Restrições
- `fk_estado`: Chave estrangeira referenciando `id_estado` na tabela `T_Estado`.

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

### Restrições
- `fk_endereco_global`: Chave estrangeira referenciando `id_endereco` na tabela `T_Endereco`.

## Tabela: Login
Registra os acessos dos usuários ao sistema.

| Campo       | Tipo        | Descrição                                    |
|-------------|-------------|----------------------------------------------|
| id_login    | INTEGER     | Chave primária, gerada automaticamente.      |
| data_hora   | TIMESTAMP   | Data e hora do login.                        |
| id_usuario  | INTEGER     | Chave estrangeira referenciando T_Usuario.   |

### Restrições
- `fk_usuario_login`: Chave estrangeira referenciando `id_usuario` na tabela `T_Usuario`.


## Tabela: Comodo
Armazena informações sobre os cômodos associados aos usuários.

| Campo       | Tipo        | Descrição                                    |
|-------------|-------------|----------------------------------------------|
| id_comodo   | INTEGER     | Chave primária, gerada automaticamente.      |
| id_usuario  | INTEGER     | Chave estrangeira referenciando T_Usuario.   |
| descricao   | VARCHAR2    | Nome ou descrição do cômodo.                 |

### Restrições
- `fk_usuario_comodo`: Chave estrangeira referenciando `id_usuario` na tabela `T_Usuario`.


## Tabela: Tipo Dispositivo
Define os tipos de dispositivos disponíveis.

| Campo              | Tipo        | Descrição                                      |
|--------------------|-------------|------------------------------------------------|
| id_tipo_dispositivo| INTEGER     | Chave primária, gerada automaticamente.        |
| descricao          | VARCHAR2    | Descrição do tipo de dispositivo (ex.: Iluminação, Eletrodoméstico). |

## Tabela: Item Casa
Armazena os itens específicos em cada cômodo.

| Campo              | Tipo        | Descrição                                      |
|--------------------|-------------|------------------------------------------------|
| id_item_casa       | INTEGER     | Chave primária, gerada automaticamente.        |
| id_comodo          | INTEGER     | Chave estrangeira para o cômodo.               |
| id_tipo_dispositivo| INTEGER     | Chave estrangeira para o tipo de dispositivo.  |
| descricao          | VARCHAR2    | Descrição do item (ex.: lâmpada, ventilador).  |

## Tabela: Orçamento
Registra o orçamento de visitas para avaliação de consumo e recomendações.

| Campo             | Tipo        | Descrição                                     |
|-------------------|-------------|-----------------------------------------------|
| id_orcamento      | INTEGER     | Chave primária, gerada automaticamente.       |
| id_usuario        | INTEGER     | Chave estrangeira para o usuário.             |
| data_hora_visita  | TIMESTAMP   | Data e hora da visita para orçamento.         |
| valor_orcamento   | DECIMAL(10, 2) | Valor estimado do orçamento.                  |


## Tabela: Formulario
Armazena dados de formulários enviados pelos usuários (serão armazenados no Firebase).

| Campo          | Tipo        | Descrição                                    |
|----------------|-------------|----------------------------------------------|
| id_formulario  | INTEGER     | Chave primária, gerada automaticamente.      |
| nome           | VARCHAR2(50) | Nome do usuário.                            |
| sobrenome      | VARCHAR2(50) | Sobrenome do usuário.                       |
| telefone       | VARCHAR2(15) | Telefone de contato.                        |
| email          | VARCHAR2(100) | Email do usuário.                           |
| mensagem       | VARCHAR2(500) | Mensagem enviada pelo usuário.              |


## Tabela: Consumo
Armazena os dados de consumo diário de cada item em casa.

| Campo        | Tipo        | Descrição                                      |
|--------------|-------------|------------------------------------------------|
| id_consumo   | INTEGER     | Chave primária, gerada automaticamente.        |
| id_item_casa | INTEGER     | Chave estrangeira para o item da casa.         |
| consumo      | DECIMAL(10, 2) | Consumo diário do item.                      |
| data_consumo | DATE        | Data do registro de consumo.                  |
| valor        | INTEGER     | Valor associado ao consumo do item.            |


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

## Tabela: Tipo Notificacao
Armazena os tipos de notificação para as preferências de comunicação.

| Campo               | Tipo           | Descrição                                                           |
|---------------------|----------------|---------------------------------------------------------------------|
| id_tipo_notificacao | INTEGER        | Chave primária, gerada automaticamente.                             |
| desc_tipo_notif     | VARCHAR2(50)   | Descrição do tipo de notificação (ex.: email, app, sms).            |


## Tabela: Notificacoes
Armazena as notificações enviadas aos usuários.

| Campo               | Tipo           | Descrição                                                           |
|---------------------|----------------|---------------------------------------------------------------------|
| id_notificacao      | INTEGER        | Chave primária, gerada automaticamente.                             |
| id_usuario          | INTEGER        | Chave estrangeira que referencia a tabela T_Usuario.                |
| id_tipo_notificacao | INTEGER        | Chave estrangeira que referencia a tabela T_Tipo_Notificacao.       |
| mensagem            | VARCHAR2(250)  | Mensagem da notificação.                                            |
| data_envio          | DATE           | Data de envio da notificação.                                       |

## Tabela: Configuracao Usuario
Armazena as configurações de cada usuário, incluindo notificações e limites de consumo.

| Campo                  | Tipo           | Descrição                                                               |
|------------------------|----------------|-------------------------------------------------------------------------|
| id_config              | INTEGER        | Chave primária, gerada automaticamente.                                 |
| id_usuario             | INTEGER        | Chave estrangeira que referencia a tabela T_Usuario.                    |
| id_tipo_notificacao    | INTEGER        | Chave estrangeira que referencia a tabela T_Tipo_Notificacao.           |
| limite_consumo         | DECIMAL(10, 2) | Limite de consumo para alertas de notificação.                          |


## Tabela: Histórico Alerta
Armazena os registros de alertas gerados para os usuários, com informações detalhadas sobre o tipo e o motivo do alerta.

| Campo                 | Tipo           | Descrição                                                             |
|-----------------------|----------------|-----------------------------------------------------------------------|
| id_alerta             | INTEGER        | Chave primária, gerada automaticamente.                               |
| id_usuario            | INTEGER        | Chave estrangeira que referencia a tabela T_Usuario.                  |
| id_comodo             | INTEGER        | Chave estrangeira que referencia a tabela T_Comodo.                   |
| id_item_casa          | INTEGER        | Chave estrangeira que referencia a tabela T_Item_Casa.                |
| data_hora             | TIMESTAMP      | Data e hora do alerta gerado.                                         |
| descricao             | VARCHAR2(255)  | Descrição do alerta.                                                  |
| tipo                  | VARCHAR2(50)   | Tipo de alerta (ex.: consumo alto).                                   |


## Tabela: Tipo Evento
Armazena os tipos de eventos que podem ser registrados no sistema.

| Campo            | Tipo           | Descrição                                |
|------------------|----------------|------------------------------------------|
| id_tipo_evento  | INTEGER        | Chave primária, gerada automaticamente.  |
| descricao        | VARCHAR2(255)  | Descrição do tipo de evento.             |


## Tabela: Evento Manutenção
Armazena os eventos de manutenção realizados nos itens da casa.

| Campo                | Tipo            | Descrição                                                  |
|----------------------|-----------------|------------------------------------------------------------|
| id_evento_manutencao | INTEGER         | Chave primária, gerada automaticamente.                    |
| id_usuario           | INTEGER         | Chave estrangeira, referencia o usuário responsável.       |
| id_item_casa         | INTEGER         | Chave estrangeira, referência ao item da casa.             |
| data_hora_evento    | TIMESTAMP       | Data e hora do evento de manutenção.                        |
| descricao            | VARCHAR2(255)   | Descrição do evento de manutenção.                          |
| id_tipo_evento      | INTEGER         | Chave estrangeira, tipo de evento (manutenção periódica, eventual, emergencial). |

### Relacionamentos:
- **id_usuario**: Chave estrangeira referenciando `T_Usuario(id_usuario)`.
- **id_item_casa**: Chave estrangeira referenciando `T_Item_Casa(id_item_casa)`.
- **id_tipo_evento**: Chave estrangeira referenciando `T_Tipo_Evento(id_tipo_evento)`.

## Tabela: Feedback
Armazena o feedback dos usuários sobre as recomendações e alertas de aumento de consumo.

| Campo               | Tipo            | Descrição                                                         |
|---------------------|-----------------|-------------------------------------------------------------------|
| id_feedback         | INTEGER         | Chave primária, gerada automaticamente.                           |
| id_usuario          | INTEGER         | Chave estrangeira, referência ao usuário que forneceu o feedback. |
| id_recomendacao     | INTEGER         | Chave estrangeira, referência à recomendação ou alerta relacionado ao feedback. |
| avaliacao           | DECIMAL(2, 1)   | Avaliação dada pelo usuário (ex.: 4.5).                           |
| comentario          | VARCHAR2(250)   | Comentário adicional do usuário sobre a sugestão ou alerta recebido. |

### Relacionamentos:
- **id_usuario**: Chave estrangeira referenciando `T_Usuario(id_usuario)`.
- **id_recomendacao**: Chave estrangeira referenciando `T_Recomendacao(id_recomendacao)`.

