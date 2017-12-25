package com.ayadykin.test.fuel.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.test.fuel.shop.dao.GasStationDao;
import com.ayadykin.test.fuel.shop.dto.GasStationDto;
import com.ayadykin.test.fuel.shop.service.CatalogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private GasStationDao gasStationDao;

	@Override
	@Transactional
	public List<GasStationDto> getGasStationsList() {
		log.debug("->getGasStationsList");
		return gasStationDao.getAllGasStations();
	}

	@Override
	public GasStationDto getGasStationById(int gasStationId) {
		log.debug("->getGasStationById, gasStationId: {}", gasStationId);
		return gasStationDao.getGasStationById(gasStationId);
	}

	@Override
	public void saveGasStation(String gasStationName) {
		log.debug("->saveGasStation, gasStationName: {}", gasStationName);
		gasStationDao.saveGasStation(gasStationName);;
		
	}

	@Override
	public void addFuel(String fuelType, float price, int gasStationId) {
		log.debug("->saveGasStation, fuelType: {}, price: {}, gasStationId: {}", fuelType, price, gasStationId);
		gasStationDao.addFuel(fuelType, price, gasStationId);
		
	}

	@Override
	public GasStationDto getGasStationByName(String gasStationName) {
		log.debug("->saveGasStation, gasStationName: {}", gasStationName);
		return gasStationDao.getGasStationByName(gasStationName);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		log.debug("->getAllGasStations");
		return gasStationDao.getAllGasStations();
	}

	@Override
	public void updateGasStation(GasStationDto gasStationDto) {
		log.debug("->updateGasStation, gasStationDto: {}", gasStationDto);
		gasStationDao.updateGasStation(gasStationDto);
	}

	@Override
	public void deleteGasStation(int gasStationId) {
		log.debug("->deleteGasStation, gasStationId: {}", gasStationId);
		gasStationDao.deleteGasStation(gasStationId);
	}
}
