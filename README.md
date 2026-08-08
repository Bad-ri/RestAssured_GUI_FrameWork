# RestRunner [ REST Assured GUI Test Runner Framework ]
**RestRunner** is a desktop-based API test execution framework built with **Java Swing** and powered by **REST Assured**. It provides an interactive desktop user interface to configure, trigger, monitor, and visualize API test executions without relying on third-party software.

## Project GUI

<img width="636" height="451" alt="image" src="https://github.com/user-attachments/assets/30a50266-652b-4846-a154-964722ec8635" />


API Test Automation Framework
REST Assured API testing engine with a Swing GUI runner, built on MVC (UI) + Service Object Model (API layer).
![Java](https://img.shields.io/badge/Java-17+-orange)
![RestAssured](https://img.shields.io/badge/RestAssured-5.4-green)
![TestNG](https://img.shields.io/badge/TestNG-7.9-blue)
![Allure](https://img.shields.io/badge/Reports-Allure-red)
![Status](https://img.shields.io/badge/status-in%20development-yellow)
---
Architecture
```mermaid
flowchart TD
    A["View<br/>Swing GUI"] --> B["Controller<br/>MainController"]
    B -->|Execute click| C["Engine<br/>TestNGRunner"]
    C --> D["Service Layer<br/>(Service Object Model)"]
    D --> E["RestAssured Core"]
    E --> F[("CBE / NBE APIs")]
    C --> G["GuiProgressListener"]
    G --> A
    C --> H[("Allure Results")]
    I["Config Manager"] --> D
    J["Data Provider"] --> D
```
Execution Flow
```mermaid
sequenceDiagram
    participant U as User
    participant V as View
    participant C as Controller
    participant E as Engine
    participant S as Service (SOM)
    participant R as RestAssured
    participant API as Bank API

    U->>V: Click Execute
    V->>C: Collect env / bank / API / data
    C->>E: run(ExecutionContext)
    E->>S: Invoke via TestNG DataProvider
    S->>R: Build & send request
    R->>API: HTTP call
    API-->>R: Response
    R-->>S: Validate response
    S-->>E: Pass / Fail
    E-->>V: Live progress update
    E->>E: Write Allure results
```
