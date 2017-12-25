package com.ayadykin.test.fuel.shop.dao;

import java.util.List;

import com.ayadykin.test.fuel.shop.dto.UpdateOrderDto;
import com.ayadykin.test.fuel.shop.model.Order;
import com.ayadykin.test.fuel.shop.model.OrderStatus;

public interface OrderDao {

	List<Order> getAllOrders();

	List<Order> getOrdersForUser(long userId);

	Order getOrder(long id);

	boolean createOrder(Order order);

	boolean updateOrder(UpdateOrderDto updateOrderDto);

	boolean deleteOrder(long id);

	boolean changeOrderStatus(OrderStatus orderStatus, long orderId);

}
