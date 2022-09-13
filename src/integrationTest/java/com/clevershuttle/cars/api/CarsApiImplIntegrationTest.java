package com.clevershuttle.cars.api;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.clevershuttle.cars.apimodel.Car;
import com.clevershuttle.cars.apimodel.Car.StatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarsApiImplIntegrationTest {

  public static final int EXPECTED_STATUS_CODE_NOT_FOUND = 404;
  public static final String CARS_ENDPOINT = "cars/";
  public static final String NON_EXISTING_CAR_ID = "9999999";
  public static final int EXPECTED_STATUS_CODE_CREATED = 204;
  public static final String APPLICATION_JSON = "application/json";
  public static final int EXPECTED_STATUS_CODE_OK = 200;
  public static final String HTTP_METHOD_POST = "POST";
  public static final int POSITION_OF_ID = 4;
  public static final String LOCATION_HEADER = "Location";
  public static final String HTTP_LOCALHOST_AND_PORT = "http://localhost:8080/";
  public static final int FIRST_ELEMENT_OF_RESPONSE = 0;

  private Car testCar;

  @BeforeAll
  public void prepareTestData(){
    testCar = new Car()
        .brand("Volkswagen")
        .licensePlate("B-12345")
        .manufacturer("Volkswagen")
        .operationsCity("Wolfsburg")
        .status(StatusEnum.IN_MAINTENANCE)
        .createdAt("2017-09-01T10:23:47.000Z")
        .lastUpdatedAt("2022-04-15T13:23:11.000Z");
  }

  @Test
  void givenNonExistingCar_whenGetrequest_thenResponseStatuscode404() {
    get(CARS_ENDPOINT + NON_EXISTING_CAR_ID).then().statusCode(EXPECTED_STATUS_CODE_NOT_FOUND);
  }

  @Test
  void givenExistingCar_whenGetrequest_thenResponseContainsCar() {
    Response responseForCarCreation = postResponse();

    String idForNewCar = responseForCarCreation.header(LOCATION_HEADER).split("/")[POSITION_OF_ID];
    get(CARS_ENDPOINT + idForNewCar).then().statusCode(EXPECTED_STATUS_CODE_OK);
  }

  @Test
  void givenNonExistingCar_whenCreateCar_thenResponseStatuscode204() {
    with().body(testCar)
        .contentType(APPLICATION_JSON)
        .when()
        .request(HTTP_METHOD_POST, CARS_ENDPOINT)
        .then()
        .statusCode(EXPECTED_STATUS_CODE_CREATED);
  }

  @Test
  void givenNonExistingCar_whenCreateCar_thenLocationContainsLocalhostPortAndEndpoint() {
    with().body(testCar)
        .contentType(APPLICATION_JSON)
        .when()
        .request(HTTP_METHOD_POST, CARS_ENDPOINT)
        .then().assertThat().headers(LOCATION_HEADER, containsString(
            HTTP_LOCALHOST_AND_PORT + CARS_ENDPOINT));
  }

  @Test
  void givenNonExistingCar_whenCreateCar_thenJsonInResponseCorrectlyPopulated()
      throws JsonProcessingException {

    //Create new car and then get it.
    String idForNewCar = postResponse().getHeader(LOCATION_HEADER).split("/")[POSITION_OF_ID];
    String createdCarJson = getResponseForCarId(idForNewCar).asPrettyString();
    ObjectMapper objectMapper = new ObjectMapper();
    Car createdCar = objectMapper.readValue(createdCarJson, Car[].class)[FIRST_ELEMENT_OF_RESPONSE];
    createdCar.id(0);
    testCar.id(0);
    assertEquals(testCar, createdCar);
  }


  private Response postResponse() {
    return with().body(testCar)
        .contentType(APPLICATION_JSON)
        .when()
        .request(HTTP_METHOD_POST, CARS_ENDPOINT);
  }

  private Response getResponseForCarId(String carId){
    return get(CARS_ENDPOINT + carId);
  }

}
