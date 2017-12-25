package com.ayadykin.test.fuel.shop.rest.admin;

import static com.ayadykin.test.fuel.shop.rest.Urls.ADMIN_URL;

import java.util.List;

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

import com.ayadykin.test.fuel.shop.dto.GasStationDto;
import com.ayadykin.test.fuel.shop.dto.OrderDto;
import com.ayadykin.test.fuel.shop.service.CatalogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = ADMIN_URL)
public class CatalogController {

	private CatalogService catalogService;

	@GetMapping(path = "/gas-station", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<OrderDto> getUserOrders() {
		log.info("->getUserOrders");
		return null;
	}

	@PostMapping(path = "/gas-station/{gasStationName}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void createGasStation(@PathVariable("gasStationName") String gasStationName) {
		log.info("->createGasStation, gasStationName: {}", gasStationName);
		catalogService.saveGasStation(gasStationName);
	}

	@PutMapping(path = "/gas-station", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateGasStation(@RequestBody GasStationDto gasStationDto) {
		log.info("->updateGasStation, gasStationDto: {}", gasStationDto);
		catalogService.updateGasStation(gasStationDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/gas-station/{gasStation}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteGasStation(@PathVariable("gasStation") String gasStation) {
		log.info("->deleteGasStation, gasStation: {}", gasStation);
		int gasStationId = Integer.parseInt(gasStation);
		catalogService.deleteGasStation(gasStationId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
