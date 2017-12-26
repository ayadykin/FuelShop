package com.ayadykin.test.fuel.shop.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.test.fuel.shop.App;
import com.ayadykin.test.fuel.shop.config.HibernateConfig;
import com.ayadykin.test.fuel.shop.config.AppConfiguration;
import com.ayadykin.test.fuel.shop.dto.GasStationDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppConfiguration.class, App.class, HibernateConfig.class })
public class GasStationDaoTest {

    @Autowired
    private GasStationDao jdbcTemplateGasStationDao;

    @Test
    public void getAllGasStationsTest() {

        List<GasStationDto> gasStations = jdbcTemplateGasStationDao.getAllGasStations();
        for (GasStationDto gasStation : gasStations) {
            log.debug("getAllGasStationsTest: {}", gasStation);
        }
    }
}
