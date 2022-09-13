# Cars API 

This is my proposed solution for the challenge to manage the creation and retrieval of instances which represent the information about cars of the Clevershuttle fleet.
Welcome to this code and have fun managing cars!

## Assumptions, compromises and good-to-knows

The maven plugin openapi generator has been used to generate a server stub for the API. 
The server code implements the mentioned server stub.
The testing of the features is done by unit and integration tests.

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

To stop the container carscontainer:
```
docker stop carscontainer
```

## Most important technologies and tools used

- Spring boot for the server framework.
- Spring data JPA as specification of the database management
- H2 as a database engine
- OpenApi 3.0.3 to specify the implemented REST API.
- Docker for containerizing the application
- Gradle for building the project

## About the API specification

The Cars API is defined in the following OpenAPI file:

[OpenApi Specification for Cars REST API](src/main/resources/cars-api-v1.yaml)

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

