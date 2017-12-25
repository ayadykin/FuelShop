package com.ayadykin.test.fuel.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayadykin.test.fuel.shop.model.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer>{

}
