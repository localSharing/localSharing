package pandha.swe.localsharing.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.service.AnfrageService;

@Controller
public class AnfrageController {
	
	@Autowired
	private AnfrageService anfrageSerivce;
	
	@RequestMapping(method = RequestMethod.GET, value = "/anfragen/{id}")
	public String showInquiries(
			Model model,
			Principal principal,
			@PathVariable("id") String userid) {
		
		// TODO Implement method to open page on which all inquiries are shown
		
		return "alleAnfragen";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{id}/inquire")
	public String writeInquiry(
			Model model,
			Principal principal,
			@PathVariable("id") String angebotsid) {
		
		// TODO Implement method to open page "Angebot senden"

		return "anfrageSenden";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/angebot/{id}/inquire")
	public String submitInquiry(
			Model model,
			Principal principal,
			@PathVariable("id") String angebotsid) {
		
		// TODO Implement method to submit inquiry
		
		// TODO Redirect nur über ID (ohne "ausleihen")
		return "redirect:../" + angebotsid + "/ausleihen";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{angbotsid}/inquire/{inquiryid}")
	public String showInquiry(
			Model model,
			Principal principal,
			@PathVariable("angebotsid") String angebotsid,
			@PathVariable("inquiryid") String inquiryid) {
		
		// TODO Implement method to open page "Angebot senden"

		return "anfrageSenden";
	}

}
