package pandha.swe.localsharing.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.dto.AnfrageDTO;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.service.AnfrageService;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.BenutzerService;

@Controller
public class AnfrageController {
	
	@Autowired
	private AnfrageService anfrageSerivce;
	
	@Autowired
	private BenutzerService benutzerService;
	
	@Autowired
	private AusleihartikelService ausleihartikelService;

//	@Autowired
//	private LS_AngebotService<Angebot, AngebotDTO> angebotService;
	
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
		
		Boolean benutzerGleich = benutzerService.sindDieBenutzerGleich(
				benutzerService.getUserByPrincipal(principal),
				benutzerService.findByAngebotsId(Long.valueOf(angebotsid)));
		if (benutzerGleich) {
			// TODO Redirect ohne "ausleihen"
			return "redirect:../" + angebotsid + "ausleihen";
		}
//		Angebot angebot = angebotService.findById(Long.valueOf(angebotsid));
//		AngebotDTO angebotDTO = angebotService.angebot_TO_AngebotDTO(angebot);
		Ausleihartikel angebot = ausleihartikelService.findById(Long.valueOf(angebotsid));
		AusleihartikelDTO angebotDTO = ausleihartikelService.angebot_TO_AngebotDTO(angebot);
		model.addAttribute("angebot", angebotDTO);
		if (angebotDTO.getEndDatum() != null) {
			model.addAttribute("endDatum", angebotDTO.getEndDatum());
		}
		model.addAttribute("anfrage", new AnfrageDTO());
		
		return "anfrageSenden";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/angebot/{id}/inquire")
	public String submitInquiry(
			Model model,
			Principal principal,
			@PathVariable("id") String angebotsid,
			@ModelAttribute("anfrage") @Valid AnfrageDTO anfrageDTO) {
		
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
