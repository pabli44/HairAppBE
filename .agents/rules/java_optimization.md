# Rule: Java Optimization & Token Saving
Activation: always on

## Context
This is a Java project. Context windows and token usage must be minimized strictly.

## Directory Isolation & Execution
- NEVER scan or read directories like `target/`, `build/`, `.gradle/`, `.settings/`, or `.metadata/`.
- For execution and dependency management, strictly use: `./mvnw` (if Maven wrapper exists) or `./gradlew` (if Gradle wrapper exists).
- Avoid full project rebuilds unless explicitly requested.

## Code Generation Constraints
- Provide ONLY the modified or requested method/class. NEVER output the entire unmodified file.
- DO NOT generate boilerplate code unless necessary (skip getters, setters, and constructors if they can be implied or use Lombok).
- Use concise Java features where applicable (e.g., Streams, Lambdas, Var keyword for local variables, Records instead of POJOs).
- Omit Javadoc or long comments in the code outputs unless strictly requested.

## Response Style
- Output raw code blocks immediately.
- Explanations must be maximum 2 sentences long.
