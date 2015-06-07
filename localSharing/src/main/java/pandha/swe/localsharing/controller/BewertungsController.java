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
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AnfrageDTO;
import pandha.swe.localsharing.model.dto.AngebotDTO;
import pandha.swe.localsharing.model.dto.BewertungDTO;
import pandha.swe.localsharing.service.AnfrageService;
import pandha.swe.localsharing.service.AngebotService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.BewertungService;

@Controller
public class BewertungsController {

	@Autowired
	private AngebotService angebotService;

	@Autowired
	private BenutzerService benutzerService;

	@Autowired
	private BewertungService bewertungService;
	
	@Autowired
	private AnfrageService anfrageService;

	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{id}/rate")
	public String bewerteAngebot(Principal principal, Model model,
			@PathVariable("id") String id) {

		Angebot angebot = angebotService.findAngebotById(Long.valueOf(id));
		AngebotDTO angebotDTO = angebotService.angebot_TO_AngebotDTO(angebot);

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		Benutzer angebotsersteller = angebotDTO.getBenutzer();
		if (benutzerService.sindDieBenutzerGleich(user, angebotsersteller) || !bewertungService.istBewertenErlaubt(user, angebot)) {
			return "redirect:/angebot/" + id;
		}

		BewertungDTO bewertung = new BewertungDTO();
		List<Anfrage> anfragen = anfrageService.findAngenommeneAnfragenByAngebotAndSender(angebot, user);
		List<AnfrageDTO> anfragenDTO = anfrageService.list_Anfrage_TO_AnfrageDTO(anfragen);

		model.addAttribute("bewertung", bewertung);
		model.addAttribute("angebot", angebotDTO);
		model.addAttribute("anfragen", anfragenDTO);

		return "bewerten";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebot/{id}/rate")
	public String saveRating(Principal principal,
			@PathVariable("id") String id,
			@ModelAttribute("bewertung") @Valid BewertungDTO bewertungDTO) {

		bewertungDTO.setDatum(new Date(System.currentTimeMillis()));

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		bewertungDTO.setBewerter(user);

		Angebot angebot = angebotService.findAngebotById(Long.valueOf(id));
		bewertungDTO.setAngebot(angebot);

		bewertungService.createBewertung(bewertungDTO);
		return "redirect:/angebot/" + id;
	}
	
}
