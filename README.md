# Teste Prático - Prothera

## Sobre o Projeto

Aplicação console que gerencia uma lista de funcionários, realizando operações como inserção, remoção, agrupamento, formatação e cálculos salariais.

## Tecnologias Utilizadas

- **Java 21**
- **Gradle 9.x**
- **Lombok** — para reduzir código boilerplate (getters/setters)
- **JUnit 5** — testes unitários

## Estrutura do Projeto

```
src/main/java/br/com/prothera/
├── Principal.java                  # Classe principal com as ações solicitadas
├── entity/
│   ├── Pessoa.java                 # Classe base com nome e data de nascimento
│   └── Funcionario.java            # Herda Pessoa, adiciona salário e função
├── factory/
│   └── FuncionarioFactory.java     # Fábrica para criação dos funcionários
└── util/
    └── FormatadorUtil.java         # Utilitário de formatação monetária
```

## Como Executar

```bash
./gradlew run
```

## Como Rodar os Testes

```bash
./gradlew test
```

## Autor

Luan Alves
