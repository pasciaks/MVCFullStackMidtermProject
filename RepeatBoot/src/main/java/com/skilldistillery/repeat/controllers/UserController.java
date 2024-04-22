package com.skilldistillery.repeat.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
public class UserController {

	private UserDAO userDAO;

	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping({ "login.do" })
	public String loginGet() {
		return "public/login";
	}

	@GetMapping({ "register.do" })
	public String registerGet() {
		return "public/register";
	}

	@PostMapping({ "login.do" })
	public ModelAndView loginPost(User user, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		User authenticatedUser = userDAO.authenticateUser(user.getUsername(), user.getPassword());

		if (authenticatedUser != null) {
			session.setAttribute("loggedInUser", authenticatedUser);
			mv.setViewName("redirect:profile.do");
		} else {
			mv.setViewName("public/login");
		}
		System.out.println(authenticatedUser);
		if (authenticatedUser == null) {
			mv.addObject("error", "Could not login. Verify credentials or user activation with admin.");
		}
		return mv;
	}

	@PostMapping({ "register.do" })
	public ModelAndView registerPost(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("imageUrl") String imageUrl,
			@RequestParam("roleId") String roleId, @RequestParam("organizationId") String organizationId,
			@RequestParam("dateOfBirth") String dateOfBirth, HttpSession session) {

		ModelAndView mv = new ModelAndView();

		System.out.println(username);
		System.out.println(password);
		System.out.println(imageUrl);
		System.out.println(roleId);
		System.out.println(organizationId);
		System.out.println(dateOfBirth);

		LocalDate dateOfBirthLocalDateObject = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_DATE);

		// use DAO to attempt to create new registration

		User registeredUser = null;
		
		try {
			registeredUser = userDAO.registerUser(username, password, imageUrl, roleId, organizationId,
					dateOfBirthLocalDateObject);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", e.getMessage());
			mv.setViewName("error");
		}

		if (registeredUser == null) {
			System.out.println("Failed to register user.");
			mv.addObject("error", "Failed to register user.");
			mv.setViewName("redirect:error.do");
		} else {
			System.out.println("Successfully registered user.");
			mv.addObject("message", "Successfully registered user, please login.");
			mv.setViewName("redirect:login.do");
		}

		return mv;
	}

	@GetMapping({ "logout.do" })
	public String logOutGet(HttpSession session, RedirectAttributes redir) {
		session.removeAttribute("loggedInUser");
		redir.addFlashAttribute("message", "You have been logged out.");
		return "redirect:about.do";
	}
	
}
