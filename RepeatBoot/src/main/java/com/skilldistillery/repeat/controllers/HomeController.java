package com.skilldistillery.repeat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.repeat.data.UserDAO;
import com.skilldistillery.repeat.entities.Organization;
import com.skilldistillery.repeat.entities.Role;
import com.skilldistillery.repeat.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	private UserDAO userDAO;

	public HomeController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping({ "/", "home.do" })
	public String home() {
		return "home";
	}

	@GetMapping({ "about.do" })
	public String about() {
		return "public/about";
	}

	@GetMapping({ "error.do" })
	public String error() {
		return "error";
	}

	@GetMapping({ "success.do" })
	public String success() {
		return "success";
	}

	@GetMapping({ "profile.do" })
	public String profile(Model model, HttpSession session, RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("error", "You must be logged in to view your profile.");
			return "redirect:error.do";
		}

		User freshUser = userDAO.findById(loggedInUser.getId());

		System.out.println(freshUser);

		session.setAttribute("loggedInUser", freshUser);

		List<Role> roles = userDAO.findAllRoles();
		List<Organization> organizations = userDAO.findAllOrganizations();
		model.addAttribute("roles", roles);
		model.addAttribute("organizations", organizations);

		return "private/profile";
	}

}
