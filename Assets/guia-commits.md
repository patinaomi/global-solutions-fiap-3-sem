## Mensagens de Commits - Conventional Commits

Para melhorar a visibilidade de alterações e padronizar as mensagens de commits, adotamos a seguinte estrutura:

```
<Tipo (Escrito em letras maiúsculas)>: Descrição das alterações

Corpo com mais detalhes (opcional)

Footer com notas úteis (opcional)

```

### Tipos

Aqui estão os tipos que podemos usar:

- **Build** – Mudanças no sistema de build ou dependências externas (gulp, npm)
- **Chore** – Mudança interna (Atualização ou adição de dependências, alteração em configurações de Linters, etc)
- **Docs** – Mudanças em documentação, README, Changelog, etc.
- **Feat** – Nova funcionalidade (feature)
- **Fix** – Conserto de bugs e problemas
- **Perf** – Mudança(s) para aumento de performance
- **Refactor** – Mudança(s) de código que não conserta bugs nem adicionam nova funcionalidade.
- **Style** – Mudança(s) que não afetam o significado do código (espaços em branco, formatação, etc.) ou estilos no front-end
- **Test** – Adição ou correção de testes
- **BREAKING CHANGE** – Mudança(s) maiores, que tiram a compatibilidade com versões anteriores.