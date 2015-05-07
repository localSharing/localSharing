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
		model.addAttribute("eigeneAngebote", true);
		model.addAttribute("artikelListA", aArtikel);
		model.addAttribute("artikelListT", tArtikel);
		model.addAttribute("artikelListH", hArtikel);

		return "angebote";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/alleAngebote")
	public String showAngebote(Model model) {

		// Liste mit allen Ausleihangeboten eines Benutzers
		List<AusleihartikelDTO> aArtikel = ausleihartikelService.findAllDTO();

		// Liste mit allen Tauschangeboten eines Benutzers
		List<TauschartikelDTO> tArtikel = tauschartikelService.findAllDTO();

		// Liste mit allen Hilfeleistungen eines Benutzers
		List<HilfeleistungDTO> hArtikel = hilfeleistungService.findAllDTO();

		// Liste Model hinzufügen
		model.addAttribute("eigeneAngebote", false);
		model.addAttribute("artikelListA", aArtikel);
		model.addAttribute("artikelListT", tArtikel);
		model.addAttribute("artikelListH", hArtikel);

		return "angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{ID}/{Type}")
	public String showAngebot(
			Model model,
			Principal principal,
			@PathVariable("ID") String id,
			@PathVariable("Type") String type) {
		
		Benutzer user = getUser(principal);
		Benutzer angebotsersteller = benutzerService.findByAngebotsIdAndType(Long.valueOf(id), type);
		if (user.getId().equals(angebotsersteller.getId())) {
			model.addAttribute("besitzer", true);
		} else {
			model.addAttribute("besitzer", false);
		}

		return addAngebotToModel(model, id, type, "angebot");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotEdit/{id}/{type}")
	public String editAngebot(
			Model model,
			Principal principal,
			@PathVariable("id") String id,
			@PathVariable("type") String type) {
		
		Benutzer user = getUser(principal);
		Benutzer angebotsersteller = benutzerService.findByAngebotsIdAndType(Long.valueOf(id), type);
		if (!user.getId().equals(angebotsersteller.getId())) {
			return "redirect:../../angebot/" + id + "/" + type;
		}
		
		return addAngebotToModel(model, id, type, "angebotEdit");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/ausleihen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid AusleihartikelDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Ausleihartikel ausleihartikel = ausleihartikelService
				.ausleihartikelDTO_TO_Ausleihartikel(angebot);

		ausleihartikelService.update(ausleihartikel);

		saveImageIfExists(image, ausleihartikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/tauschen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid TauschartikelDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Tauschartikel artikel = tauschartikelService
				.tauschartikelDTO_TO_Tauschartikel(angebot);

		tauschartikelService.update(artikel);

		saveImageIfExists(image, artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/helfen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid HilfeleistungDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = getUser(principal);

		angebot.setBenutzer(user);

		Hilfeleistung hilfe = hilfeleistungService
				.hilfeleistungDTO_TO_Hilfeleistung(angebot);

		hilfeleistungService.update(hilfe);

		saveImageIfExists(image, hilfe);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/ausleihen")
	public String deleteAusleihartikel(@PathVariable("id") String id) {

		Ausleihartikel ausleihartikel = ausleihartikelService
				.findById(new Long(id));

		ausleihartikelService.delete(ausleihartikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/tauschen")
	public String deleteTauschartikel(@PathVariable("id") String id) {

		Tauschartikel artikel = tauschartikelService.findById(new Long(id));

		tauschartikelService.delete(artikel);

		return "redirect:../../angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/helfen")
	public String deleteHilfeleistung(@PathVariable("id") String id) {

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

		saveImageIfExists(image, angebot);

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

		saveImageIfExists(image, angebot);

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

		saveImageIfExists(image, angebot);

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
		AusleihartikelDTO ausleihartikel = 
				ausleihartikelService.ausleihartikel_TO_AusleihartikelDTO(
						ausleihartikelService.findById(new Long(id)));

		if (ausleihartikel != null) {
			model.addAttribute("angebot", ausleihartikel);
			model.addAttribute("endDatum", ausleihartikel.getEndDatum());
			model.addAttribute("kategorie", ausleihartikel.getKategorie());
			model.addAttribute("dauer", ausleihartikel.getDauer());
		}
	}

	private String addAngebotToModel(Model model, String id, String type,
			String successReturn) {
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

		return successReturn;
	}

	private void saveImageIfExists(MultipartFile image, Ausleihartikel artikel) {
		if (isImageValid(image)) {
			fileService.save(artikel, image);
		}
	}

	private void saveImageIfExists(MultipartFile image, Tauschartikel artikel) {
		if (isImageValid(image)) {
			fileService.save(artikel, image);
		}
	}

	private void saveImageIfExists(MultipartFile image, Hilfeleistung artikel) {
		if (isImageValid(image)) {
			fileService.save(artikel, image);
		}
	}

	private boolean isImageValid(MultipartFile image) {
		return image != null && !image.isEmpty();
	}

}
