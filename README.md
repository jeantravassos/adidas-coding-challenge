# adidas-coding-challenge
## subscriptions-service

Adidas coding challenge 2022: develop a subscription system composed by 3 microservices

- Public service
- Subscription service
- Email service

### Setting up environment

1 Clone the repository

2 Enter each project
~~~~
cd {name}-service
~~~~

2.1 First, compile
~~~~
mvn clean install -DskipTests=true 
~~~~
2.2 Build the docker images by running
~~~~
docker build -t adidas/{name}-service . 
~~~~

### Running
Move back to the root directory, the same location of the 'docker-compose.yml' file.

With docker running locally, execute the docker-compose command:
~~~~
docker-compose up
~~~~

### Using it

Accessible pages:
| Documentation | Link |
| ------ | ------ |
| Swagger page | [http://localhost:{service-port}/docs/index.html] |
| Actuator | [localhost:{service-port}/actuator] |
| Zipkin | [http://localhost:9411/zipkin/] |
| Eureka | [http://localhost:8761/] |

## Technologies

The whole solution is based on Spring Boot using JDK 11.
The following libs/dependencies were also used:

- JUnit / Mockito - Integration and unit tests
- Slf4j - Logging
- Spring security - Basic authentication
- Netflix Eureka - Service discovery and registration
- OpenFeign - Integrations among Spring services
- Spring cloud LoadBalancer - Round robin balacing the load
- Zipkin and Sleuth - Distributable tracing
- Actuator - Service general information
- Swagger - API Docs
- Spring cloud Gateway - Route to APIs and provide cross cutting concerns
- Hystrix - Fault tolerance / circuit braker / resiliency
- Docker - Containerizing projects

## Endpoints

Examples of how to perform API calls:

| Endpoint | CURL |
| ------ | ------ |
| GET all subscriptions | curl -X GET "http://localhost:8080/api/public/subscriptions/" -H "accept: application/json" |
| GET subscription by ID | curl -X GET "http://localhost:8080/api/public/subscriptions/{id}" -H "accept: application/json" |
| POST create subscription | curl -X POST "http://localhost:8080/api/public/subscriptions/" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"firstName\":\"Adidas Zaragoza\",\"gender\":0,\"email\":\"zaragoza@adidas.com\",\"dateOfBirth\":\"1800-01-01\",\"consent\":true,\"newsletterId\":55}" |
| DELETE cancel subscription | curl -X DELETE "http://localhost:8080/api/public/subscriptions/cancel/{id}}" -H "accept: */*" |

## Architecture

As a Spring Boot solution, the diagram found in the root directory here represents well the implementation:

springboot-microservices-architecture.svg

## CI/CD

As a proposition for a CI/CD pipeline:
- Git is our repository
- For automation, Travis CI
  - Creating an account and specifying language, jdk and script 
  - And building via ./mvnw clean install
- CD by dockerizing using DockerHub
  - with jib-maven-plugin creating and pushing the image to our docker repository
- Code analysis for code quality
  - Using CodeCov and adding jacoco plugin in our maven projects
- Deploying to Kubernetes