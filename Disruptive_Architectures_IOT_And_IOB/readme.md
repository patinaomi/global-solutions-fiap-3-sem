<p align="center"> <img src ="https://github.com/patinaomi/lexus-tech/blob/main/Assets/lexus.png" width = 190px> </p>

# Projeto de previsão de consumo de energia residencial

## Dados dos alunos

1. Claudio Silva Bispo
```bash
    RM 553472
```

2. Patricia Naomi Yamagishi
```bash
    RM 552981
```

## Link do vídeo geral explicando o projeto
```bash
    https://youtu.be/JoY1wFdp5v4
```

## Vídeo navegando na plataforma web
```bash
    https://youtu.be/C01bLjsOcRY
```

## Vídeo com beta no uso da IA
```bash
    https://youtu.be/jtjY-xqsUqQ
```

## Objetivo

Este projeto tem como objetivo desenvolver um modelo de Inteligência Artificial (IA) capaz de analisar e prever o consumo de energia em uma residência, utilizando dados históricos sobre o uso de diferentes ambientes e aparelhos. A partir dessa análise, o modelo identificará os ambientes e os itens que mais consomem energia, e será capaz de estimar o valor da próxima conta de luz com base nesses consumos.

A visualização dessas informações será proporcionada por meio de uma aplicação desenvolvida em .NET. O usuário poderá interagir com a aplicação de forma simples e intuitiva: ele poderá enviar fotos de cada ambiente de sua residência. A IA, então, realizará a identificação dos itens presentes nas imagens e, por meio de um processo de reconhecimento de objetos, exibirá um pop-up interativo com o consumo estimado de cada item, tanto em quantidade quanto em valor previsto para o consumo de energia.

Também teremos o Dashboard com um resumo de cada gasto e consumo, de forma mais simplificada e detalhista.

Essa abordagem inovadora visa não apenas fornecer uma previsão precisa do consumo de energia, mas também permitir que o usuário visualize de forma prática e direta os impactos do uso de aparelhos em sua conta de luz, incentivando uma gestão mais eficiente e consciente do consumo de energia.

## Etapas do Projeto

**1. Coleta de Dados**

Para o desenvolvimento deste modelo, será necessário coletar dados sobre o consumo de energia de diferentes ambientes e aparelhos presentes na residência. Esses dados devem incluir:

    Ambientes da casa: Sala, cozinha, quartos, banheiro, lavanderia, sacada, etc.
    
    Itens que consomem energia: Eletrodomésticos como ar-condicionado, geladeira, TV, chuveiro, lâmpadas, etc.

    Dados de uso dos itens: Tempo de uso diário de cada aparelho.

    Características adicionais: Fatores como temperatura externa, número de pessoas na casa, entre outros, que podem influenciar no consumo de energia.

Os dados coletados através das aplicações dotnet e mobile(kotlin) via aplicação Oracle (Banco de dados local). Esses dados serão a base para a análise de consumo, identificando quais ambientes e aparelhos estão consumindo mais energia.

## 2. Análise dos Dados

Após a coleta dos dados, será realizada uma análise exploratória para identificar padrões e comportamentos no consumo de energia. Através de gráficos e tabelas, será possível identificar os ambientes e aparelhos que têm o maior impacto no consumo total de energia da casa.

## 3. Desenvolvimento do Modelo Preditivo

Com os dados organizados e analisados, o próximo passo será desenvolver um modelo preditivo utilizando técnicas de aprendizado de máquina. O modelo será responsável por prever o consumo de energia futuro e o valor da conta de luz com base nas variáveis de consumo.

O modelo utilizará as seguintes características:

    Tempo de uso de cada aparelho
    
    Potência dos aparelhos em kWh
    
    Consumo total diário por ambiente e por aparelho

Utilizaremos um modelo de Regressão, como o Random Forest Regressor, para prever o valor total da conta de luz. Esse modelo será treinado com os dados históricos de consumo de energia e será capaz de fazer previsões de consumo para períodos futuros.

## 4. Treinamento e Validação do Modelo

Para garantir a precisão do modelo, será realizado um processo de treinamento com um conjunto de dados históricos, seguido de uma validação utilizando um conjunto de dados separados. A ideia é testar a capacidade do modelo de prever corretamente o consumo de energia e o valor da conta de luz para um novo período.

O modelo será avaliado utilizando métricas como:

    Erro Médio Absoluto (MAE)
    
    Erro Quadrático Médio (MSE)
    
    R² (Coeficiente de Determinação)

Essas métricas ajudarão a entender o quão bem o modelo está fazendo as previsões e se há necessidade de ajustes nos parâmetros.

## 5. Predição do valor da próxima conta de luz

Uma vez que o modelo tenha sido treinado e validado, ele será capaz de fazer previsões sobre o consumo de energia futuro com base nas informações fornecidas. O usuário poderá inserir dados sobre o tempo de uso dos aparelhos e o modelo irá calcular o valor estimado da próxima conta de luz, com base nos hábitos de consumo atuais.

Além disso, o modelo poderá identificar os aparelhos e ambientes que estão consumindo mais energia e sugerir possíveis ações para otimizar o consumo e reduzir o valor da conta.

## 6. Geração de recomendações

A partir da análise dos dados, o modelo também poderá fornecer recomendações personalizadas para o usuário. Por exemplo, pode sugerir reduzir o tempo de uso de certos aparelhos, como o ar-condicionado ou a lâmpada, e identificar maneiras de economizar energia sem comprometer o conforto. Todo este processo terá o envio de e-mails, sinalização na aplicação mobile e web, assim, o cliente fica por dentro de tudo que acontece em sua residência, sem que ele busque estas informações.

## 7. Análise para identificar as imagens

A partir do modelo treinado, ao importar uma imagem para nossa base de cadastro, o sistema utilizará esse modelo para identificar os equipamentos eletrônicos presentes na residência do cliente. Será possível avaliar, em cada ambiente, junto com as informações de consumo, onde estão concentrados os maiores gastos e quais equipamentos demandam atenção.

## Conclusão

Este projeto tem como objetivo utilizar técnicas de aprendizado de máquina para criar um modelo preditivo que ajude os usuários a entender seu consumo de energia e a otimizar seus hábitos para reduzir custos. A previsão do consumo e o valor da conta de luz será baseada em dados históricos, e o modelo fornecerá recomendações personalizadas para o usuário, de forma a promover uma utilização mais eficiente da energia na residência.

## Base para cálculo de custos de energia

```bash
    https://www.enel.com.br/pt-saopaulo/Corporativo_e_Governo/tabela-de-tarifas.html
```

Para calcular os custos de energia em nosso modelo, utilizaremos os dados de consumo fornecidos pela Enel, com base na tarifa de energia elétrica vigente. Os dados de consumo serão extraídos a partir da fatura de energia da Enel, levando em consideração os valores de consumo em kWh (kilowatt-hora) e as modalidades tarifárias aplicáveis.

Nossa base de cálculo será o grupo padrão de tarifas, que considera as modalidades de consumo em ponta e fora ponta, onde o custo da energia varia de acordo com o horário de uso. Para simplificar, tomaremos como referência as tarifas médias para as diferentes faixas de consumo, considerando as modalidades tarifárias predominantes para o cliente em questão.

A partir dos valores de consumo em kWh e das tarifas vigentes, calcularemos o custo total de energia, considerando a tarifa de ponta (horários de maior demanda) e fora ponta (horários de menor demanda). Esses cálculos serão aplicados para estimar o impacto financeiro do consumo de energia, considerando as variáveis de custo determinadas pela Enel e as condições tarifárias específicas.

Em resumo, a partir dos dados de consumo fornecidos pela Enel e das tarifas definidas pela companhia, realizaremos os cálculos de custo de energia com base nas condições tarifárias mais apropriadas para nosso modelo de análise.

**1. Tarifa de Ponta e Fora Ponta**

A Enel cobra uma tarifa diferenciada para o consumo de energia durante o dia (ponta) e à noite (fora ponta). O valor por kWh pode variar de acordo com o horário de consumo:

Tarifa de Ponta (Horário de Pico): Geralmente, o custo por kWh é mais alto durante o dia, quando a demanda de energia é maior.

Tarifa Fora Ponta (Horário de Baixa Demanda): O custo por kWh é mais baixo durante a noite e finais de semana, quando a demanda por energia é menor.

**2. Custo de Demanda**

Além do consumo de energia, a Enel pode cobrar uma tarifa de demanda, que é uma taxa fixa com base na capacidade de energia contratada. O valor é calculado com base na potência máxima que o cliente pode utilizar, ou seja, a demanda contratada pela unidade consumidora.

**3. Impostos e Taxas Regulamentares**

Existem também impostos e taxas regulamentares que são aplicados sobre o custo da energia:

ICMS (Imposto sobre Circulação de Mercadorias e Serviços): É um imposto estadual que incide sobre o valor da energia elétrica.

PIS/COFINS: São contribuições federais que incidem sobre o preço da energia elétrica.

Taxa de Iluminação Pública (TIP): Em algumas localidades, pode haver uma cobrança adicional referente à iluminação pública, que também é repassada ao consumidor.

**4. Custo de Serviços e Leitura**

Além das tarifas de consumo e demanda, a Enel pode cobrar pela prestação de serviços, como a taxa de leitura do medidor, manutenção de redes e outros serviços associados à distribuição de energia.

**5. Ajustes e Encargos Setoriais**

A Enel também aplica ajustes e encargos setoriais relacionados ao setor elétrico, como encargos de serviços do sistema elétrico e taxas de compensação de energia (para garantir a distribuição de energia de forma eficiente, mesmo com eventuais falhas ou interrupções).

**6. Tarifa de Energia Contratada**

Caso o cliente tenha contratado uma tarifa diferenciada (como uma tarifa verde, azul ou branca), o custo pode variar conforme o modelo escolhido. A tarifa branca, por exemplo, tem diferentes custos por kWh em horários específicos, podendo ser mais vantajosa dependendo do padrão de consumo.

## Como utilizamos esses custos no cálculo

***Custo de Consumo***

Vamos multiplicar os kWh consumidos nos horários de ponta e fora ponta pelas tarifas correspondentes.

***Custo de Demanda*** 

Para clientes com cobrança de demanda, será aplicada a tarifa de demanda com base na capacidade contratada.

***Impostos e Taxas***

Os impostos e taxas serão adicionados ao cálculo conforme as alíquotas vigentes.

***Ajustes e Encargos***

Incluiremos esses valores no cálculo para garantir que o custo total seja o mais próximo possível do valor real da fatura.

Com esses custos detalhados, podemos calcular de forma precisa o custo de energia com base no consumo real e nas tarifas da Enel.
