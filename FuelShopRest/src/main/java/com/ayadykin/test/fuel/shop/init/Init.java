package com.ayadykin.test.fuel.shop.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.test.fuel.shop.dao.GasStationDao;
import com.ayadykin.test.fuel.shop.model.Fuel;
import com.ayadykin.test.fuel.shop.model.GasStation;
import com.ayadykin.test.fuel.shop.repository.GasStationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Init {

    private final static String A92 ="A-92";
    private final static String A95 ="A-95";
    private final static String A98 ="A-98";
    @Autowired
    private GasStationRepository gasStationRepository; 
    @Autowired
    private GasStationDao jdbcTemplateGasStationDao;
    
    //@PostConstruct
    @Transactional
    public void init(){
        log.debug("Init start");
        creteGasStation("Okko");
        creteGasStation("NPM");
        creteGasStation("Wok");
        creteJdbcTemplateGasStation("Ais");
        creteJdbcTemplateGasStation("Na");
        log.debug("Init end");   
    }
    
    private void creteGasStation(String name){
        Fuel fuel95 = new Fuel(A95, 25.5f);
        Fuel fuel92 = new Fuel(A92, 24.5f);
        
        GasStation gasStation = new GasStation(name);
        gasStation.addFuel(fuel95);
        gasStation.addFuel(fuel92);
        
        fuel95.setGasStation(gasStation);
        fuel92.setGasStation(gasStation);
        gasStationRepository.save(gasStation);
    }
    
    private void creteJdbcTemplateGasStation(String name){
        jdbcTemplateGasStationDao.saveGasStation(name);
        jdbcTemplateGasStationDao.addFuel(A92, 25.5f, 1);
        jdbcTemplateGasStationDao.addFuel(A98, 30, 1);
    }
}
