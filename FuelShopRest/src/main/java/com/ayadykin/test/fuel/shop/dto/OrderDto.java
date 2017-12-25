package com.ayadykin.test.fuel.shop.dto;

import java.time.LocalDateTime;

import com.ayadykin.test.fuel.shop.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDto {
	
	private Integer id;
	private LocalDateTime orderDate;
	private String gasStation;
	private String fuelType;
	private Float price;
	private Integer liters;
	private OrderStatus orderStatus;
	private Long userId;
}
