package com.ayadykin.test.fuel.shop.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ayadykin.test.fuel.shop.model.User;
import com.ayadykin.test.fuel.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Long getUserId() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getId();
	}

}
