package pandha.swe.localsharing.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

@Controller
public class AngebotController {

	@Autowired
	private AusleihartikelService ausleihartikelService;

	@Autowired
	private TauschartikelService tauschartikelService;

	@Autowired
	private HilfeleistungService hilfeleistungService;

	@Autowired
	private BenutzerService benutzerService;

	@RequestMapping(method = RequestMethod.GET, value = "/angebote")
	public String showAngebote(Model model, Principal principal) {

		Benutzer user = getUser(principal);

		// Beispieldaten zum Anzeigen

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

		TauschartikelDTO tauschartikel1 = new TauschartikelDTO(new Long(1),
				user, "Tarzan", "Tolle DVD", Date.valueOf("2014-11-27"), "DVD");

		TauschartikelDTO tauschartikel2 = new TauschartikelDTO(new Long(2),
				user, "Mickey Mouse", "tolles Buch",
				Date.valueOf("2014-11-27"), "Buch");

		TauschartikelDTO tauschartikel3 = new TauschartikelDTO(new Long(3),
				user, "Simpsons", "Tolle DVD", Date.valueOf("2014-11-27"),
				"DVD");

		HilfeleistungDTO hilfe1 = new HilfeleistungDTO(new Long(1), user,
				"Streichen", "Ich kann das", Date.valueOf("2014-11-23"),
				Date.valueOf("2014-12-11"));

		HilfeleistungDTO hilfe2 = new HilfeleistungDTO(new Long(2), user,
				"Rasen mähen", "Ich bin super", Date.valueOf("2014-11-23"),
				Date.valueOf("2014-12-11"));

		HilfeleistungDTO hilfe3 = new HilfeleistungDTO(new Long(3), user,
				"Einkaufen", "Ich will eben", Date.valueOf("2014-11-23"),
				Date.valueOf("2014-12-11"));

		// Liste mit allen Ausleihangeboten eines Benutzers
		// List<AusleihartikelDTO> aArtikel = ausleihartikelService
		// .findAllByBenutzer(user);

		// Liste mit allen Tauschangeboten eines Benutzers
		// List<TauschartikelDTO> tArtikel = tauschartikelService
		// .findAllByBenutzer(user);

		// Liste mit allen Hilfeleistungen eines Benutzers
		// List<TauschartikelDTO> hArtikel = hilfeleistungService
		// .findAllByBenutzer(user);

		// Liste Model hinzufügen
		// model.addAttribute("artikelListA", aArtikel);
		// model.addAttribute("artikelListT", tArtikel);
		// model.addAttribute("artikelListH", hArtikel);

		List<AusleihartikelDTO> aArtikel = new ArrayList<AusleihartikelDTO>();
		aArtikel.add(ausleihartikel1);
		aArtikel.add(ausleihartikel2);
		aArtikel.add(ausleihartikel3);

		List<TauschartikelDTO> tArtikel = new ArrayList<TauschartikelDTO>();
		tArtikel.add(tauschartikel1);
		tArtikel.add(tauschartikel2);
		tArtikel.add(tauschartikel3);

		List<HilfeleistungDTO> hArtikel = new ArrayList<HilfeleistungDTO>();
		hArtikel.add(hilfe1);
		hArtikel.add(hilfe2);
		hArtikel.add(hilfe3);

		model.addAttribute("artikelListA", aArtikel);
		model.addAttribute("artikelListT", tArtikel);
		model.addAttribute("artikelListH", hArtikel);

		return "angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{ID}/{Type}")
	public String showAngebot(Model model, Principal principal,
			@PathVariable("ID") String id, @PathVariable("Type") String type) {

		Benutzer user = getUser(principal);
		switch (type) {
		case "ausleihen":
			// AusleihartikelDTO ausleihartikel = ausleihartikelService
			// .ausleihartikel_TO_AusleihartikelDTO(ausleihartikelService
			// .findById(new Long(id)));

			// Daten zum Anzeigen in der View
			AusleihartikelDTO ausleihartikel = new AusleihartikelDTO(new Long(
					id), user, "Tarzan", "Tolle DVD",
					Date.valueOf("2014-11-27"), Date.valueOf("2014-12-27"), 3,
					"DVD");

			model.addAttribute("angebot", ausleihartikel);
			model.addAttribute("endDatum", ausleihartikel.getEndDatum());
			model.addAttribute("kategorie", ausleihartikel.getKategorie());
			model.addAttribute("dauer", ausleihartikel.getDauer());
			break;

		case "tauschen":
			// TauschartikelDTO tauschartikel = tauschartikelService
			// .tauschartikel_TO_TauschartikelDTO(tauschartikelService
			// .findById(new Long(id)));

			// Daten zum Anzeigen
			TauschartikelDTO tauschartikel = new TauschartikelDTO(new Long(id),
					user, "Mickey Mouse", "tolles Buch",
					Date.valueOf("2014-11-27"), "Buch");

			model.addAttribute("angebot", tauschartikel);
			model.addAttribute("kategorie", tauschartikel.getKategorie());

			break;

		case "helfen":
			// HilfeleistungDTO hilfeleistung = hilfeleistungService
			// .hilfeleistung_TO_HilfeleistungDTO(hilfeleistungService
			// .findById(new Long(id)));

			// Daten zum Anzeigen
			HilfeleistungDTO hilfeleistung = new HilfeleistungDTO(new Long(id),
					user, "Einkaufen", "Ich will eben",
					Date.valueOf("2014-11-23"), Date.valueOf("2014-12-11"));

			model.addAttribute("angebot", hilfeleistung);
			model.addAttribute("endDatum", hilfeleistung.getEndDatum());

			break;
		}

		return "angebot";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotEdit/{id}/{type}")
	public String editAngebot(Model model, Principal principal,
			@PathVariable("id") String id, @PathVariable("type") String type) {

		Benutzer user = getUser(principal);

		switch (type) {
		case "ausleihen":
			// AusleihartikelDTO ausleihartikel = ausleihartikelService
			// .ausleihartikel_TO_AusleihartikelDTO(ausleihartikelService
			// .findById(new Long(id)));

			// Daten zum Anzeigen in der View
			AusleihartikelDTO ausleihartikel = new AusleihartikelDTO(new Long(
					id), user, "Tarzan", "Tolle DVD",
					Date.valueOf("2014-11-27"), Date.valueOf("2014-12-27"), 3,
					"DVD");

			model.addAttribute("angebot", ausleihartikel);
			model.addAttribute("endDatum", ausleihartikel.getEndDatum());
			model.addAttribute("kategorie", ausleihartikel.getKategorie());
			model.addAttribute("dauer", ausleihartikel.getDauer());
			break;

		case "tauschen":
			// TauschartikelDTO tauschartikel = tauschartikelService
			// .tauschartikel_TO_TauschartikelDTO(tauschartikelService
			// .findById(new Long(id)));

			// Daten zum Anzeigen
			TauschartikelDTO tauschartikel = new TauschartikelDTO(new Long(id),
					user, "Mickey Mouse", "tolles Buch",
					Date.valueOf("2014-11-27"), "Buch");

			model.addAttribute("angebot", tauschartikel);
			model.addAttribute("kategorie", tauschartikel.getKategorie());

			break;

		case "helfen":
			// HilfeleistungDTO hilfeleistung = hilfeleistungService
			// .hilfeleistung_TO_HilfeleistungDTO(hilfeleistungService
			// .findById(new Long(id)));

			// Daten zum Anzeigen
			HilfeleistungDTO hilfeleistung = new HilfeleistungDTO(new Long(id),
					user, "Einkaufen", "Ich will eben",
					Date.valueOf("2014-11-23"), Date.valueOf("2014-12-11"));

			model.addAttribute("angebot", hilfeleistung);
			model.addAttribute("endDatum", hilfeleistung.getEndDatum());

			break;
		}

		return "angebotEdit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/ausleihen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") AusleihartikelDTO angebot, Model model,
			Principal principal) {

		System.out.println(angebot);

		// Ausleihartikel ausleihartikel = ausleihartikelService
		// .ausleihartikelDTO_TO_Ausleihartikel(angebot);

		// System.out.println(ausleihartikel);

		// ausleihartikelService.update(ausleihartikel);

		return "redirect:../angebote";
	}

	private Benutzer getUser(Principal principal) {
		String email = principal.getName();

		Benutzer user = benutzerService.findByEmail(email);

		return user;
	}

}
