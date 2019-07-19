package com.thbs.gb.service;

import com.thbs.gb.model.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);
}
