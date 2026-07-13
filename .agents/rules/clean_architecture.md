# Rule: Clean Architecture & Clean Code Compliance
Activation: always on

## Architectural Constraints
- Strictly enforce Separation of Concerns based on Clean Architecture.
- Code must be decoupled into independent layers: Domain (Entities/Use Cases), Data (Repositories/Data Sources), and Presentation/Infrastructure.
- Inner layers (Domain) must NEVER depend on outer layers (Frameworks, Databases, UI).
- Use Interfaces/Inversion of Control (IoC) to communicate between layers.

## Clean Code & SOLID Standards
- Follow SOLID principles strictly (Single Responsibility is top priority).
- Functions and methods must do exactly ONE thing and be short.
- Use meaningful, self-explanatory names for variables, classes, and methods. Do not use abbreviations.
- Prefer explicit error handling and custom exceptions over returning null or generic exceptions.
- Avoid deep nesting (maximum 2 levels). Use guard clauses and early returns.

## Output Directive
- Apply these principles natively in every code snippet generated.
- Do not explain why the architecture was used unless requested.
