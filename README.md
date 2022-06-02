# Backend coding challenge UP42
## How to run
There are 2 ways to atart service: with Gradle or with Docker. 

### Docker
To start with docker you need to execute the command:
```docker-compose up``` from the root directory. 

When service is up, it is available with address http://localhost/v1/features/

### Gradle
To start with Gradle you need to execute the command:
```gradlew bootRun``` from the root directory.

When service is up, it is available with address http://localhost:8080/v1/features/

## Documentation
To see the end-user documentation visit the address http://localhost/swagger-ui/index.html (or http://localhost:8080/swagger-ui/index.html accordingly).

## Comments 
Here I just want to mention several things about implementation:

1. It's my first code in Kotlin, so please bear with me :)
2. I decided to keep existing data parser as it is and just wrap it to repository class. The idea behind is to encapsulate data layer. With current solution in is easy to replace data source to any DB with Spring Boot. 
3. I realise that go through the list of data to find element by id is not optimal, but since it's kinda mock DB I kept it as it is.
4. I decided not to put any Java docks to the Service, because for now business logic there is pretty straightforward.   
5. Since I extended existing FeatureControllerTest and didn't mock anything there, let's consider it integration tests.
6. In the real web-service it would be nice to have pagination and filtering in the /features/ endpoint. At the moment I decided not implement them.
7. In the real web-service it is necessary to have authentication. I would implement it with separate instance of Keycloak and integration via Spring Security or using Nginx as a proxy server.  