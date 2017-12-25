package com.ayadykin.test.fuel.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateOrderDto {
	
	private Long id;
	private String gasStation;
	private String fuelType;
	private Float price;
	private Integer liters;
}
