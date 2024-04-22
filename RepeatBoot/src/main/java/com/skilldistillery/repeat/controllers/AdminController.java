package com.skilldistillery.repeat.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String listUsersGet(HttpSession session, Model model, RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 4) {
			redir.addFlashAttribute("message", "You must be logged in as an Admin to edit users.");
			return "redirect:login.do";
		}

		List<User> users = new ArrayList<>();

		try {
			users = userDAO.findAllUser();
		} catch (Exception e) {
			System.out.print("Error in listUsersGet");
			e.printStackTrace();
		}

		System.out.println(users);

		System.out.println("**************************************");

		model.addAttribute("users", users);

		return "admin/list_user";

	}

	@PostMapping({ "enable_user.do" })
	public String enableUser(@RequestParam("id") int id, HttpSession session, Model model, RedirectAttributes redir) {
		User user = null;
		user = userDAO.enableUser(id);
		if (user == null) {
			model.addAttribute("error", "Could not enable user.");	
			return "error";
		}
		else { 
			model.addAttribute("message", "User has been enabled.");
		}
		return "success";

	}
	
	@PostMapping({ "disable_user.do" })
	public String disableUser(@RequestParam("id") int id, HttpSession session, Model model, RedirectAttributes redir) {
		User user = null;
		user = userDAO.disableUser(id);
		if (user == null) {
			model.addAttribute("error", "Could not disable user.");		 
			return "error";
		}
		else { 
			model.addAttribute("message", "User has been disabled.");
		}
		return "success";
		
	}
}
