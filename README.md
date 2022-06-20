# Backend coding challenge UP42
## How to run
There are two ways to start a service: with Gradle or with Docker.

### Docker
To start with Docker you need to execute the command:
```docker-compose up``` from the root directory. 

When the service is up, it is available with the address http://localhost/v1/features/

### Gradle
To start with Gradle you need to execute the command:
```gradlew bootRun``` from the root directory.

When the service is up, it is available with address http://localhost:8080/v1/features/

## Documentation
To see the end-user documentation visit the address http://localhost/swagger-ui/index.html (or http://localhost:8080/swagger-ui/index.html accordingly).

## Comments 
Here I just want to mention several things about implementation:

1. It's my first code in Kotlin, so please bear with me :)
2. I decided to keep the existing data parser as it is and just wrap it in the repository class. The idea behind it is to encapsulate the data layer. With the current solution, it is easy to replace the data source to any DB with Spring Boot.
3. I extended the existing model only with the Quicklook field, but the idea here is to have a normal business object with all necessary data.
4. I realise that going through the list of data to find elements by id is not optimal, but since it's a kinda mock DB I kept it as it is.
5. I decided not to put any Java docks to the Service, because for now business logic there is pretty straightforward.
6. Since I extended the existing FeatureControllerTest and didn't mock anything there, let's consider its integration tests.
7. In the real web service, it would be nice to have pagination and filtering in the /features/ endpoint. At the moment I decided not to implement them.
8. In a real web service, it is necessary to have authentication. I would implement it with a separate instance of Keycloak and integration via Spring Security or using Nginx as a proxy server.  