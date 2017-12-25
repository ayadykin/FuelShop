package com.ayadykin.test.fuel.shop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gas_station")
public class GasStation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "gasStation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Fuel> catalog = new ArrayList<>();

    public GasStation(String name) {
        this.name = name;
    }

    public boolean addFuel(Fuel fuel) {
        return catalog.add(fuel);
    }
}
