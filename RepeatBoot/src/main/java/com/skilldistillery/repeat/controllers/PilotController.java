package com.skilldistillery.repeat.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.skilldistillery.repeat.data.PilotDAO;
import com.skilldistillery.repeat.data.UserDAO;
import com.skilldistillery.repeat.entities.ExperienceType;
import com.skilldistillery.repeat.entities.PilotLogEntry;
import com.skilldistillery.repeat.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class PilotController {

	private PilotDAO pilotDAO;
	private UserDAO userDAO;
	public PilotController(PilotDAO pilotDAO, UserDAO userDAO) {
		this.pilotDAO = pilotDAO;
		this.userDAO = userDAO;
	}

	@GetMapping({ "add_log_entry.do" })
	public String addExperience(HttpSession session, RedirectAttributes redir, Model model) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 1) {
			redir.addFlashAttribute("message", "You must be logged in as an pilot to add pilot log entries.");
			return "redirect:login.do";
		}
		List<ExperienceType> experienceTypes = new ArrayList<>();

		try {
			experienceTypes = pilotDAO.findAllExperienceTypes();
		} catch (Exception e) {
			System.out.print("Error in listUsersGet");
			e.printStackTrace();
		}

		model.addAttribute("experienceTypes", experienceTypes);

		return "pilot/add_experience";

	}

	@PostMapping({ "add_log_entry.do" })
	public String addExperience(HttpSession session, @RequestParam("startTime") String startTimeStr,
			@RequestParam("stopTime") String stopTimeStr, @RequestParam("experienceType") int experienceTypeId,
			RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 1) {
			redir.addFlashAttribute("message", "You must be logged in as an pilot to add pilot log entries.");
			return "redirect:login.do";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
		LocalDateTime stopTime = LocalDateTime.parse(stopTimeStr, formatter);

		pilotDAO.addPilotLog(startTime, stopTime, experienceTypeId, loggedInUser.getId());
		redir.addFlashAttribute("message", "Flight has been logged.");
		return "redirect:success.do";

	}

	@GetMapping({ "list_experience.do" })
	public String listExperience(HttpSession session, RedirectAttributes redir, Model model) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 1) {
			redir.addFlashAttribute("message", "You must be logged in as an pilot to add pilot log entries.");
			return "redirect:login.do";
		}
		List<PilotLogEntry> pilotLogEntries = new ArrayList<>();

		try {
			pilotLogEntries = pilotDAO.findAllPilotLogEntries(loggedInUser.getId());
		} catch (Exception e) {
			System.out.print("Error in listUsersGet");
			e.printStackTrace();
		}

		model.addAttribute("pilotLogEntries", pilotLogEntries);

		return "pilot/list_experience";

	}

	@GetMapping({ "edit_experience.do" })
	public String editExperience(@RequestParam("id") int id, HttpSession session, RedirectAttributes redir,
			Model model) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 1) {
			redir.addFlashAttribute("message", "You must be logged in as an pilot to add pilot log entries.");
			return "redirect:login.do";
		}
		PilotLogEntry pilotLogEntry = null;

		try {
			pilotLogEntry = pilotDAO.findPilotLogEntryById(id);
		} catch (Exception e) {
			System.out.print("Error in PilotDAO findPilotLogEntryById");
			e.printStackTrace();
		}
		
		model.addAttribute("pilotLogEntry", pilotLogEntry);
		
		List<ExperienceType> experienceTypes = new ArrayList<>();

		try {
			experienceTypes = pilotDAO.findAllExperienceTypes();
		} catch (Exception e) {
			System.out.print("Error in listUsersGet");
			e.printStackTrace();
		}

		model.addAttribute("experienceTypes", experienceTypes);

		return "pilot/edit_experience";

	}

	@PostMapping({ "edit_experience.do" })
	public String editExperience(HttpSession session, @RequestParam("startTime") String startTimeStr,
			@RequestParam("stopTime") String stopTimeStr, @RequestParam("experienceType") int experienceTypeId,
			RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 1) {
			redir.addFlashAttribute("message", "You must be logged in as an pilot to add pilot log entries.");
			return "redirect:login.do";
		}
		User user = userDAO.findById(loggedInUser.getId());
		ExperienceType experienceType = pilotDAO.findById(experienceTypeId)		;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
		LocalDateTime stopTime = LocalDateTime.parse(stopTimeStr, formatter);
		PilotLogEntry pilotLogEntryDetails = new PilotLogEntry();
		pilotLogEntryDetails.setStartTime(startTime);
		pilotLogEntryDetails.setStopTime(stopTime);
		pilotLogEntryDetails.setUser(user);
		pilotLogEntryDetails.setExperienceType(experienceType);
		pilotDAO.updatePilotLog(loggedInUser.getId(), pilotLogEntryDetails);
		redir.addFlashAttribute("message", "Flight has been updated.");
		return "redirect:success.do";

	}

}
