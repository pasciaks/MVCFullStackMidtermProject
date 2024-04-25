package com.skilldistillery.repeat.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.repeat.data.CommanderDAO;
import com.skilldistillery.repeat.entities.AircraftType;
import com.skilldistillery.repeat.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommanderController {

	private CommanderDAO commanderDAO;

	public CommanderController(CommanderDAO commanderDAO) {
		this.commanderDAO = commanderDAO;
	}

	@GetMapping({ "list_aircraft_types.do" })
	public String listAircraftTypes(Model model, HttpSession session, RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 3) {
			redir.addFlashAttribute("message",
					"You must be logged in a Commander to view or edit additional aircraft types.");
			return "redirect:login.do";
		}

		List<AircraftType> aircraftTypes = commanderDAO.findAllAircraftType();

		model.addAttribute("aircraftTypes", aircraftTypes);

		return "commander/list_aircraft_types";
	}

	@GetMapping({ "experience_type_requirements.do" })
	public String listExperienceTypeRequirements(@RequestParam("aircraftTypeId") int aircraftTypeId, Model model,
			HttpSession session, RedirectAttributes redir) {

		User loggedInUser = session.getAttribute("loggedInUser") != null ? (User) session.getAttribute("loggedInUser")
				: null;

		if (loggedInUser == null) {
			redir.addFlashAttribute("message", "You must be logged in.");
			return "redirect:login.do";
		}

		if (loggedInUser.getRole().getId() != 3) {
			redir.addFlashAttribute("message",
					"You must be logged in a Commander to view or edit additional aircraft types.");
			return "redirect:login.do";
		}

		List<AircraftType> aircraftTypes = commanderDAO.findAllAircraftType();

		model.addAttribute("aircraftTypeId", aircraftTypeId);

		model.addAttribute("aircraftTypes", aircraftTypes);

		return "commander/list_aircraft_types";
	}

}
