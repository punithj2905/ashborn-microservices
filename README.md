Ashborn Microservices Backend System
Ashborn is a distributed e-commerce backend system built using the Spring ecosystem. 
The project follows a microservices architecture with independent services for customer, product, order, payment, and notification management.
The system uses an API Gateway for centralized request routing and integrates asynchronous communication using Kafka messaging. 
Security is implemented using OAuth2 resource server authentication. Distributed tracing is enabled using Zipkin monitoring.
The entire infrastructure is containerized using Docker and Docker Compose, including databases, messaging brokers, mail testing service, and authentication server.
Technologies used include Java, Spring Boot, MySQL, PostgreSQL, Kafka, Keycloak, Docker, MailDev, and Zipkin.
To run the project, create a .env file in the project root and configure required credentials locally. Then start infrastructure services using Docker Compose.
Future improvements may include circuit breaker pattern implementation, caching optimization, and cloud deployment enhancements.
