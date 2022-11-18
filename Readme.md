# Cars API

This is my proposed solution for the challenge to manage the creation and retrieval of instances which represent the information about cars of the carscompany fleet.
Welcome to this code and have fun managing cars!

## Assumptions, compromises and good-to-knows

The maven plugin openapi generator has been used to generate a server stub for the API.
The server code implements the mentioned server stub.
In order to speed up the development, the testing of the features is done by unit and integration tests. For next releases, it will be necessary to do more Unit testing and less integration testing, in accordance with the established best practices (testing pyramid).
Invested amount of time to implement this task: between 8 and 10 hours.

## Getting started

To build the project, it is necessary to run the following commands in the project root:

```
gradle build
```

To create a docker image called carsimage, using the description from Dockerfile:
```
docker build -f ./app/Dockerfile -t carsimage .
```

To run create carscontainer using carsimage:
```
docker run --name carscontainer -p 8080:8080 carsimage:latest
```

After the steps already mentioned, the system should be up und running. Now we can launch the integration tests with the following command:

```
gradle integrationTest
```

To stop the container carscontainer:
```
docker stop carscontainer
```

To delete unused resources like images and containers:
```
docker system prune
```

## Most important technologies and tools used

- Java 8
- Spring boot for the server framework
- Spring data JPA as specification of the database management
- H2 as a database engine
- OpenApi 3.0.3 to specify the implemented REST API
- Docker for containerizing the application
- Gradle for building the project
- Rest assured to test the API
- Lombok to generate code

## About the API specification

The Cars API is defined in the following OpenAPI file:

[OpenApi Specification for Cars REST API](src/main/resources/cars-api-v1.yaml)

This API specification has been used to generate a server stub. The interface CarsApiDelegate
contains the API contract, which is then implemented in the backend, in this class ```CarsApiImpl```.

## Sample car in JSON

```
{
    “id”: 12345,
    “brand”: “Flexa”,
    “licensePlate”: “L-CS8877E”,
    “status”: “available”,
    “createdAt”: “2017-09-01T10:23:47.000Z",
    “lastUpdatedAt”: “2022-04-15T13:23:11.000Z"
}
```

### Important for IntelliJ: in order for the IDE to recognize the generated class as source code which is used to build the project, after generating the API, the IDE needs to be restarted. Eventually, this can also be done by going to the Gradle tab and clicking on Reload all projects.

## Possible developments on the functional side

Endpoints to delete entities, to filter/query/search, etc.

### In order to be production-ready, the following matters still need to be addressed
Better test coverage  
Use more secure dependencies for OpenApi generator  
Scalability  
CI/CD, including the following stages: code fetch, vulnerabilities-analysis, static code analysis, compile, run dev tests, deploy on test environment, run API tests, performance tests, deploy on prod, create RELEASE)    
Kubernetes  
New gradle task to independently run integrationTest with one single command and cleanup after it  
Abstract the data management to an interface  
Metrics and Health endpoints  
Schema validation  
HTTPS  
Security: check the parameters sent to the API for possible injection attacks.    
Authentication/authorization (scopes, roles)  
Monitoring for the different stages  
Document new features/bugfixes released    
Database indexing  
