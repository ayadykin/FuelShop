package com.ayadykin.test.fuel.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FuelDto {

    private Integer id;
	private String fuelType;
	private Float price;
}
