# Project: template-kotlin

## Architecture

Multi-module Spring Boot 3.5 Kotlin project with layered architecture:

- **api** — REST controllers, request/response DTOs, Spring Boot main class
- **application** — Use cases, application services (orchestration)
- **core** — Domain entities, value objects, port interfaces (input/output). Zero framework dependencies
- **cross-cutting** — Shared concerns: exception handling, logging, configs
- **data** — JPA entities, Spring Data repositories, database configs
- **infra** — External service clients, infrastructure configs

## Dependency Rules

core → (no dependencies)
application → core, cross-cutting
data → core, cross-cutting
infra → core, cross-cutting
api → all modules

## Spec-Driven Development

This project uses a **spec document** (`specs/spec.json`) as the single source of truth for development.

### How to use the spec:
1. Before implementing any feature, check `specs/spec.json` for requirements, API specs, and data models
2. Each functional requirement has an ID (FR-XXX), priority, user story, and acceptance criteria
3. API endpoints are defined in `api_specifications` — follow the contracts exactly
4. Data models in the spec map to JPA entities in the `data` module and domain entities in `core`
5. Components in `technical_architecture` specify which module they belong to

### Workflow:
1. PRD brainstorm → generates PRD document
2. PRD → converted to `specs/spec.json` using `specs/prd-to-spec-prompt.md`
3. `specs/spec.json` → used as development reference
4. Schema validation: `specs/spec-schema.json`

## Tech Stack

- Kotlin 2.1 / Java 21
- Spring Boot 3.5 (virtual threads enabled)
- Gradle 8.12 (multi-module)
- Spring Data JPA (PostgreSQL + H2 for dev)
- Spring Actuator for observability

## Build

```bash
./gradlew build
```

Requires Java 21. If multiple Java versions installed, set JAVA_HOME:
```bash
JAVA_HOME=/path/to/jdk-21 ./gradlew build
```
