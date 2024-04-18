package com.skilldistillery.repeat.controllers;

import org.springframework.stereotype.Controller;

import com.skilldistillery.repeat.data.UserDAO;

@Controller
public class UserController {
	
	private UserDAO userDAO;
	
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
