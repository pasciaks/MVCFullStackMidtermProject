package com.skilldistillery.repeat.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.repeat.data.UserDAO;
import com.skilldistillery.repeat.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	private UserDAO userDAO;

	public AdminController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping({ "list_user.do" })
	public String listUsersGet(HttpSession session, RedirectAttributes redir) {
		List<User> users = userDAO.findAllUser();
		System.out.println(users);
		System.out.println("**************************************");
		redir.addAttribute("users", users);
		redir.addFlashAttribute("message", "Listed users");
		return "admin/list_user";
		
	}
	
}
