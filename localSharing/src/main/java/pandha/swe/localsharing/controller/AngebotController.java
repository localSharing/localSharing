package pandha.swe.localsharing.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
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

		// Liste mit allen Ausleihangeboten eines Benutzers
		List<AusleihartikelDTO> aArtikel = ausleihartikelService
				.findAllByBenutzer(user);

		// Liste mit allen Tauschangeboten eines Benutzers
		List<TauschartikelDTO> tArtikel = tauschartikelService
				.findAllByBenutzer(user);

		// Liste mit allen Hilfeleistungen eines Benutzers
		List<HilfeleistungDTO> hArtikel = hilfeleistungService
				.findAllByBenutzer(user);

		// Liste Model hinzufügen
		model.addAttribute("artikelListA", aArtikel);
		model.addAttribute("artikelListT", tArtikel);
		model.addAttribute("artikelListH", hArtikel);

		return "angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{ID}/{Type}")
	public String showAngebot(Model model, Principal principal,
			@PathVariable("ID") String id, @PathVariable("Type") String type) {

		switch (type) {
		case "ausleihen":
			AusleihartikelDTO ausleihartikel = ausleihartikelService
					.ausleihartikel_TO_AusleihartikelDTO(ausleihartikelService
							.findById(new Long(id)));

			System.out.println(ausleihartikel);

			model.addAttribute("angebot", ausleihartikel);
			model.addAttribute("endDatum", ausleihartikel.getEndDatum());
			model.addAttribute("kategorie", ausleihartikel.getKategorie());
			model.addAttribute("dauer", ausleihartikel.getDauer());
			break;

		case "tauschen":
			TauschartikelDTO tauschartikel = tauschartikelService
					.tauschartikel_TO_TauschartikelDTO(tauschartikelService
							.findById(new Long(id)));

			model.addAttribute("angebot", tauschartikel);
			model.addAttribute("kategorie", tauschartikel.getKategorie());

			break;

		case "helfen":
			HilfeleistungDTO hilfeleistung = hilfeleistungService
					.hilfeleistung_TO_HilfeleistungDTO(hilfeleistungService
							.findById(new Long(id)));

			model.addAttribute("angebot", hilfeleistung);
			model.addAttribute("endDatum", hilfeleistung.getEndDatum());

			break;
		}

		return "angebot";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotEdit/{id}/{type}")
	public String editAngebot(Model model, Principal principal,
			@PathVariable("id") String id, @PathVariable("type") String type) {

		switch (type) {
		case "ausleihen":
			AusleihartikelDTO ausleihartikel = ausleihartikelService
					.ausleihartikel_TO_AusleihartikelDTO(ausleihartikelService
							.findById(new Long(id)));

			model.addAttribute("angebot", ausleihartikel);
			model.addAttribute("endDatum", ausleihartikel.getEndDatum());
			model.addAttribute("kategorie", ausleihartikel.getKategorie());
			model.addAttribute("dauer", ausleihartikel.getDauer());
			break;

		case "tauschen":
			TauschartikelDTO tauschartikel = tauschartikelService
					.tauschartikel_TO_TauschartikelDTO(tauschartikelService
							.findById(new Long(id)));

			model.addAttribute("angebot", tauschartikel);
			model.addAttribute("kategorie", tauschartikel.getKategorie());

			break;

		case "helfen":
			HilfeleistungDTO hilfeleistung = hilfeleistungService
					.hilfeleistung_TO_HilfeleistungDTO(hilfeleistungService
							.findById(new Long(id)));

			model.addAttribute("angebot", hilfeleistung);
			model.addAttribute("endDatum", hilfeleistung.getEndDatum());

			break;
		}

		return "angebotEdit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/ausleihen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") AusleihartikelDTO angebot,
			@PathVariable("id") String id, Model model, Principal principal) {

		System.out.println(angebot);

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Ausleihartikel ausleihartikel = ausleihartikelService
				.ausleihartikelDTO_TO_Ausleihartikel(angebot);

		System.out.println(ausleihartikel);

		ausleihartikelService.update(ausleihartikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/tauschen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") TauschartikelDTO angebot,
			@PathVariable("id") String id, Model model, Principal principal) {

		System.out.println(angebot);

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Tauschartikel artikel = tauschartikelService
				.tauschartikelDTO_TO_Tauschartikel(angebot);

		tauschartikelService.update(artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/helfen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") HilfeleistungDTO angebot,
			@PathVariable("id") String id, Model model, Principal principal) {

		System.out.println(angebot);

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Hilfeleistung hilfe = hilfeleistungService
				.hilfeleistungDTO_TO_Hilfeleistung(angebot);

		hilfeleistungService.update(hilfe);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/ausleihen")
	public String deleteAusleihartikel(@PathVariable("id") String id,
			Model model, Principal principal) {

		System.out.println("-----------DELETE-------------");

		Ausleihartikel ausleihartikel = ausleihartikelService
				.findById(new Long(id));

		System.out.println(ausleihartikel);

		ausleihartikelService.delete(ausleihartikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}/tauschen")
	public String deleteTauschartikel(
			@ModelAttribute("angebot") TauschartikelDTO angebot,
			@PathVariable("id") String id, Model model, Principal principal) {

		System.out.println(angebot);

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Tauschartikel artikel = tauschartikelService
				.tauschartikelDTO_TO_Tauschartikel(angebot);

		System.out.println(artikel);

		tauschartikelService.delete(artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/tauschen")
	public String deleteTauschartikel(@PathVariable("id") String id,
			Model model, Principal principal) {

		System.out.println("-----------DELETE-------------");

		Tauschartikel artikel = tauschartikelService.findById(new Long(id));

		System.out.println(artikel);

		tauschartikelService.delete(artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}/helfen")
	public String deleteHilfeleistung(
			@ModelAttribute("angebot") HilfeleistungDTO angebot,
			@PathVariable("id") String id, Model model, Principal principal) {

		System.out.println(angebot);

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Hilfeleistung artikel = hilfeleistungService
				.hilfeleistungDTO_TO_Hilfeleistung(angebot);

		hilfeleistungService.delete(artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/helfen")
	public String deleteHilfeleistung(@PathVariable("id") String id,
			Model model, Principal principal) {

		System.out.println("-----------DELETE-------------");

		Hilfeleistung artikel = hilfeleistungService.findById(new Long(id));

		System.out.println(artikel);

		hilfeleistungService.delete(artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotNeu/ausleihen")
	public String newAusleihen(Model model) {
		model.addAttribute("newAngebot", new AusleihartikelDTO());
		model.addAttribute("ausleihen", "ausleihen");
		return "angebotNeu";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotNeu/tauschen")
	public String newTauschen(Model model) {
		model.addAttribute("newAngebot", new TauschartikelDTO());
		model.addAttribute("tauschen", "tauschen");
		return "angebotNeu";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotNeu/helfen")
	public String newHelfen(Model model) {
		model.addAttribute("newAngebot", new HilfeleistungDTO());
		model.addAttribute("helfen", "helfen");
		return "angebotNeu";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotNeu/ausleihen")
	public String saveNewAusleihen(
			@ModelAttribute("newAngebot") AusleihartikelDTO newAngebot,
			Model model, Principal principal) {

		Benutzer user = getUser(principal);

		newAngebot.setBenutzer(user);

		System.out.println(newAngebot);

		ausleihartikelService.createAusleihartikel(newAngebot);

		return "redirect:../angebote";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotNeu/tauschen")
	public String saveNewTauschen(
			@ModelAttribute("newAngebot") TauschartikelDTO newAngebot,
			Model model, Principal principal) {

		Benutzer user = getUser(principal);

		newAngebot.setBenutzer(user);

		System.out.println(newAngebot);

		tauschartikelService.createTauschartikel(newAngebot);

		return "redirect:../angebote";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotNeu/helfen")
	public String saveNewHelfen(
			@ModelAttribute("newAngebot") HilfeleistungDTO newAngebot,
			Model model, Principal principal) {

		Benutzer user = getUser(principal);

		newAngebot.setBenutzer(user);

		System.out.println(newAngebot);

		hilfeleistungService.createHilfeleistung(newAngebot);

		return "redirect:../angebote";

	}

	private Benutzer getUser(Principal principal) {
		String email = principal.getName();

		Benutzer user = benutzerService.findByEmail(email);

		return user;
	}

}
