<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Kotlin-2.1-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Gradle-8.12-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle"/>
  <img src="https://img.shields.io/badge/Virtual%20Threads-Enabled-00C853?style=for-the-badge" alt="Virtual Threads"/>
  <img src="https://img.shields.io/badge/OpenAPI-3.0-85EA2D?style=for-the-badge&logo=openapiinitiative&logoColor=black" alt="OpenAPI"/>
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="License"/>
</p>

<p align="center">
  <img src="https://img.shields.io/github/stars/joaorobertopereira/template-spring-boot?style=social" alt="Stars"/>
  <img src="https://img.shields.io/github/forks/joaorobertopereira/template-spring-boot?style=social" alt="Forks"/>
  <img src="https://img.shields.io/github/last-commit/joaorobertopereira/template-spring-boot" alt="Last Commit"/>
  <img src="https://img.shields.io/github/repo-size/joaorobertopereira/template-spring-boot" alt="Repo Size"/>
</p>

---

# Template Spring Boot - Kotlin

Template de referencia para construcao de APIs REST com **Spring Boot 3.5**, **Kotlin** e arquitetura modular. Pronto para uso com **Virtual Threads**, **Swagger/OpenAPI**, **contract-first code generation** e **spec-driven development**.

> Use este repositorio como ponto de partida para seus projetos. Clone, renomeie e comece a desenvolver.

---

## Indice

- [Arquitetura](#arquitetura)
- [Tech Stack](#tech-stack)
- [Pre-requisitos](#pre-requisitos)
- [Quick Start](#quick-start)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Usar Este Template](#como-usar-este-template)
- [Contract-First com OpenAPI](#contract-first-com-openapi)
- [Spec-Driven Development](#spec-driven-development)
- [Virtual Threads](#virtual-threads)
- [Swagger UI](#swagger-ui)
- [Regras de Dependencia entre Modulos](#regras-de-dependencia-entre-modulos)
- [Comandos Uteis](#comandos-uteis)
- [Contribuindo](#contribuindo)
- [Contato](#contato)
- [Licenca](#licenca)

---

## Arquitetura

O projeto segue uma **arquitetura modular em camadas**, separando responsabilidades de forma clara:

```
template-spring-boot/
├── api/              # Controllers, Spring Boot main, OpenAPI spec
├── application/      # Use cases e servicos de aplicacao
├── core/             # Entidades de dominio, value objects, ports (interfaces)
├── cross-cutting/    # Exception handler global, logging, configs compartilhados
├── data/             # JPA entities, repositories, configs de banco
├── infra/            # Clients HTTP, integracao com servicos externos
└── specs/            # Spec document (JSON) gerado do PRD
```

### Diagrama de Dependencias

```
                    ┌─────────────┐
                    │     api     │
                    └──────┬──────┘
           ┌───────┬───────┼───────┬────────┐
           v       v       v       v        v
      ┌────────┐ ┌─────┐ ┌──────┐ ┌─────┐ ┌──────────────┐
      │  app   │ │data │ │infra │ │core │ │cross-cutting │
      └───┬──┬─┘ └──┬──┘ └──┬───┘ └─────┘ └──────────────┘
          │  │      │       │
          │  └──────┼───────┘
          v         v
      ┌──────┐  ┌──────────────┐
      │ core │  │cross-cutting │
      └──────┘  └──────────────┘
```

---

## Tech Stack

| Tecnologia | Versao | Descricao |
|---|---|---|
| **Kotlin** | 2.1.10 | Linguagem principal |
| **Java** | 21 (LTS) | JDK com suporte a Virtual Threads |
| **Spring Boot** | 3.5.0 | Framework web |
| **Gradle** | 8.12 | Build tool (multi-module) |
| **Spring Data JPA** | - | Persistencia |
| **PostgreSQL** | - | Banco de dados (producao) |
| **H2** | - | Banco de dados (desenvolvimento) |
| **SpringDoc OpenAPI** | 2.8.4 | Swagger UI e documentacao |
| **OpenAPI Generator** | 7.12.0 | Geracao de codigo contract-first |
| **Spring Actuator** | - | Observabilidade |
| **Virtual Threads** | - | Concorrencia leve nativa do Java 21 |

---

## Pre-requisitos

- **Java 21** (ou superior)
- **Git**

> O Gradle wrapper ja esta incluido no projeto. Nao precisa instalar o Gradle.

---

## Quick Start

### 1. Clone o repositorio

```bash
git clone https://github.com/joaorobertopereira/template-spring-boot.git
cd template-spring-boot
```

### 2. Build do projeto

```bash
./gradlew build
```

Se voce tem multiplas versoes do Java instaladas:

```bash
JAVA_HOME=/path/to/jdk-21 ./gradlew build
```

### 3. Execute a aplicacao

```bash
./gradlew :api:bootRun
```

### 4. Acesse

| URL | Descricao |
|---|---|
| http://localhost:8080/swagger-ui.html | Swagger UI |
| http://localhost:8080/api-docs | OpenAPI JSON |
| http://localhost:8080/api/health | Health check |
| http://localhost:8080/actuator | Actuator endpoints |

---

## Estrutura do Projeto

### `core/` - Dominio

O coracao da aplicacao. **Zero dependencias de framework.**

```
core/src/main/kotlin/com/template/core/
├── domain/
│   ├── entity/         # Entidades de dominio
│   └── valueobject/    # Value objects
└── port/
    ├── input/          # Portas de entrada (interfaces para use cases)
    └── output/         # Portas de saida (interfaces para repositorios/clients)
```

### `application/` - Casos de Uso

Orquestra as operacoes de negocio. Depende apenas de `core` e `cross-cutting`.

```
application/src/main/kotlin/com/template/application/
└── usecase/            # Implementacoes dos casos de uso
```

### `data/` - Persistencia

Implementa as portas de saida do `core` com Spring Data JPA.

```
data/src/main/kotlin/com/template/data/
├── entity/             # Entidades JPA
├── repository/         # Spring Data repositories
└── config/             # Configuracoes de banco
```

### `infra/` - Infraestrutura

Integracao com servicos externos (APIs, mensageria, etc).

```
infra/src/main/kotlin/com/template/infra/
├── client/             # Clients HTTP para servicos externos
└── config/             # Configuracoes de infraestrutura
```

### `cross-cutting/` - Aspectos Transversais

Funcionalidades compartilhadas entre todos os modulos.

```
cross-cutting/src/main/kotlin/com/template/crosscutting/
├── exception/          # GlobalExceptionHandler, BusinessException, NotFoundException
├── logging/            # Extensao logger() para Kotlin
└── config/             # Configuracoes globais
```

### `api/` - Camada de Apresentacao

Controllers que implementam as interfaces geradas pelo OpenAPI Generator.

```
api/src/main/kotlin/com/template/
├── TemplateApplication.kt          # Spring Boot main
└── api/
    ├── controller/                 # Controllers implementando interfaces geradas
    └── dto/
        ├── request/                # DTOs de request (quando nao gerados)
        └── response/               # DTOs de response (quando nao gerados)
api/src/main/resources/
├── application.yml                 # Configuracoes da aplicacao
└── openapi/
    └── api.yml                     # Contrato OpenAPI (source of truth)
```

### `specs/` - Documentacao de Especificacao

```
specs/
├── spec-schema.json                # JSON Schema do documento de spec
├── spec-example.json               # Exemplo preenchido
└── prd-to-spec-prompt.md           # Prompt para converter PRD em spec
```

---

## Como Usar Este Template

### Passo 1 - Clone e Renomeie

```bash
# Clone
git clone https://github.com/joaorobertopereira/template-spring-boot.git meu-projeto
cd meu-projeto

# Remova o historico git e inicie do zero
rm -rf .git
git init
```

### Passo 2 - Renomeie o Pacote

Substitua `com.template` pelo pacote do seu projeto em todos os arquivos:

- `build.gradle.kts` (root) - altere o `group`
- Renomeie os diretorios `com/template/` em cada modulo
- Atualize os `package` nos arquivos `.kt`
- Atualize `apiPackage` e `modelPackage` no `api/build.gradle.kts`

### Passo 3 - Configure o Banco de Dados

Edite `api/src/main/resources/application.yml` com as credenciais do seu banco:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/meu_banco
    username: meu_usuario
    password: minha_senha
```

### Passo 4 - Defina o Contrato da API

Edite `api/src/main/resources/openapi/api.yml` com os endpoints do seu projeto. Ao rodar `./gradlew build`, as interfaces e DTOs serao gerados automaticamente.

### Passo 5 - Implemente os Controllers

Crie controllers que implementam as interfaces geradas:

```kotlin
@RestController
class ResourceController(
    private val createResourceUseCase: CreateResourceUseCase
) : ResourceApi {

    override fun createResource(request: CreateResourceRequest): ResponseEntity<ResourceResponse> {
        val result = createResourceUseCase.execute(request.toDomain())
        return ResponseEntity.status(HttpStatus.CREATED).body(result.toResponse())
    }
}
```

### Passo 6 - Implemente de Dentro para Fora

Siga a ordem recomendada:

1. **core** - Defina entidades de dominio e interfaces (ports)
2. **application** - Implemente os use cases
3. **data** - Implemente os repositorios
4. **infra** - Implemente integracao com servicos externos
5. **api** - Implemente os controllers

---

## Contract-First com OpenAPI

Este template utiliza a abordagem **contract-first**: voce define o contrato da API no YAML e o codigo e gerado automaticamente.

### Como funciona

```
api.yml (voce escreve) → OpenAPI Generator Plugin → Interfaces + DTOs (gerados)
                                                          ↓
                                               Controllers (voce implementa)
```

### Arquivo de contrato

`api/src/main/resources/openapi/api.yml`

### Codigo gerado (no build)

```
api/build/generated/openapi/src/main/kotlin/
├── com/template/api/generated/
│   ├── controller/
│   │   ├── HealthApi.kt          # Interface gerada
│   │   └── ResourceApi.kt        # Interface gerada
│   └── model/
│       ├── CreateResourceRequest.kt
│       ├── ResourceResponse.kt
│       └── ...
└── org/openapitools/
    └── SpringDocConfiguration.kt  # Config do Swagger gerada
```

### Gerar apenas as interfaces (sem build completo)

```bash
./gradlew :api:openApiGenerate
```

---

## Spec-Driven Development

O template inclui uma estrutura para desenvolvimento orientado a especificacao:

### Fluxo Completo

```
Brainstorm → PRD → spec.json → Desenvolvimento
```

1. **Brainstorm** - Use ChatGPT com o scaffold template para criar o PRD
2. **PRD** - Expanda o scaffold com o Claude para gerar o PRD completo
3. **Spec** - Converta o PRD para `specs/spec.json` usando `specs/prd-to-spec-prompt.md`
4. **Desenvolvimento** - Use o `spec.json` como referencia unica

### O que contem o spec.json

| Secao | Descricao |
|---|---|
| `metadata` | Nome do projeto, versao, status, autores |
| `executive_summary` | Visao, objetivos e criterios de sucesso |
| `problem_statement` | Problema, dores e oportunidade |
| `solution_overview` | Conceito, diferenciadores e decisoes tecnicas |
| `personas` | Perfis de usuarios com necessidades e dores |
| `technical_architecture` | Componentes mapeados para os modulos do projeto |
| `functional_requirements` | User stories com IDs, prioridades e criterios de aceite |
| `api_specifications` | Contratos dos endpoints |
| `data_models` | Entidades com campos, tipos e relacionamentos |
| `implementation_plan` | Fases, tasks com estimativas e riscos |
| `success_metrics` | KPIs com metas e intervalos de revisao |

---

## Virtual Threads

O template vem com **Virtual Threads** habilitadas nativamente via configuracao:

```yaml
spring:
  threads:
    virtual:
      enabled: true
```

Virtual Threads (Project Loom) permitem lidar com milhares de requisicoes concorrentes sem o overhead de threads tradicionais. Cada requisicao roda em uma virtual thread leve gerenciada pela JVM.

Para verificar que esta funcionando, acesse `/api/health` e observe o campo `thread`:

```json
{
  "status": "UP",
  "thread": "VirtualThread[#31]/runnable@ForkJoinPool-1-worker-1"
}
```

---

## Swagger UI

Acesse `http://localhost:8080/swagger-ui.html` para visualizar e testar todos os endpoints da API.

A documentacao e gerada automaticamente a partir do contrato `api.yml`.

| Endpoint | Descricao |
|---|---|
| `/swagger-ui.html` | Interface visual do Swagger |
| `/api-docs` | Especificacao OpenAPI em JSON |

---

## Regras de Dependencia entre Modulos

| Modulo | Depende de | Responsabilidade |
|---|---|---|
| **core** | nenhum | Dominio puro, sem framework |
| **application** | core, cross-cutting | Orquestracao de use cases |
| **data** | core, cross-cutting | Persistencia com JPA |
| **infra** | core, cross-cutting | Integracao externa |
| **cross-cutting** | Spring Boot | Concerns transversais |
| **api** | todos | Entry point da aplicacao |

> O `core` nunca depende de nenhum outro modulo. Isso garante que a logica de dominio seja testavel e independente de framework.

---

## Comandos Uteis

```bash
# Build completo
./gradlew build

# Executar a aplicacao
./gradlew :api:bootRun

# Gerar interfaces do OpenAPI
./gradlew :api:openApiGenerate

# Executar testes
./gradlew test

# Limpar build
./gradlew clean

# Build de um modulo especifico
./gradlew :core:build
./gradlew :api:build
```

---

## Contribuindo

Contribuicoes sao bem-vindas! Sinta-se a vontade para:

1. Fazer um **fork** do projeto
2. Criar uma **branch** para sua feature (`git checkout -b feature/minha-feature`)
3. **Commitar** suas alteracoes (`git commit -m 'Adiciona minha feature'`)
4. Fazer **push** para a branch (`git push origin feature/minha-feature`)
5. Abrir um **Pull Request**

---

## Contato

Criado por **Joao Roberto Pereira**

- Email: joaorobertof@gmail.com
- GitHub: [github.com/joaorobertopereira](https://github.com/joaorobertopereira)

Se este template foi util para voce, deixe uma estrela no repositorio!

---

## Licenca

Este projeto esta licenciado sob a licenca MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
