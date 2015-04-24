package pandha.swe.localsharing.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;
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

	@Autowired
	private FileService fileService;

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
	public String showAngebot(Model model, @PathVariable("ID") String id,
			@PathVariable("Type") String type) {

		switch (type) {
		case "ausleihen":
			addAusleihAngebotToModel(model, id);
			break;

		case "tauschen":
			addTauschAngebotToModel(model, id);
			break;

		case "helfen":
			addHilfsAngebotToModel(model, id);
			break;
		default:
			return "redirect:angebote";
		}

		if (!model.containsAttribute("angebot")) {
			return "redirect:angebote";
		}

		return "angebot";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotEdit/{id}/{type}")
	public String editAngebot(Model model, @PathVariable("id") String id,
			@PathVariable("type") String type) {

		switch (type) {
		case "ausleihen":
			addAusleihAngebotToModel(model, id);
			break;

		case "tauschen":
			addTauschAngebotToModel(model, id);

			break;

		case "helfen":
			addHilfsAngebotToModel(model, id);

			break;
		}

		return "angebotEdit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/ausleihen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid AusleihartikelDTO angebot,
			@PathVariable("id") String id,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Ausleihartikel ausleihartikel = ausleihartikelService
				.ausleihartikelDTO_TO_Ausleihartikel(angebot);

		ausleihartikelService.update(ausleihartikel);

		if (!image.isEmpty()) {
			fileService.save(ausleihartikel, image);
		}

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/tauschen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid TauschartikelDTO angebot,
			@PathVariable("id") String id,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Tauschartikel artikel = tauschartikelService
				.tauschartikelDTO_TO_Tauschartikel(angebot);

		tauschartikelService.update(artikel);

		if (!image.isEmpty()) {
			fileService.save(artikel, image);
		}

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/helfen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid HilfeleistungDTO angebot,
			@PathVariable("id") String id,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Hilfeleistung hilfe = hilfeleistungService
				.hilfeleistungDTO_TO_Hilfeleistung(angebot);

		hilfeleistungService.update(hilfe);

		if (!image.isEmpty()) {
			fileService.save(hilfe, image);
		}

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/ausleihen")
	public String deleteAusleihartikel(@PathVariable("id") String id,
			Model model, Principal principal) {

		Ausleihartikel ausleihartikel = ausleihartikelService
				.findById(new Long(id));

		ausleihartikelService.delete(ausleihartikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}/tauschen")
	public String deleteTauschartikel(
			@ModelAttribute("angebot") @Valid TauschartikelDTO angebot,
			@PathVariable("id") String id, Model model, Principal principal) {

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Tauschartikel artikel = tauschartikelService
				.tauschartikelDTO_TO_Tauschartikel(angebot);

		tauschartikelService.delete(artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/tauschen")
	public String deleteTauschartikel(@PathVariable("id") String id,
			Model model, Principal principal) {

		Tauschartikel artikel = tauschartikelService.findById(new Long(id));

		tauschartikelService.delete(artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}/helfen")
	public String deleteHilfeleistung(
			@ModelAttribute("angebot") @Valid HilfeleistungDTO angebot,
			Principal principal) {

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

		Hilfeleistung artikel = hilfeleistungService.findById(new Long(id));

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
			@ModelAttribute("newAngebot") @Valid AusleihartikelDTO newAngebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		newAngebot.setBenutzer(user);

		Long id = ausleihartikelService.createAusleihartikel(newAngebot);

		Ausleihartikel angebot = ausleihartikelService.findById(id);

		if (!image.isEmpty()) {
			fileService.save(angebot, image);
		}

		return "redirect:../angebote";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotNeu/tauschen")
	public String saveNewTauschen(
			@ModelAttribute("newAngebot") @Valid TauschartikelDTO newAngebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		newAngebot.setBenutzer(user);

		Long id = tauschartikelService.createTauschartikel(newAngebot);

		Tauschartikel angebot = tauschartikelService.findById(id);

		if (!image.isEmpty()) {
			fileService.save(angebot, image);
		}

		return "redirect:../angebote";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotNeu/helfen")
	public String saveNewHelfen(
			@ModelAttribute("newAngebot") @Valid HilfeleistungDTO newAngebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		newAngebot.setBenutzer(user);

		Long id = hilfeleistungService.createHilfeleistung(newAngebot);

		Hilfeleistung angebot = hilfeleistungService.findById(id);

		if (!image.isEmpty()) {
			fileService.save(angebot, image);
		}

		return "redirect:../angebote";

	}

	private Benutzer getUser(Principal principal) {
		String email = principal.getName();

		Benutzer user = benutzerService.findByEmail(email);

		return user;
	}

	private void addHilfsAngebotToModel(Model model, String id) {
		HilfeleistungDTO hilfeleistung = hilfeleistungService
				.hilfeleistung_TO_HilfeleistungDTO(hilfeleistungService
						.findById(new Long(id)));

		if (hilfeleistung != null) {
			model.addAttribute("angebot", hilfeleistung);
			model.addAttribute("endDatum", hilfeleistung.getEndDatum());
		}
	}

	private void addTauschAngebotToModel(Model model, String id) {
		TauschartikelDTO tauschartikel = tauschartikelService
				.tauschartikel_TO_TauschartikelDTO(tauschartikelService
						.findById(new Long(id)));

		if (tauschartikel != null) {
			model.addAttribute("angebot", tauschartikel);
			model.addAttribute("kategorie", tauschartikel.getKategorie());
		}
	}

	private void addAusleihAngebotToModel(Model model, String id) {
		AusleihartikelDTO ausleihartikel = ausleihartikelService
				.ausleihartikel_TO_AusleihartikelDTO(ausleihartikelService
						.findById(new Long(id)));

		if (ausleihartikel != null) {
			model.addAttribute("angebot", ausleihartikel);
			model.addAttribute("endDatum", ausleihartikel.getEndDatum());
			model.addAttribute("kategorie", ausleihartikel.getKategorie());
			model.addAttribute("dauer", ausleihartikel.getDauer());
		}
	}

}
