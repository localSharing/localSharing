package pandha.swe.localsharing.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.model.Anfrage;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AnfrageDTO;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.enums.AnfrageStatus;
import pandha.swe.localsharing.service.AnfrageService;
import pandha.swe.localsharing.service.AngebotService;
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
	
	@Autowired
	private AngebotService angebotService2;
	
	@RequestMapping(method = RequestMethod.GET, value = "/anfragen")
	public String showInquiries(
			Model model,
			Principal principal) {
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		
		List<Anfrage> gesendeteAnfragen = anfrageSerivce.findAllByEmpfaenger(user);
		List<AnfrageDTO> gesendeteAnfragenDTO = anfrageSerivce.list_Anfrage_TO_AnfrageDTO(gesendeteAnfragen);
		
		model.addAttribute("anfragenListG", gesendeteAnfragenDTO);
		
		List<Anfrage> empfangeneAnfragen = anfrageSerivce.findAllBySender(user);
		List<AnfrageDTO> empfangeneAnfragenDTO = anfrageSerivce.list_Anfrage_TO_AnfrageDTO(empfangeneAnfragen);
		
		model.addAttribute("anfragenListG", empfangeneAnfragenDTO);
		
		return "anfragen";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{id}/inquire")
	public String writeInquiry(
			Model model,
			Principal principal,
			@PathVariable("id") String angebotsid) {
		
//		Angebot angebot = angebotService.findById(Long.valueOf(angebotsid));
//		AngebotDTO angebotDTO = angebotService.angebot_TO_AngebotDTO(angebot);
		Ausleihartikel angebot = ausleihartikelService.findById(Long.valueOf(angebotsid));
		AusleihartikelDTO angebotDTO = ausleihartikelService.angebot_TO_AngebotDTO(angebot);

		Boolean benutzerGleich = benutzerService.sindDieBenutzerGleich(
				benutzerService.getUserByPrincipal(principal),
				angebotDTO.getBenutzer());
		if (benutzerGleich) {
			// TODO Redirect ohne "ausleihen"
			return "redirect:../" + angebotsid + "ausleihen";
		}
		model.addAttribute("angebot", angebotDTO);
		model.addAttribute("endDatum", angebotDTO.getEndDatum());
		model.addAttribute("dauer", angebotDTO.getDauer());
		model.addAttribute("anfrage", new AnfrageDTO());
		
		return "anfrageSenden";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/angebot/{id}/inquire")
	public String submitInquiry(
			Model model,
			Principal principal,
			@PathVariable("id") String angebotsid,
			@ModelAttribute("anfrage") @Valid AnfrageDTO anfrageDTO) {
		
		anfrageDTO.setDatum(new Date(System.currentTimeMillis()));
		
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		anfrageDTO.setSender(user);
		
		// TODO Anfrage für richtiges Angebot speichern
		Angebot angebot = angebotService2.findByIdAndType(Long.valueOf(angebotsid), "ausleihen");
		anfrageDTO.setAngebot(angebot);
		
		anfrageDTO.setStatus(AnfrageStatus.offen);
		
		anfrageSerivce.createAnfrage(anfrageDTO);
		
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
