package com.ayadykin.test.fuel.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateOrderDto {
	
	@NonNull
	@JsonProperty(value = "gas_station_id")
	private Integer gasStationId;
	@NonNull
	@JsonProperty(value = "fuel_type_id")
	private Integer fuelTypeId;
	
	@NonNull
	@JsonProperty(value = "liters")
	private Integer liters;
}
