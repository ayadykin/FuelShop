package com.ayadykin.test.fuel.shop.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GasStationDto {

    private Integer id;
	private String name;
	private List<FuelDto> catalog;
}
