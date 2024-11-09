


# Global Solutions
<p align="center">  <img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>  </p>

Repositório criado para a organização e hospedagem dos projetos da Global Solutions da FIAP, correspondente ao 3º semestre do curso de Análise e Desenvolvimento de Sistemas.

## Índice
 * [Global Solutions](#challenge-odontoprev)
 * [Índice](#índice)
 * [Video Pitch](#%EF%B8%8F-Video-Pitch)
 * [Desafios e Problemas](#%EF%B8%8F-desafios-e-problemas)
 * [Solução Apresentada](#-solução-apresentada)
 * [Disciplinas](#%EF%B8%8F-disciplinas)
 * [Tecnologias Utilizadas](#%EF%B8%8F-tecnologias-utilizadas)
 * [Equipe](#-equipe)

## 📹 Video Pitch

Em construção

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## ⁉️ Desafios e Problemas

Criar uma solução que traga benefícios voltado ao meio ambiente, com foco em energia limpa.

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## 🚩 Solução Apresentada

# Objetivo Geral

Desenvolver um sistema inteligente de monitoramento e controle de consumo de energia elétrica em residências, utilizando .NET e inteligência artificial com Python, para promover a conscientização sobre o uso eficiente de energia e a redução de custos.

# Objetivos Específicos

## Mapeamento do Consumo de Energia
Criar um mapa interativo de um apartamento de dois quartos, sala, cozinha, banheiro e lavanderia, que mostre o consumo de energia em tempo real utilizando cores que variam do verde claro ao vermelho, indicando os pontos de maior consumo.

## Monitoramento em Tempo Real
Implementar sensores de energia em diferentes pontos do apartamento para coletar dados de consumo em tempo real e enviar essas informações para o servidor.

## Análise de Dados e Previsão de Consumo
Utilizar técnicas de machine learning para analisar os dados coletados, prever o consumo de energia e identificar padrões de uso que possam indicar desperdícios ou necessidade de manutenção.

## Visualização e Feedback ao Usuário
Desenvolver um dashboard interativo que permita aos moradores visualizar o consumo de energia em tempo real, com gráficos e alertas sobre áreas de alto consumo e sugestões de economia.

## Estimativa de Custos
Calcular e exibir a estimativa do valor da conta de luz com base no consumo atual, ajudando os moradores a entenderem o impacto financeiro do uso de energia.

## Recomendações de Manutenção
Fornecer recomendações específicas sobre onde realizar manutenção ou ajustes para reduzir o consumo de energia, baseadas na análise dos dados coletados.

## Integração de Tecnologias
Integrar as tecnologias .NET para o desenvolvimento do frontend e backend, e Python para a coleta e análise de dados, garantindo uma comunicação eficiente entre os componentes do sistema.

# Estrutura do Projeto

## Mapeamento do Apartamento
## Frontend
Use uma biblioteca de visualização como o Blazor no .NET para criar a interface do usuário. Você pode desenhar o mapa do apartamento e usar cores para indicar o consumo de energia.

## Backend
Utilize ASP.NET Core para gerenciar a lógica do servidor e a comunicação com o banco de dados.
Monitoramento de Energia:

## Sensores
Instale sensores de consumo de energia em diferentes pontos do apartamento. Esses sensores podem enviar dados em tempo real para o servidor.

## Coleta de Dados
Use Python para coletar e processar os dados dos sensores. Bibliotecas como pandas e NumPy podem ser úteis para análise de dados.

## Análise de Dados e IA
Machine Learning: Utilize ML.NET para criar modelos de machine learning que possam prever o consumo de energia e identificar padrões. Você pode treinar modelos para detectar anomalias e sugerir manutenção.

Integração com Python: Para tarefas mais complexas de IA, como redes neurais, você pode usar bibliotecas Python como TensorFlow ou PyTorch e integrar os resultados no seu aplicativo .NET.

## Visualização e Conscientização
Dashboard: Crie um dashboard interativo no Blazor que mostre o consumo de energia em tempo real. Use gráficos e cores para indicar áreas de alto consumo.
Alertas e Recomendações: Baseado nos dados analisados, envie alertas para os moradores sobre áreas que precisam de manutenção ou onde podem economizar energia.

## Tecnologias e Ferramentas
.NET: Para o desenvolvimento do frontend e backend.
Blazor: Para a criação de interfaces interativas.
ML.NET: Para machine learning e análise de dados.
Python: Para coleta e processamento de dados, e tarefas avançadas de IA.
Sensores de Energia: Para monitoramento em tempo real.
Azure: Para hospedagem e serviços de IA, como o Azure Machine Learning.

## Passos Iniciais

Desenhe o Mapa: Comece desenhando o mapa do apartamento e definindo as áreas onde os sensores serão instalados.
Instale os Sensores: Configure os sensores de energia e conecte-os ao seu sistema.
Desenvolva o Backend: Crie a API em ASP.NET Core para receber e processar os dados dos sensores.
Implemente a IA: Treine modelos de machine learning para análise de consumo e integração com o sistema.
Crie o Dashboard: Desenvolva a interface do usuário no Blazor para visualização dos dados.

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## 🗂️ Disciplinas
* [DevOps Tools & Cloud Computing](https://github.com/patinaomi/delfos-machine/tree/main/DevOps%20Tools%20%26%20Cloud%20Computing)
* [Compliance, Quality Assurance & Tests](https://github.com/patinaomi/delfos-machine/tree/main/Compliance%2C%20Quality%20Assurance%20%26%20Tests)
* [Mastering Relational and Non-Relational Database](https://github.com/patinaomi/delfos-machine/tree/main/Mastering%20Relational%20And%20Non%20Relational%20Database)
* [Advanced Business Development with .Net](https://github.com/patinaomi/delfos-machine/tree/main/Advanced%20Business%20With%20.NET)
* [Disruptive Architectures: IOT, IOB & Generative IA](https://github.com/patinaomi/delfos-machine/tree/main/Disruptive%20Architectures%3A%20IOT%20%26%20IOB)
* [Java Advanced](https://github.com/patinaomi/delfos-machine/tree/main/JAVA%20ADVANCED)
* [Mobile Application Development](https://github.com/patinaomi/delfos-machine/tree/main/Mobile%20Application%20Development)

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## 🛠️ Tecnologias Utilizadas
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) ![Azure](https://img.shields.io/badge/azure-%230072C6.svg?style=for-the-badge&logo=microsoftazure&logoColor=white) <br>
![.Net](https://img.shields.io/badge/.NET-5C2D91?style=for-the-badge&logo=.net&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54) ![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white) ![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white) ![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white) 

[:arrow_up: voltar para o índice :arrow_up:](#índice)

## 🧑‍🤝‍🧑 Equipe

| <h3>Claudio Bispo</h3><img src="https://avatars.githubusercontent.com/u/110735259?v=4" width=180px> <h6>RM553472</h6> <a href="https://github.com/Claudio-Silva-Bispo"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/claudiosbispo"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/_claudiobispo/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|<h3>Patricia Naomi</h3> <img src="https://avatars.githubusercontent.com/u/132932532?v=4" width=180px><h6>RM552981</h6> <a href="https://github.com/patinaomi"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/patinaomi/"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/naomipati/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|
|--|--|


[:arrow_up: voltar para o índice :arrow_up:](#índice)
