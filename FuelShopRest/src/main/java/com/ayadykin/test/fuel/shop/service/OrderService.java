package com.ayadykin.test.fuel.shop.service;

import java.util.List;

import com.ayadykin.test.fuel.shop.dto.CreateOrderDto;
import com.ayadykin.test.fuel.shop.dto.OrderDto;
import com.ayadykin.test.fuel.shop.dto.OrderStatusDto;
import com.ayadykin.test.fuel.shop.dto.UpdateOrderDto;

public interface OrderService {

	OrderDto saveOrder(CreateOrderDto createOrderDto);

	OrderDto getOrder(long orderId);

	List<OrderDto> getAllOrders();

	List<OrderDto> getOrdersForUser();

	boolean updateOrder(UpdateOrderDto updateOrderDto);

	boolean deleteOrder(long orderId);

	boolean changeOrderStatus(OrderStatusDto orderStatusDto);

}
