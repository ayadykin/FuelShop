package com.ayadykin.test.fuel.shop.dao;

import java.util.List;

import com.ayadykin.test.fuel.shop.dto.GasStationDto;

public interface GasStationDao {

	GasStationDto getGasStationById(int gasStationId);

	void saveGasStation(String gasStationName);

	void addFuel(String fuelType, float price, int gasStationId);

	GasStationDto getGasStationByName(String gasStationName);

	List<GasStationDto> getAllGasStations();

	void updateGasStation(GasStationDto gasStationDto);

	void deleteGasStation(int gasStationId);

}
