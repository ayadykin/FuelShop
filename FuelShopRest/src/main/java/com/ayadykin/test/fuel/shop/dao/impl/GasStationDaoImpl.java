package com.ayadykin.test.fuel.shop.dao.impl;

import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ayadykin.test.fuel.shop.dao.GasStationDao;
import com.ayadykin.test.fuel.shop.dto.GasStationDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class GasStationDaoImpl implements GasStationDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public GasStationDto getGasStationById(int gasStationId) {
		log.debug("->getGasStationByName, gasStationId: {}", gasStationId);
		final String sql = "select * from gas_station where id = ?";

		GasStationDto gasStationDto = jdbcTemplate
				.query(sql, new Object[] { gasStationId }, JdbcTemplateMapperFactory.newInstance().newRowMapper(GasStationDto.class))
				.get(0);
		log.debug("<-getGasStationByName, gasStationDto: {}", gasStationDto);
		return gasStationDto;
	}

	@Override
	public GasStationDto getGasStationByName(String gasStationName) {
		log.debug("->getGasStationByName, gasStationName: {}", gasStationName);
		final String sql = "select * from gas_station where name = ?";

		GasStationDto gasStationDto = jdbcTemplate
				.query(sql, new Object[] { gasStationName }, JdbcTemplateMapperFactory.newInstance().newRowMapper(GasStationDto.class))
				.get(0);
		log.debug("<-getGasStationByName, gasStationDto: {}", gasStationDto);
		return gasStationDto;
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		log.debug("->getAllGasStations");
		final String sql = "SELECT gs.id as id, gs.name as name, gs.id as catalog_id, f.grade_of_fuel as catalog_fuelType, "
				+ "f.price as catalog_price  FROM gas_station gs LEFT JOIN fuel f ON gs.id = f.gas_station_id";

		List<GasStationDto> gasStations = jdbcTemplate.query(sql,
				JdbcTemplateMapperFactory.newInstance().addKeys("id").newResultSetExtractor(GasStationDto.class));
		log.debug("<-getGasStationByName, gasStations: {}", gasStations);
		return gasStations;
	}

	@Override
	public void saveGasStation(String gasStationName) {
		log.debug("->saveGasStation, gasStationName: {}", gasStationName);
		final String sql = "insert into gas_station(name) values(?)";
		int res = jdbcTemplate.update(sql, new Object[] { gasStationName });
	}

	@Override
	public void addFuel(String fuelType, float price, int gasStationId) {
		log.debug(">addFuel, fuelType: {}, price: {} ", fuelType, price);
		final String sql = "insert into fuel(grade_of_fuel, price, gas_station_id) values(?, ?, ?)";
		GasStationDto gasStationDto = getGasStationById(gasStationId);
		if (Objects.isNull(gasStationDto)) {
			// TODO ADD Exception
		}
		int res = jdbcTemplate.update(sql, new Object[] { fuelType, price, gasStationId });
	}

	@Override
	public void updateGasStation(GasStationDto gasStationDto) {
		log.debug("->updateGasStation, gasStationDto: {}", gasStationDto);
		final String sql = "update gas_station set name = ? where id = ?";
		int res = jdbcTemplate.update(sql, new Object[] { gasStationDto.getName(), gasStationDto.getId() });
	}

	@Override
	public void deleteGasStation(int gasStationId) {
		log.debug("->deleteGasStation, gasStationId: {}", gasStationId);
		final String sql = "delete from gas_station where id = ?";
		int res = jdbcTemplate.update(sql, new Object[] { gasStationId });
	}
}
