package com.skilldistillery.repeat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.repeat.data.UserDAO;
import com.skilldistillery.repeat.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	private UserDAO userDAO;
	
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@GetMapping({"login.do"})
	public String loginGet() {
        return "login";
    }
	
	
	@GetMapping({"register.do"})
	public String registerGet() {
        return "public/register";
    }
	
	@PostMapping({"login.do"})
	public ModelAndView loginPost(User user, HttpSession session) {
        
		ModelAndView mv = new ModelAndView();
		
		User authenticatedUser = userDAO.authenticateUser(user.getUsername(), user.getPassword());
		
		if (authenticatedUser != null) {
			session.setAttribute("loggedInUser", authenticatedUser);
		    mv.setViewName("private/profile");
		} else {
			mv.setViewName("login");
			//mv.setViewName("error");
			//mv.setViewName("redirect:login.do");
		}
		
		return mv;
    }
	

}
