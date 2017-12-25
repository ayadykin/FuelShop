package com.ayadykin.test.fuel.shop.dto;

import com.ayadykin.test.fuel.shop.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStatusDto {
	private OrderStatus orderStatus;
	private long orderId;
}
