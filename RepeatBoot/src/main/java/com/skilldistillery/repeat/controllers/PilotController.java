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
import com.skilldistillery.repeat.entities.AircraftType;
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

		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
		// HH:mm");
		// LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
		// LocalDateTime stopTime = LocalDateTime.parse(stopTimeStr, formatter);

		if (startTimeStr == null || startTimeStr.equals("")) {
			//
		} else {
			startTimeStr = startTimeStr.replace("T", " ");
		}

		if (stopTimeStr == null || stopTimeStr.equals("")) {
			//
		} else {
			stopTimeStr = stopTimeStr.replace("T", " ");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime startTime = null; // allow null, verify DB constraints

		try {
			startTime = LocalDateTime.parse(startTimeStr, formatter);
		} catch (Exception e) {
			startTime = null;
		}

		LocalDateTime stopTime = null;// allow null, verify DB constraints

		try {
			stopTime = LocalDateTime.parse(stopTimeStr, formatter);
		} catch (Exception e) {
			stopTime = null;
		}

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
	public String editExperience(HttpSession session, @RequestParam("pilogLogEntryId") int pilogLogEntryId,
			@RequestParam("startTime") String startTimeStr, @RequestParam("stopTime") String stopTimeStr,
			@RequestParam("experienceType") int experienceTypeId, RedirectAttributes redir) {

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

		ExperienceType experienceType = pilotDAO.findById(experienceTypeId);

		if (startTimeStr == null || startTimeStr.equals("")) {
			//
		} else {
			startTimeStr = startTimeStr.replace("T", " ");
		}

		if (stopTimeStr == null || stopTimeStr.equals("")) {
			//
		} else {
			stopTimeStr = stopTimeStr.replace("T", " ");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime startTime = null; // allow null, verify DB constraints

		try {
			startTime = LocalDateTime.parse(startTimeStr, formatter);
		} catch (Exception e) {
			startTime = null;
		}

		LocalDateTime stopTime = null;// allow null, verify DB constraints

		try {
			stopTime = LocalDateTime.parse(stopTimeStr, formatter);
		} catch (Exception e) {
			stopTime = null;
		}

		PilotLogEntry pilotLogEntryDetails = new PilotLogEntry();

		pilotLogEntryDetails.setStartTime(startTime);
		pilotLogEntryDetails.setStopTime(stopTime);
		pilotLogEntryDetails.setUser(user);
		pilotLogEntryDetails.setExperienceType(experienceType);

		// NOTE: this was mistakenly using the pilot user id and not the actual pilot
		// log entry id!
		pilotDAO.updatePilotLog(pilogLogEntryId, pilotLogEntryDetails);

		redir.addFlashAttribute("message", "Flight has been updated. " + experienceType.getId() + ", "
				+ experienceType.getDescription() + ", " + startTime + ", " + stopTime);

		return "redirect:success.do";

	}

	@GetMapping({ "choose_aircraft_type.do" })
	public String chooseAircraftType(HttpSession session, RedirectAttributes redir, Model model) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 1) {
			redir.addFlashAttribute("message", "You must be logged in as an pilot to choose aircraft type.");
			return "redirect:login.do";
		}

		List<AircraftType> aircraftTypes = new ArrayList<>();

		try {
			aircraftTypes = pilotDAO.findAllAircraftType();
		} catch (Exception e) {
			System.out.print("Error in findAllAircraftType");
			e.printStackTrace();
		}

		model.addAttribute("aircraftTypes", aircraftTypes);

		return "pilot/choose_aircraft_type";
	}

	@GetMapping({ "evaluate_experience.do" })
	public String evaluateExperience(@RequestParam("aircraftTypeId") int aircraftTypeId, HttpSession session,
			RedirectAttributes redir, Model model) {

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
			experienceTypes = pilotDAO.findAllExperienceTypesByAircraftTypeId(aircraftTypeId);
		} catch (Exception e) {
			System.out.print("Error in listUsersGet");
			e.printStackTrace();
		}

		List<PilotLogEntry> pilotLogEntries = new ArrayList<>();

		try {

//			pilotLogEntries = pilotDAO.findAllPilotLogEntries(loggedInUser.getId());

			LocalDateTime now = LocalDateTime.now();

			LocalDateTime aYearAgo = now.minusDays(365); // 365 days ago, TESTING // TODO // NOTE

			pilotLogEntries = pilotDAO.findAllPilotLogEntries(loggedInUser.getId(), aYearAgo);

		} catch (Exception e) {
			System.out.print("Error in listUsersGet");
			e.printStackTrace();
		}

		// NOTE: This is a temporary solution to the problem of summing up the minutes

		int[] sum = new int[999];

		for (int i = 0; i < 999; i++) {
			sum[i] = 0;
		}

		for (PilotLogEntry pilotLogEntry : pilotLogEntries) {

			// TODO: EXPIRED LOGS any thing that is older than ??? should not be included...

			long minutes = 0;

			LocalDateTime startDateTime = pilotLogEntry.getStartTime();
			LocalDateTime stopDateTime = pilotLogEntry.getStopTime();

			// get the difference in minutes
			if (startDateTime != null && stopDateTime != null) {
				minutes = java.time.Duration.between(startDateTime, stopDateTime).toMinutes();
				int key = pilotLogEntry.getExperienceType().getId();
				sum[key] += minutes;
			}

			System.out.println(pilotLogEntry.getExperienceType().getDescription() + " " + minutes);
		}

		for (int j = 0; j < 999; j++) {
			if (sum[j] > 0) {
				System.out.println("Experience Type ID: " + j + " Minutes: " + sum[j]);
			}
		}

		model.addAttribute("sum", sum);
		model.addAttribute("experienceTypes", experienceTypes);
		model.addAttribute("pilotLogEntries", pilotLogEntries);

		return "pilot/evaluate_experience";

	}

	@PostMapping({ "delete_experience.do" })
	public String deleteExperience(@RequestParam("id") int id, HttpSession session, RedirectAttributes redir,
			Model model) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 1) {
			redir.addFlashAttribute("message", "You must be logged in as an pilot to delete pilot log entries.");
			return "redirect:login.do";
		}

		Boolean wasDeleted = false;

		try {
			wasDeleted = pilotDAO.deletePilotLogEntryById(id);
		} catch (Exception e) {
			System.out.print("Error in PilotDAO deletePilotLogEntryById");
			e.printStackTrace();
		}

		if (wasDeleted) {
			redir.addFlashAttribute("message", "Flight log has been deleted.");
		} else {
			redir.addFlashAttribute("message", "Flight log could not be deleted.");
		}

		return "redirect:success.do";

	}

}
