# JavaInsights

A Spring Boot application that provides interesting Java programming facts using LangChain4j and Azure OpenAI.

## Requirements

- Java 17 or higher
- Maven 3.8+
- Azure OpenAI API access

## Setup

1. Clone the repository
2. Set up the following environment variables:
   ```bash
   export AZURE_OPENAI_API_KEY=your_api_key
   export AZURE_OPENAI_ENDPOINT=your_endpoint
   export AZURE_OPENAI_DEPLOYMENT_NAME=your_deployment_name
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Get Random Java Fact
```
GET /api/facts/random
```

Response:
```json
{
  "topic": "Random",
  "fact": "A randomly generated Java programming fact"
}
```

### Get Topic-Specific Java Fact
```
GET /api/facts/topic?name={topic}
```

Response:
```json
{
  "topic": "Inheritance",
  "fact": "A fact about Java inheritance"
}
```

## Features

- RESTful API endpoints
- Integration with Azure OpenAI via LangChain4j
- In-memory caching of facts
- Input validation
- Unit tests
- Clean architecture
