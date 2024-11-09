# Estrutura do Banco de Dados

## Tabela: Usuario
Armazena informações dos usuários.

| Campo         | Tipo        | Descrição                          |
|---------------|-------------|------------------------------------|
| idUsuario     | NUMBER      | Chave primária                    |
| nome          | VARCHAR2    | Nome do usuário                   |
| telefone      | VARCHAR2    | Telefone de contato               |
| email         | VARCHAR2    | Email do usuário                  |
| senha         | VARCHAR2    | Senha para login                  |
| idEndereco    | NUMBER      | Chave estrangeira para Endereco   |

---

## Tabela: Endereco
Armazena o endereço dos usuários.

| Campo         | Tipo        | Descrição                             |
|---------------|-------------|---------------------------------------|
| idEndereco    | NUMBER      | Chave primária                       |
| logradouro    | VARCHAR2    | Nome da rua                          |
| numero        | VARCHAR2    | Número da residência                 |
| complemento   | VARCHAR2    | Complemento do endereço (opcional)   |
| bairro        | VARCHAR2    | Bairro                               |
| cidade        | VARCHAR2    | Cidade                               |
| estado        | VARCHAR2    | Estado                               |
| cep           | VARCHAR2    | CEP                                  |

---

## Tabela: Login
Registra as sessões de login dos usuários.

| Campo         | Tipo        | Descrição                            |
|---------------|-------------|--------------------------------------|
| idLogin       | NUMBER      | Chave primária                     |
| idUsuario     | NUMBER      | Chave estrangeira para Usuario      |
| dataHora      | TIMESTAMP   | Data e hora do login                |

---

## Tabela: Comodo
Armazena os cômodos de cada apartamento.

| Campo         | Tipo        | Descrição                             |
|---------------|-------------|---------------------------------------|
| idComodo      | NUMBER      | Chave primária                       |
| idUsuario     | NUMBER      | Chave estrangeira para Usuario       |
| descricao     | VARCHAR2    | Nome ou descrição do cômodo          |

---

## Tabela: ItemCasa
Armazena os itens específicos em cada cômodo.

| Campo            | Tipo        | Descrição                             |
|------------------|-------------|---------------------------------------|
| idItem           | NUMBER      | Chave primária                       |
| idComodo         | NUMBER      | Chave estrangeira para Comodo        |
| idTipoDispositivo| NUMBER      | Chave estrangeira para TipoDispositivo |
| descricao        | VARCHAR2    | Descrição do item                    |

---

## Tabela: TipoDispositivo
Define os tipos de dispositivos, categorizando itens para análise.

| Campo             | Tipo        | Descrição                             |
|-------------------|-------------|---------------------------------------|
| idTipoDispositivo | NUMBER      | Chave primária                       |
| descricao         | VARCHAR2    | Tipo do dispositivo (ex.: Iluminação, Eletrodoméstico) |

---

## Tabela: Orcamento
Registra o orçamento de visitas para avaliação de consumo e recomendações.

| Campo            | Tipo        | Descrição                             |
|------------------|-------------|---------------------------------------|
| idOrcamento      | NUMBER      | Chave primária                       |
| idUsuario        | NUMBER      | Chave estrangeira para Usuario       |
| dataHoraVisita   | TIMESTAMP   | Data e hora da visita                |
| valorOrcamento   | NUMBER      | Valor estimado do orçamento          |

---

## Tabela: Consumo
Armazena os dados de consumo para cada item em cada cômodo, monitorados em tempo real.

| Campo          | Tipo        | Descrição                             |
|----------------|-------------|---------------------------------------|
| idConsumo      | NUMBER      | Chave primária                       |
| idItem         | NUMBER      | Chave estrangeira para ItemCasa      |
| idComodo       | NUMBER      | Chave estrangeira para Comodo        |
| amperagem      | NUMBER      | Amperagem do dia                     |
| data           | DATE        | Data do registro de consumo          |

---

## Tabela: ModeloTreinado
Armazena dados de predição e análises feitas pelo sistema de IA para sugerir melhorias.

| Campo             | Tipo        | Descrição                             |
|-------------------|-------------|---------------------------------------|
| idModelo          | NUMBER      | Chave primária                       |
| idComodo          | NUMBER      | Chave estrangeira para Comodo        |
| idItem            | NUMBER      | Chave estrangeira para ItemCasa      |
| amperagem         | NUMBER      | Valor da amperagem monitorada        |
| valorPrevisto     | NUMBER      | Consumo previsto pelo modelo         |
| variacaoConsumo   | NUMBER      | Variação no consumo em comparação    |
| sugestaoMelhoria  | VARCHAR2    | Recomendações baseadas na análise    |

---

## Tabela: ConfiguracaoUsuario
Permite personalizar configurações de notificações e limites de consumo.

| Campo                | Tipo        | Descrição                                 |
|----------------------|-------------|-------------------------------------------|
| idConfiguracao       | NUMBER      | Chave primária                           |
| idUsuario            | NUMBER      | Chave estrangeira para Usuario           |
| limiteConsumo        | NUMBER      | Limite de consumo para alertas           |
| preferenciaNotificacao | VARCHAR2 | Preferências de notificação (ex.: email, app) |
| intervaloAtualizacao | NUMBER      | Intervalo de atualização em minutos      |

---

## Tabela: HistoricoAlerta
Registra alertas enviados ao usuário para que ele possa acompanhar histórico e frequência de alertas.

| Campo            | Tipo        | Descrição                                  |
|------------------|-------------|--------------------------------------------|
| idAlerta         | NUMBER      | Chave primária                            |
| idUsuario        | NUMBER      | Chave estrangeira para Usuario            |
| idComodo         | NUMBER      | Chave estrangeira para Comodo (opcional)  |
| idItem           | NUMBER      | Chave estrangeira para ItemCasa (opcional)|
| dataHoraAlerta   | TIMESTAMP   | Data e hora do alerta                     |
| descricao        | VARCHAR2    | Descrição do alerta                       |
| tipo             | VARCHAR2    | Tipo de alerta (ex.: "Consumo Alto")      |

---

## Tabela: EventoManutencao
Registra eventos de manutenção realizados com base nas recomendações do sistema.

| Campo             | Tipo        | Descrição                                    |
|-------------------|-------------|----------------------------------------------|
| idEventoManutencao | NUMBER     | Chave primária                              |
| idUsuario         | NUMBER      | Chave estrangeira para Usuario              |
| idItem            | NUMBER      | Chave estrangeira para ItemCasa (opcional)  |
| dataHoraEvento    | TIMESTAMP   | Data e hora do evento de manutenção         |
| descricao         | VARCHAR2    | Descrição da ação de manutenção             |
| tipoEvento        | VARCHAR2    | Tipo de evento (ex.: "Substituição")        |
