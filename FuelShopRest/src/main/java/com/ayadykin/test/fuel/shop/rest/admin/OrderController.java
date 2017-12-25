package com.ayadykin.test.fuel.shop.rest.admin;

import static com.ayadykin.test.fuel.shop.rest.Urls.ADMIN_URL;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayadykin.test.fuel.shop.dto.OrderDto;
import com.ayadykin.test.fuel.shop.dto.OrderStatusDto;
import com.ayadykin.test.fuel.shop.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = ADMIN_URL)
public class OrderController {

	private OrderService orderService;

	@GetMapping(path = "/orders", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<OrderDto> getAllOrders() {
		log.info("->getUserOrders");
		return orderService.getAllOrders();
	}

	@GetMapping(path = "/order/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<OrderDto> getOrder() {
		log.info("->getUserOrders");
		return orderService.getOrdersForUser();
	}

	@PutMapping(path = "/order/status", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> changeOrderStatus(@RequestBody OrderStatusDto orderStatusDto) {
		log.info("->getUserOrders", orderStatusDto);
		if (orderService.changeOrderStatus(orderStatusDto)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/order/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) {
		log.info("->getUserOrders");
		if (orderService.deleteOrder(orderId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
