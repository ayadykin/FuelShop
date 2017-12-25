package com.ayadykin.test.fuel.shop.service;

import java.util.List;

import com.ayadykin.test.fuel.shop.dto.GasStationDto;

public interface CatalogService {

	List<GasStationDto> getGasStationsList();

	GasStationDto getGasStationById(int gasStationId);

	void saveGasStation(String gasStationName);

	void addFuel(String fuelType, float price, int gasStationId);

	GasStationDto getGasStationByName(String gasStationName);

	List<GasStationDto> getAllGasStations();

	void updateGasStation(GasStationDto gasStationDto);

	void deleteGasStation(int gasStationId);
}
