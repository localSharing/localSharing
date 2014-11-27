package pandha.swe.localsharing.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.BenutzerService;

@Controller
public class AngebotController {

	@Autowired
	private AusleihartikelService ausleihartikelService;

	@Autowired
	private BenutzerService benutzerService;

	@RequestMapping(method = RequestMethod.GET, value = "/angebote")
	public String showAngebote(Model model, Principal principal) {

		Benutzer user = getUser(principal);

		AusleihartikelDTO ausleihartikel1 = new AusleihartikelDTO(new Long(1),
				user, "Tarzan", "Tolle DVD", Date.valueOf("2014-11-27"),
				Date.valueOf("2014-12-27"), 3, "DVD");

		AusleihartikelDTO ausleihartikel2 = new AusleihartikelDTO(new Long(2),
				user, "Mickey Mouse", "tolles Buch",
				Date.valueOf("2014-11-27"), Date.valueOf("2014-12-27"), 3,
				"Buch");

		AusleihartikelDTO ausleihartikel3 = new AusleihartikelDTO(new Long(3),
				user, "Simpsons", "Tolle DVD", Date.valueOf("2014-11-27"),
				Date.valueOf("2014-12-27"), 3, "DVD");

		// Liste mit allen Ausleihangeboten bei denen Benutzer = user

		// Liste Model hinzufügen

		List<AusleihartikelDTO> aArtikel = new ArrayList<AusleihartikelDTO>();
		aArtikel.add(ausleihartikel1);
		aArtikel.add(ausleihartikel2);
		aArtikel.add(ausleihartikel3);

		model.addAttribute("artikelList", aArtikel);

		return "angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{ausleihenID}")
	public String showAngebot(Model model, Principal principal,
			@PathVariable("ausleihenID") String ausleihenID) {

		System.out.println(ausleihenID);

		return "angebot";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotEdit")
	public String editAngebot(Model model, Principal principal) {

		return "angebotEdit";
	}

	private Benutzer getUser(Principal principal) {
		String email = principal.getName();

		Benutzer user = benutzerService.findByEmail(email);

		return user;
	}

}
