
---

# **JavaInsight – Technical Design Document**

---

## **1. System Overview**

**JavaInsight** is a cloud-native RESTful API service designed to generate and deliver Java programming facts either randomly or based on a specific topic. It uses **Spring Boot** for backend API development and leverages **Azure AI Foundry** to generate content using large language models. The application may optionally persist frequently accessed or user-approved facts in a cloud database for future retrieval. The service will be deployed on **Microsoft Azure** using Azure App Service and will integrate with a frontend client built separately.

---

## **2. Functional Requirements**

* The system shall expose a REST API endpoint to return a **random Java fact**.
* The system shall expose a REST API endpoint to return a **topic-specific Java fact**.
* The system shall invoke **Azure AI Foundry** to dynamically generate Java facts.
* The system shall optionally store AI-generated facts in a persistent storage layer (e.g., Azure Cosmos DB or PostgreSQL).
* The system shall log and monitor application health via **Azure Monitor**.
* The system shall support potential frontend integration via JSON-formatted API responses.

---

## **3. Non-Functional Requirements**

* **Scalability**: The system must support a growing number of concurrent API calls.
* **Availability**: The application should maintain 99.9% uptime on Azure App Service.
* **Latency**: Average API response time should not exceed 500ms excluding AI response time.
* **Security**: All endpoints should be protected from unauthorized access and malicious injection.
* **Maintainability**: The codebase must follow clean architecture and be modular for ease of updates.
* **Observability**: Logs and metrics must be streamed to Azure Application Insights.

---

## **4. Architecture Diagram**

```
        [Frontend Client (React/Vue)]
                   |
                   v
         [JavaInsight REST API - Spring Boot]
                   |
        +--------------------------+
        |   Azure AI Foundry (LLM) |
        +--------------------------+
                   |
                   v
     [Optional: Azure Cosmos DB / PostgreSQL]
                   |
                   v
           Azure App Service (Backend)
                   |
                   v
         Azure Monitor & App Insights
```

---

## **5. Component Descriptions**

### 5.1 Frontend

* **Role**: Sends user requests for facts and displays API responses.
* **Tech**: React.js or Vue.js (not in scope here but supported by backend).
* **Hosting**: Azure Static Web Apps or similar.

### 5.2 Backend (Spring Boot)

* **Role**: Core REST API engine to handle requests and responses.
* **Endpoints**:

  * `/api/facts/random` → Generates a random Java fact.
  * `/api/facts/topic?name=OOP` → Generates a fact about a specific topic.
* **Modules**:

  * Controller Layer
  * Service Layer
  * Azure Foundry Integration Module
  * Optional Persistence Layer

### 5.3 AI Model (Azure AI Foundry)

* **Role**: Generate Java facts using prompt-driven requests.
* **Model**: Uses a large language model capable of understanding developer context (e.g., OpenAI GPT via Azure).

### 5.4 Storage (Optional)

* **Role**: Store reusable facts for performance and moderation.
* **Tech Options**:

  * Azure Cosmos DB (NoSQL)
  * Azure Database for PostgreSQL (Relational)

### 5.5 Deployment Infrastructure

* **Role**: Host and scale the backend service.
* **Tech**: Azure App Service + Azure DevOps / GitHub Actions
* **Monitoring**: Azure Monitor, Application Insights

---

## **6. Data Flow**

1. **User/Frontend** sends request to `/api/facts/random` or `/api/facts/topic?name=X`.
2. **Backend API** receives and validates request.
3. **Azure AI Foundry** is called with the constructed prompt.
4. AI responds with a fact.
5. (Optional) Fact is stored in database.
6. API returns fact to frontend.

---

## **7. API Design**

| Endpoint            | Method | Description                           | Parameters                     |
| ------------------- | ------ | ------------------------------------- | ------------------------------ |
| `/api/facts/random` | GET    | Returns a random Java fact            | None                           |
| `/api/facts/topic`  | GET    | Returns a fact about a specific topic | `name` (query param, required) |

**Example Request**:
`GET /api/facts/topic?name=Inheritance`
**Response**:

```json
{
  "topic": "Inheritance",
  "fact": "In Java, inheritance allows one class to acquire the properties and behaviors of another class using the 'extends' keyword."
}
```

---

## **8. Security Considerations**

* Input validation and sanitization for all request parameters.
* Rate limiting and throttling using Spring Cloud Gateway or Azure API Management.
* API key or OAuth2 protection for external use.
* Secure AI prompt structure to avoid prompt injection.
* HTTPS enforcement and TLS encryption.

---

## **9. Future Enhancements**

* Daily fact email subscription service.
* Admin dashboard to moderate and approve facts.
* Add support for facts about other programming languages.
* Use of Redis for caching high-frequency facts.
* Integration with Microsoft Teams or Slack for daily developer fact feeds.

---

## **10. Technologies & Tools Used**

| Category            | Technology                          |
| ------------------- | ----------------------------------- |
| Backend             | Java 17+, Spring Boot 3.x           |
| AI                  | Azure AI Foundry, Azure OpenAI      |
| Storage             | Azure Cosmos DB / PostgreSQL        |
| Deployment          | Azure App Service                   |
| DevOps              | GitHub Actions / Azure DevOps       |
| Monitoring          | Azure Monitor, Application Insights |
| Frontend (External) | React.js / Vue.js                   |
| Testing             | JUnit, Postman                      |

---

## **11. DevOps & Deployment Pipeline (on Azure)**

### CI/CD Flow (GitHub Actions or Azure DevOps):

1. **Code Push** → GitHub or Azure Repos
2. **CI Build Pipeline**:

   * Compile and test Java code
   * Generate JAR artifact
3. **CD Pipeline**:

   * Deploy JAR to Azure App Service
   * Environment: `dev` → `prod`
4. **Monitor**:

   * Logs and telemetry sent to Azure Monitor
   * Alerts for uptime and error rates

