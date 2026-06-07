package com.carscompany.cars.services;

import com.carscompany.cars.apimodel.Car;
import com.carscompany.cars.persistence.model.CarEntity;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

  int FRACTIONAL_DIGITS_MILLIS = 3;

  DateTimeFormatter INSTANT_FORMATTER =
      new DateTimeFormatterBuilder().appendInstant(FRACTIONAL_DIGITS_MILLIS).toFormatter();

  @Mapping(target = "id", ignore = true)
  CarEntity toEntity(Car car);

  Car toApi(CarEntity carEntity);

  default Instant stringToInstant(String value) {
    return Instant.parse(value);
  }

  default String instantToString(Instant value) {
    return INSTANT_FORMATTER.format(value);
  }
}