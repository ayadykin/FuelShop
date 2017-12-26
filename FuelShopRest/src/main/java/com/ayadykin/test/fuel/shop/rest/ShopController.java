package com.ayadykin.test.fuel.shop.rest;

import static com.ayadykin.test.fuel.shop.rest.Urls.SHOP_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayadykin.test.fuel.shop.dto.CreateOrderDto;
import com.ayadykin.test.fuel.shop.dto.GasStationDto;
import com.ayadykin.test.fuel.shop.dto.OrderDto;
import com.ayadykin.test.fuel.shop.dto.UpdateOrderDto;
import com.ayadykin.test.fuel.shop.service.CatalogService;
import com.ayadykin.test.fuel.shop.service.OrderService;
import com.ayadykin.test.fuel.shop.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = SHOP_URL)
public class ShopController {

	@Autowired
	private CatalogService catalogService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@GetMapping(path = "/catalog", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<GasStationDto> getGasStationsCatalog() {
		log.info("->getGasStationsList");
		List<GasStationDto> gasStations = catalogService.getGasStationsList();
		log.info("<-getGasStationsList, gasStations: {} ", gasStations);
		return gasStations;
	}

	@GetMapping(path = "/order", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<OrderDto> getUserOrders() {
		log.info("->getUserOrders");
		return orderService.getOrdersForUser();
	}

	@PostMapping(path = "/order", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OrderDto createOrder(@RequestBody CreateOrderDto createOrderDto) {
		log.info("->createOrder, createOrderDto: {}", createOrderDto);
		return orderService.saveOrder(createOrderDto);
	}

	@PutMapping(path = "/order", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateOrder(@RequestBody UpdateOrderDto updateOrderDto) {
		log.info("->updateOrder, updateOrderDto: {}", updateOrderDto);

		OrderDto orderDto = orderService.getOrder(updateOrderDto.getId());
		if (!orderDto.getUserId().equals(userService.getUserId())) {
			// TODO
		}

		if (orderService.updateOrder(updateOrderDto)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(path = "/order/{orderId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteOrder(@PathVariable("orderId") String order) {
		log.info("->createOrder, order: {}", order);
		long orderId = Long.parseLong(order);
		OrderDto orderDto = orderService.getOrder(orderId);
		if (!orderDto.getUserId().equals(userService.getUserId())) {
			// TODO
		}

		if (orderService.deleteOrder(orderId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
