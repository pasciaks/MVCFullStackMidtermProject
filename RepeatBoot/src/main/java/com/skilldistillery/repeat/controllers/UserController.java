package com.skilldistillery.repeat.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.repeat.data.PilotDAO;
import com.skilldistillery.repeat.data.UserDAO;
import com.skilldistillery.repeat.entities.Certification;
import com.skilldistillery.repeat.entities.Organization;
import com.skilldistillery.repeat.entities.PilotCertification;
import com.skilldistillery.repeat.entities.Role;
import com.skilldistillery.repeat.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private UserDAO userDAO;
	private PilotDAO pilotDAO;

	public UserController(UserDAO userDAO, PilotDAO pilotDAO) {
		this.userDAO = userDAO;
		this.pilotDAO = pilotDAO;
	}

	@GetMapping({ "login.do" })
	public String loginGet() {
		return "public/login";
	}

	@GetMapping({ "register.do" })
	public String registerGet(Model model, HttpSession session) {
		List<Role> roles = userDAO.findAllRoles();
		List<Organization> organizations = userDAO.findAllOrganizations();
		model.addAttribute("roles", roles);
		model.addAttribute("organizations", organizations);
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
			mv.addObject("error",
					"Could not login. Verify your credentials are correct - NOTE: Newly registered accounts must be activated by admin before use is allowed.");
		}
		return mv;
	}

	@PostMapping({ "register.do" })
	public ModelAndView registerPost(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("imageUrl") String imageUrl,
			@RequestParam("roleId") String roleId, @RequestParam("organizationId") String organizationId,
			@RequestParam("dateOfBirth") LocalDate dateOfBirth, HttpSession session, RedirectAttributes redir) {

		ModelAndView mv = new ModelAndView();

		User registeredUser = null;

		// see if the user already exists in the database and if so, return an error
		// stating that the user already exists

		List<User> users = userDAO.findAllUser();

		for (User user : users) {
			if (user.getUsername().equals(username)) {
				mv.addObject("error", "User already exists.");
				redir.addFlashAttribute("error", "User already exists.");
				mv.setViewName("redirect:error.do");
				return mv;
			}
		}

		// if the user does not exist, then create a new user and return a success
		// message

		try {
			registeredUser = userDAO.registerUser(username, password, imageUrl, roleId, organizationId, dateOfBirth);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", e.getMessage());
			redir.addFlashAttribute("error", " " + e.getMessage());
			mv.setViewName("redirect:error.do");
		}

		if (registeredUser == null) {
			System.out.println("Failed to register user.");
			mv.addObject("error", "Failed to register user.");
			redir.addFlashAttribute("error", "Failed to register user.");
			mv.setViewName("redirect:error.do");
		} else {
			System.out.println("Successfully registered user.");
			mv.addObject("message", "Successfully registered user, please login.");
			redir.addFlashAttribute("message", "Successfully registered user, please login.");
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

	@PostMapping({ "update_profile.do" })
	public String updateProfile(User user, HttpSession session, RedirectAttributes redir) {

		ModelAndView mv = new ModelAndView();

		User updatedUser = null;

		try {
			updatedUser = userDAO.updateUser(user.getId(), user);
		} catch (Exception e) {
			e.printStackTrace();
			redir.addFlashAttribute("error", " " + e.getMessage());
			return "redirect:error.do";
		}

		if (updatedUser == null) {
			session.setAttribute("loggedInUser", null);
			System.out.println("Failed to update user.");
			redir.addFlashAttribute("error", "Failed to update user.!!");
			redir.addFlashAttribute("message", null);
			return "redirect:error.do";
		} else {
			session.setAttribute("loggedInUser", updatedUser);
			System.out.println("Successfully updated user.");
			redir.addFlashAttribute("error", null);
			redir.addFlashAttribute("message", "Successfully updated user.!!");
			return "redirect:success.do";
		}

	}

	@GetMapping({ "list_users_for_certification.do" })
	public String listUsersForCertificationGet(HttpSession session, Model model, RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 2) {
			redir.addFlashAttribute("message", "You must be logged in as Clerk to manage certifications.");
			return "redirect:login.do";
		}

		List<User> users = new ArrayList<>();

		try {
			users = userDAO.findAllUsersByOrgId(loggedInUser.getOrganization().getId());
		} catch (Exception e) {
			System.out.print("Error in findAllUsersByOrgId");
			e.printStackTrace();
		}

		System.out.println(users);

		model.addAttribute("users", users);

		return "clerk/list_users_for_certification";

	}

	@GetMapping({ "show_user.do" })
	public String showUserGet(@RequestParam("id") int pilotId, HttpSession session, Model model,
			RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 2) {
			redir.addFlashAttribute("message", "You must be logged in as Clerk to manage certifications.");
			return "redirect:login.do";
		}

		List<PilotCertification> certifications = new ArrayList<>();

		try {
			certifications = pilotDAO.findAllPilotCertification(pilotId);
		} catch (Exception e) {
			System.out.print("Error in findAllPilotCertification");
			e.printStackTrace();
		}

		System.out.println(certifications);

		model.addAttribute("certifications", certifications);
		model.addAttribute("currentPilotId", pilotId);	
		model.addAttribute("pilotId", pilotId);
		
		System.out.println("currentPilotId: " + pilotId);
		System.out.println("pilotId: " + pilotId);

		return "clerk/show_user_certifications";

	}

	@GetMapping({ "add_certification.do" })
	public String addCertification(@RequestParam("pilotId") int pilotId, HttpSession session, Model model,
			RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 2) {
			redir.addFlashAttribute("message", "You must be logged in as Clerk to manage certifications.");
			return "redirect:login.do";
		}

		List<Certification> certifications = new ArrayList<>();

		try {
			certifications = pilotDAO.findAllCertification();
		} catch (Exception e) {
			System.out.print("Error in findAllCertification");
			e.printStackTrace();
		}

		System.out.println(certifications);

		model.addAttribute("certifications", certifications);
		model.addAttribute("currentPilotId", pilotId);

		return "clerk/add_certification";

	}

	@PostMapping({ "add_certification.do" })
	public String addCertification(@RequestParam("id") int pilotId, @RequestParam("details") String details,
			@RequestParam("passed") String passed, @RequestParam("expirationDate") LocalDate expirationDate,
			@RequestParam("effectiveDate") LocalDate effectiveDate, @RequestParam("certificationId") int certificationId, HttpSession session, Model model,
			RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 2) {
			redir.addFlashAttribute("message", "You must be logged in as Clerk to manage certifications.");
			return "redirect:login.do";
		}

		PilotCertification pilotCertification = new PilotCertification();
		
		pilotCertification.setDetails(details);
		
		pilotCertification.setEffectiveDate(effectiveDate);
		
		pilotCertification.setExpirationDate(expirationDate);
		
		pilotCertification.setPassed(passed.equals("1") ? true : false);
		
		Certification certification = pilotDAO.findCertificationById(certificationId);
		
		if (certification == null) {
			redir.addFlashAttribute("message", "Certification not found.");
			return "redirect:error.do";
		}
		
		pilotCertification.setCertification(certification);
		
		User pilotUser = userDAO.findById(pilotId);
		
		if (pilotUser == null) {
			redir.addFlashAttribute("message", "Pilot not found.");
			return "redirect:error.do";
		}
		
		pilotCertification.setUser(pilotUser);
		
		try {
			pilotCertification = pilotDAO.addPilotCertification(pilotCertification);
        } catch (Exception e) {
            System.out.print("Error in addPilotCertification");
            e.printStackTrace();
        }
		
		if (pilotCertification == null) {
			redir.addFlashAttribute("message", "Failed to add certification.");
			return "redirect:error.do";
		} else {
			redir.addFlashAttribute("message", "Successfully added certification.");
            return "redirect:success.do";
        }


	}

}
