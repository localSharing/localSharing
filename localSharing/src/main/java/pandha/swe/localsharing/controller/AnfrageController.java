package pandha.swe.localsharing.controller;

import static pandha.swe.localsharing.util.VornamenWandler.erzeugeVornameFuerAngebotsseite;

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
import pandha.swe.localsharing.model.dto.AngebotDTO;
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
	
	@Autowired
	private AngebotService angebotService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/anfragen")
	public String showInquiries(
			Model model,
			Principal principal) {
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		
		List<Anfrage> gesendeteAnfragen = anfrageSerivce.findAllBySender(user);
		List<AnfrageDTO> gesendeteAnfragenDTO = anfrageSerivce.list_Anfrage_TO_AnfrageDTO(gesendeteAnfragen);
		
		model.addAttribute("anfragenListG", gesendeteAnfragenDTO);
		
		List<Anfrage> empfangeneAnfragen = anfrageSerivce.findAllByEmpfaenger(user);
		List<AnfrageDTO> empfangeneAnfragenDTO = anfrageSerivce.list_Anfrage_TO_AnfrageDTO(empfangeneAnfragen);
		
		model.addAttribute("anfragenListE", empfangeneAnfragenDTO);
		
		return "anfragen";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{id}/inquire")
	public String writeInquiry(
			Model model,
			Principal principal,
			@PathVariable("id") String angebotsid) {
		
		// TODO Angebot ermitteln
//		Angebot angebot = angebotService.findById(Long.valueOf(angebotsid));
//		AngebotDTO angebotDTO = angebotService.angebot_TO_AngebotDTO(angebot);
		Ausleihartikel angebot = ausleihartikelService.findById(Long.valueOf(angebotsid));
		AusleihartikelDTO angebotDTO = ausleihartikelService.angebot_TO_AngebotDTO(angebot);

		Boolean benutzerGleich = benutzerService.sindDieBenutzerGleich(
				benutzerService.getUserByPrincipal(principal),
				angebotDTO.getBenutzer());
		if (benutzerGleich) {
			// TODO Redirect ohne "ausleihen"
			return "redirect:../" + angebotsid + "/ausleihen";
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
		
		Angebot angebot = angebotService.findAngebotById(Long.valueOf(angebotsid));
		anfrageDTO.setAngebot(angebot);
		
		anfrageDTO.setStatus(AnfrageStatus.offen);
		
		anfrageSerivce.createAnfrage(anfrageDTO);
		
		// TODO Redirect nur über ID (ohne "ausleihen")
		return "redirect:../" + angebotsid + "/ausleihen";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{angebotsid}/inquiry/{inquiryid}")
	public String showInquiry(
			Model model,
			Principal principal,
			@PathVariable("angebotsid") String angebotsid,
			@PathVariable("inquiryid") String inquiryid) {
		
		Anfrage anfrage = anfrageSerivce.findById(Long.valueOf(inquiryid));
		Angebot angebot = anfrage.getAngebot();
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		
		Boolean principalIstAnfrageEmpfaenger = 
				benutzerService.sindDieBenutzerGleich(
						user,
						angebot.getBenutzer());
		Boolean principalIstAnfrageSender = 
				benutzerService.sindDieBenutzerGleich(
						user,
						anfrage.getSender());
		
		if (! (principalIstAnfrageEmpfaenger || principalIstAnfrageSender)) {
			return "redirect:../../../anfragen";
		}
		
		AnfrageDTO anfrageDTO = anfrageSerivce.anfrage_TO_AnfrageDTO(anfrage);
		model.addAttribute("anfrage", anfrageDTO);
		AngebotDTO angebotDTO = angebotService.angebot_TO_AngebotDTO(angebot);
		model.addAttribute("angebot", angebotDTO);
		
		if (principalIstAnfrageSender) {
			model.addAttribute("gesendet",  true);
		} else {
			model.addAttribute("gesendet",  false);
			model.addAttribute("sendername",  erzeugeVornameFuerAngebotsseite(anfrage.getSender().getVorname()));
		}

		return "anfrage";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/inquiry/{id}/accept")
	public String acceptInquiry(
			Model model,
			Principal principal,
			@PathVariable("id") String id) {
		
		Anfrage anfrage = anfrageSerivce.findById(Long.valueOf(id));
		Angebot angebot = anfrage.getAngebot();
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		
		Boolean principalIstAnfrageEmpfaenger = 
				benutzerService.sindDieBenutzerGleich(
						user,
						angebot.getBenutzer());
		
		if (!(AnfrageStatus.offen.equals(anfrage.getStatus()) 
				&& principalIstAnfrageEmpfaenger)) {
			return "redirect:../../anfragen";
		}
		
		anfrage.setStatus(AnfrageStatus.angenommen);
		anfrageSerivce.update(anfrage);
		
		
		return "redirect:../../angebot/" + angebot.getAngebotsid() + "/inquiry/" + id;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/inquiry/{id}/decline")
	public String declineInquiry(
			Model model,
			Principal principal,
			@PathVariable("id") String id) {
		
		Anfrage anfrage = anfrageSerivce.findById(Long.valueOf(id));
		Angebot angebot = anfrage.getAngebot();
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		
		Boolean principalIstAnfrageEmpfaenger = 
				benutzerService.sindDieBenutzerGleich(
						user,
						angebot.getBenutzer());
		
		if (!(AnfrageStatus.offen.equals(anfrage.getStatus()) 
				&& principalIstAnfrageEmpfaenger)) {
			return "redirect:../../anfragen";
		}
		
		anfrage.setStatus(AnfrageStatus.abgelehnt);
		anfrageSerivce.update(anfrage);
		
		
		return "redirect:../../angebot/" + angebot.getAngebotsid() + "/inquiry/" + id;
	}

}
