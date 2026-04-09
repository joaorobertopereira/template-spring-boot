# PRD to Spec Converter Prompt

Use this prompt to convert a PRD (Product Requirements Document) into the structured JSON spec format.

---

## Prompt

```
You are an expert product manager and software architect. Convert the following PRD into a structured JSON spec document that follows the schema below.

IMPORTANT RULES:
1. Map every requirement, user story, and feature from the PRD into the JSON structure
2. Assign priority levels (P0 = must have, P1 = should have, P2 = nice to have, P3 = future)
3. Map components to the correct module: api, application, core, infra, cross-cutting, data
4. Generate unique IDs for functional requirements (FR-001, FR-002, etc.)
5. Write acceptance criteria in Given/When/Then format
6. Keep the implementation plan realistic with effort estimates
7. Extract specific, measurable KPIs for success metrics

The output must be valid JSON following this schema:
[PASTE THE CONTENTS OF spec-schema.json HERE]

PRD TO CONVERT:
[PASTE YOUR PRD HERE]

Output ONLY the JSON. No explanations, no markdown wrapping.
```

## How to Use

1. Generate your PRD using the brainstorm process (ChatGPT scaffold + Claude expansion)
2. Copy the prompt above
3. Paste the `spec-schema.json` contents where indicated
4. Paste your PRD where indicated
5. Run in Claude or ChatGPT
6. Save the output as `specs/spec.json` in your project
7. Use `spec.json` as the development reference
