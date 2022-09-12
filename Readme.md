//TODO test insertions with @ElementCollection columns!

# Fleetmanager API 

This is my proposed solution for the challenge to manage the creation and retrieval of instances which represent the information about cars of the Clevershuttle fleet.
Welcome to this code and have fun managing fleets!

## Assumptions, compromises and good-to-knows

The maven plugin openapi generator has been used to generate a server stub for the API. 
The server code implements the mentioned server stub.

## Getting started

To build the project, it is necessary to run the following commands in the project root:

```
gradle build
```

To create a docker image called fleetmanagerimage, using the description from Dockerfile:
```
docker build -f ./app/Dockerfile -t fleetmanagerimage .
```

To run create fleetmanagercontainer using fleetmanagerimage:
```
docker run --name fleetmanagercontainer -p 8080:8080 fleetmanagerimage:latest
```

To stop the container fleetmanagercontainer:
```
docker stop fleetmanagercontainer
```

## Most important technologies and tools used

- Spring boot for the server framework.
- Spring data JPA as specification of the database management
- H2 as a database engine
- OpenApi 3.0.3 to specify the implemented REST API.
- Docker for containerizing the application
- Gradle for building the project

## About the API specification

The Fleetmanager API is defined in the following OpenAPI file:

[OpenApi Specification for Fleetmanager REST API](src/main/resources/fleetmanager-api-v1.yaml)

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

