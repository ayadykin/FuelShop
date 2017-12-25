package com.ayadykin.test.fuel.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayadykin.test.fuel.shop.dao.OrderDao;
import com.ayadykin.test.fuel.shop.dto.UpdateOrderDto;
import com.ayadykin.test.fuel.shop.model.Order;
import com.ayadykin.test.fuel.shop.model.OrderStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Order> getAllOrders() {
		log.debug("->getAllOrders");
		final String sql = "select o.id, o.order_date, o.gas_station, o.fuel_type, o.price, o.liters, o.order_status, o.user_id from order o";
		List<Order> orders = Collections.emptyList();
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getLong("id"));
				order.setOrderDate(rs.getObject("order_date", LocalDateTime.class));
				order.setGasStation(rs.getString("gas_station"));
				order.setFuelType(rs.getString("fuel_type"));
				order.setPrice(rs.getFloat("price"));
				order.setLiters(rs.getInt("liters"));
				order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
				order.setUserId(rs.getLong("user_id"));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersForUser(long userId) {
		log.debug("->getOrdersForUser, userId: {} ", userId);
		final String sql = "select o.id, o.order_date, o.gas_station, o.fuel_type, o.price, o.liters, o.order_status, o.user_id from order o where o.user_id = ?";
		List<Order> orders = Collections.emptyList();
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getLong("id"));
				order.setOrderDate(rs.getObject("order_date", LocalDateTime.class));
				order.setGasStation(rs.getString("gas_station"));
				order.setFuelType(rs.getString("fuel_type"));
				order.setPrice(rs.getFloat("price"));
				order.setLiters(rs.getInt("liters"));
				order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
				order.setUserId(rs.getLong("user_id"));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return orders;
	}

	@Override
	public Order getOrder(long id) {
		log.debug("->getOrder, id: {}", id);
		final String sql = "select o.id, o.order_date, o.gas_station, o.fuel_type, o.price, o.liters, o.order_status from order o where o.id = ?";
		Order order = new Order();
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(0, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			order.setId(rs.getLong("id"));
			order.setOrderDate(rs.getObject("order_date", LocalDateTime.class));
			order.setGasStation(rs.getString("gas_station"));
			order.setFuelType(rs.getString("fuel_type"));
			order.setPrice(rs.getFloat("price"));
			order.setLiters(rs.getInt("liters"));
			order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
			order.setUserId(rs.getLong("user_id"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block;
		}
		return order;
	}

	@Override
	public boolean createOrder(Order order) {
		log.debug("->createOrder, order: {}");
		final String sql = "insert into order(order_date, gas_station, fuel_type, price, liters, order_status, user_id) values(?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setObject(0, order.getOrderDate());
			statement.setString(1, order.getGasStation());
			statement.setString(3, order.getFuelType());
			statement.setFloat(4, order.getPrice());
			statement.setInt(5, order.getLiters());
			statement.setString(6, order.getOrderStatus().name());
			statement.setLong(7, order.getUserId());

			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return false;
	} 
	
	@Override
	public boolean changeOrderStatus(OrderStatus orderStatus, long orderId){
		log.debug("->changeOrderStatus, orderStatus: {}, orderId: {}", orderStatus, orderId);
		final String sql = "update order set order_status = ?  where id = ?";
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(0, orderStatus.name());
			statement.setLong(1, orderId);
			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return false;
	}

	@Override
	public boolean updateOrder(UpdateOrderDto updateOrderDto) {
		log.debug("->updateOrder, updateOrderDto: {}", updateOrderDto);
		final String sql = "update order set gas_station = ?, fuel_type = ?, price = ?, liters = ?  where id = ?";
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(0, updateOrderDto.getGasStation());
			statement.setString(1, updateOrderDto.getFuelType());
			statement.setFloat(3, updateOrderDto.getPrice());
			statement.setInt(4, updateOrderDto.getLiters());
			statement.setLong(5, updateOrderDto.getId());
			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return false;
	}

	@Override
	public boolean deleteOrder(long id) {
		log.debug("->createOrder, order: {}");
		final String sql = "delete from order where id = ?";
		try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(0, id);
			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return false;
	}
}
