package com.ayadykin.test.fuel.shop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fuel")
public class Fuel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fuel_type")
    private String fuelType;
    
    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "gas_station_id", nullable = false)
    private GasStation gasStation;

    public Fuel(String fuelType, Float price) {
        this.fuelType = fuelType;
        this.price = price;
    }

}
