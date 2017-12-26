package com.ayadykin.test.fuel.shop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Role implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String role;

    @OneToOne(mappedBy = "role")
    private User user;

	@Override
	public String getAuthority() {
		return role;
	}


}
