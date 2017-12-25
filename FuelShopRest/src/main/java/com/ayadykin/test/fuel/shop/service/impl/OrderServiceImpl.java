package com.ayadykin.test.fuel.shop.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayadykin.test.fuel.shop.dao.GasStationDao;
import com.ayadykin.test.fuel.shop.dao.OrderDao;
import com.ayadykin.test.fuel.shop.dto.CreateOrderDto;
import com.ayadykin.test.fuel.shop.dto.FuelDto;
import com.ayadykin.test.fuel.shop.dto.GasStationDto;
import com.ayadykin.test.fuel.shop.dto.OrderDto;
import com.ayadykin.test.fuel.shop.dto.OrderStatusDto;
import com.ayadykin.test.fuel.shop.dto.UpdateOrderDto;
import com.ayadykin.test.fuel.shop.model.Order;
import com.ayadykin.test.fuel.shop.model.OrderStatus;
import com.ayadykin.test.fuel.shop.service.OrderService;
import com.ayadykin.test.fuel.shop.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private GasStationDao gasStationDao;
	@Autowired
	private UserService userService;
	
	@Override
	public OrderDto saveOrder(CreateOrderDto createOrderDto) {
		log.debug("->saveOrder, createOrderDto: {}", createOrderDto);

		GasStationDto gasStationDto = gasStationDao.getGasStationById(createOrderDto.getGasStationId());
		FuelDto fueldto = gasStationDto.getCatalog().stream().filter(fuel -> fuel.getId().equals(createOrderDto.getFuelTypeId()))
				.findFirst().get();

		Order order = new Order();
		order.setGasStation(gasStationDto.getName());
		order.setOrderDate(LocalDateTime.now());
		order.setFuelType(fueldto.getFuelType());
		order.setPrice(fueldto.getPrice());
		order.setLiters(createOrderDto.getLiters());
		order.setOrderStatus(OrderStatus.NEW);
		order.setUserId(userService.getUserId());
		orderDao.createOrder(order);

		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		log.debug("->saveOrder, orderDto: {}", orderDto);
		return orderDto;
	}

	@Override
	public OrderDto getOrder(long orderId) {
		log.debug("->getOrder, orderId: {}", orderId);
		Order order = orderDao.getOrder(orderId);
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		log.debug("->getOrder, orderDto: {}", orderDto);
		return orderDto;
	}

	@Override
	public List<OrderDto> getAllOrders() {
		log.debug("getAllOrders");
		List<Order> orders = orderDao.getAllOrders();
		return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> getOrdersForUser() {
		log.debug("getOrdersForUser");
		long userId = userService.getUserId();
		List<Order> orders = orderDao.getOrdersForUser(userId);
		return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public boolean changeOrderStatus(OrderStatusDto orderStatusDto) {
		log.debug("changeOrderStatus, orderStatusDto: {}", orderStatusDto);
		return orderDao.changeOrderStatus(orderStatusDto.getOrderStatus(), orderStatusDto.getOrderId());
	}

	@Override
	public boolean updateOrder(UpdateOrderDto updateOrderDto) {
		log.debug("updateOrder, updateOrderDto: {}");
		return orderDao.updateOrder(updateOrderDto);
	}

	@Override
	public boolean deleteOrder(long orderId) {
		log.debug("deleteOrder, orderId: {}", orderId);
		return orderDao.deleteOrder(orderId);
	}

}
