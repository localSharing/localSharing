package pandha.swe.localsharing.controller;

import static pandha.swe.localsharing.util.VornameAngebotsseiteWandler.erzeugeVornameFuerAngebotsseite;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.BewertungDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.model.enums.Rollen;
import pandha.swe.localsharing.service.AngebotService;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.BewertungService;
import pandha.swe.localsharing.service.FileService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

@Controller
public class AngebotController {
	
	@Autowired
	private AngebotService angebotService;

	@Autowired
	private AusleihartikelService ausleihartikelService;

	@Autowired
	private TauschartikelService tauschartikelService;

	@Autowired
	private HilfeleistungService hilfeleistungService;

	@Autowired
	private BenutzerService benutzerService;

	@Autowired
	private BewertungService bewertungService;

	@Autowired
	private FileService fileService;

	@RequestMapping(method = RequestMethod.GET, value = "/angebote/{id}")
	public String showAngebote(Model model, Principal principal,
			@PathVariable("id") String userid) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		List<AusleihartikelDTO> aArtikel = null;
		List<TauschartikelDTO> tArtikel = null;
		List<HilfeleistungDTO> hArtikel = null;

		// Eigene Angebote
		if (user.getId().equals(Long.valueOf(userid))) {
			aArtikel = ausleihartikelService.findAllByBenutzer(user);
			tArtikel = tauschartikelService.findAllByBenutzer(user);
			hArtikel = hilfeleistungService.findAllByBenutzer(user);

			model.addAttribute("titel", "Deine");
		} else {
			Benutzer angebotsersteller = benutzerService.findById(Long
					.valueOf(userid));

			if (angebotsersteller != null && angebotsersteller.isEnabled()) {
				aArtikel = ausleihartikelService
						.findAllEnabledByBenutzer(angebotsersteller);
				tArtikel = tauschartikelService
						.findAllEnabledByBenutzer(angebotsersteller);
				hArtikel = hilfeleistungService
						.findAllEnabledByBenutzer(angebotsersteller);

				model.addAttribute("titel",
						erzeugeVornameFuerAngebotsseite(angebotsersteller
								.getVorname()));
			}
		}

		if (aArtikel == null || tArtikel == null || hArtikel == null) {
			return "redirect:angebote";
		}

		// Liste Model hinzufügen
		model.addAttribute("artikelListA", aArtikel);
		model.addAttribute("artikelListT", tArtikel);
		model.addAttribute("artikelListH", hArtikel);

		return "angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebote")
	public String showAngebote(Model model) {

		// Liste mit allen Ausleihangeboten eines Benutzers
		List<AusleihartikelDTO> aArtikel = ausleihartikelService.findAllEnabled();

		// Liste mit allen Tauschangeboten eines Benutzers
		List<TauschartikelDTO> tArtikel = tauschartikelService.findAllEnabled();

		// Liste mit allen Hilfeleistungen eines Benutzers
		List<HilfeleistungDTO> hArtikel = hilfeleistungService.findAllEnabled();

		// Liste Model hinzufügen
		model.addAttribute("titel", "Alle");
		model.addAttribute("artikelListA", aArtikel);
		model.addAttribute("artikelListT", tArtikel);
		model.addAttribute("artikelListH", hArtikel);

		return "angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebote/disabled")
	public String showDisabledAngebote(Principal principal, Model model) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		if (!benutzerService.hatBenutzerRolle(user, Rollen.ADMIN)) {
			return "redirect:../angebote";
		}

		List<AusleihartikelDTO> aArtikel = ausleihartikelService
				.findAllDisabled();
		List<TauschartikelDTO> tArtikel = tauschartikelService
				.findAllDisabled();
		List<HilfeleistungDTO> hArtikel = hilfeleistungService
				.findAllDisabled();

		// Liste Model hinzufügen
		model.addAttribute("titel", "Deaktivierte");
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

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		Benutzer angebotsersteller = benutzerService.findByAngebotsIdAndType(
				Long.valueOf(id), type);
		if (angebotsersteller == null) {
			return "redirect:angebote";
		}

		boolean kommentarErlaubt = false;
		
		if (user.getId().equals(angebotsersteller.getId())) {
			model.addAttribute("besitzer", true);
		} else if (angebotService.getAngebotByIdAndType(Long.valueOf(id), type).getEnabled()) {
			model.addAttribute("besitzer", false);
			// TODO Wann ist es erlaubt zu kommentieren? > Anfragenmanagement
			if (true) {
				kommentarErlaubt = true;
			}
		} else {
			return "redirect:../../angebote";
		}

//		Angebot angebot = angebotService.getAngebotByIdAndType(Long.valueOf(id), type);
//		List<Bewertung> bewertungen = bewertungService.findByAngebot(angebot);
//		List<BewertungDTO> bewertungenDTO = bewertungService.list_Bewertung_TO_BewertungDTO(bewertungen);
		List<BewertungDTO> bewertungenDTO = bewertungService.erzeugeDummyDaten();
		model.addAttribute("bewertungen", bewertungenDTO);
		model.addAttribute("kommentarErlaubt", kommentarErlaubt);
		
		return addAngebotToModel(model, id, type, "angebot");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/angebotEdit/{id}/{type}")
	public String editAngebot(Model model, Principal principal,
			@PathVariable("id") String id, @PathVariable("type") String type) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		Benutzer angebotsersteller = benutzerService.findByAngebotsIdAndType(
				Long.valueOf(id), type);
		if (user != null && angebotsersteller != null
				&& !user.getId().equals(angebotsersteller.getId())) {
			return "redirect:../../angebot/" + id + "/" + type;
		}

		model.addAttribute("userid", user.getId());

		return addAngebotToModel(model, id, type, "angebotEdit");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/ausleihen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid AusleihartikelDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		angebot.setBenutzer(user);

		Ausleihartikel ausleihartikel = ausleihartikelService
				.ausleihartikelDTO_TO_Ausleihartikel(angebot);

		ausleihartikelService.update(ausleihartikel);

		saveImageIfExists(image, ausleihartikel);

		return "redirect:../../angebote/" + user.getId();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/tauschen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid TauschartikelDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		angebot.setBenutzer(user);

		Tauschartikel artikel = tauschartikelService
				.tauschartikelDTO_TO_Tauschartikel(angebot);

		tauschartikelService.update(artikel);

		saveImageIfExists(image, artikel);

		return "redirect:../../angebote/" + user.getId();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotEdit/{id}/helfen")
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid HilfeleistungDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		angebot.setBenutzer(user);

		Hilfeleistung hilfe = hilfeleistungService
				.hilfeleistungDTO_TO_Hilfeleistung(angebot);

		hilfeleistungService.update(hilfe);

		saveImageIfExists(image, hilfe);

		return "redirect:../../angebote/" + user.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/ausleihen")
	public String deleteAusleihartikel(Principal principal,
			@PathVariable("id") String id) {

		Ausleihartikel ausleihartikel = ausleihartikelService
				.findById(new Long(id));

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		Benutzer angebotsersteller = ausleihartikel.getBenutzer();
		if (!user.getId().equals(angebotsersteller.getId())) {
			return "redirect:../../angebot/" + id + "/" + "ausleihen";
		}

		ausleihartikelService.delete(ausleihartikel);

		return "redirect:../../angebote/" + user.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/tauschen")
	public String deleteTauschartikel(Principal principal,
			@PathVariable("id") String id) {

		Tauschartikel artikel = tauschartikelService.findById(new Long(id));

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		Benutzer angebotsersteller = artikel.getBenutzer();
		if (!user.getId().equals(angebotsersteller.getId())) {
			return "redirect:../../angebot/" + id + "/" + "tauschen";
		}

		tauschartikelService.delete(artikel);

		return "redirect:../../angebote/" + user.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}/helfen")
	public String deleteHilfeleistung(Principal principal,
			@PathVariable("id") String id) {

		Hilfeleistung artikel = hilfeleistungService.findById(new Long(id));

		Benutzer user = benutzerService.getUserByPrincipal(principal);
		Benutzer angebotsersteller = artikel.getBenutzer();
		if (!user.getId().equals(angebotsersteller.getId())) {
			return "redirect:../../angebot/" + id + "/" + "helfen";
		}

		hilfeleistungService.delete(artikel);

		return "redirect:../../angebote/" + user.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/disable/{id}/{type}")
	public String disableAngebot(Principal principal,
			@PathVariable("id") String id, @PathVariable("type") String type) {

		if (!benutzerService.hatBenutzerRolle(
				benutzerService.getUserByPrincipal(principal), Rollen.ADMIN)) {
			return "redirect:../../angebot/" + id + "/" + "ausleihen";
		}

		angebotActivation(id, type, Boolean.FALSE);

		return "redirect:../../angebot/" + id + "/" + type;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/enable/{id}/{type}")
	public String enableAngebot(Principal principal,
			@PathVariable("id") String id, @PathVariable("type") String type) {

		if (!benutzerService.hatBenutzerRolle(
				benutzerService.getUserByPrincipal(principal), Rollen.ADMIN)) {
			return "redirect:../../angebot/" + id + "/" + "ausleihen";
		}
		angebotActivation(id, type, Boolean.TRUE);

		return "redirect:../../angebot/" + id + "/" + type;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{id}/rate")
	public String bewerteAngebot(
			Principal principal,
			Model model,
			@PathVariable("id") String id) {

		// TODO Angebot mit ID holen
		Angebot angebot = angebotService.getAngebotByIdAndType(Long.valueOf(id), "ausleihen");
//		Angebot angebot = angebotService.getAngebotById(Long.valueOf(id));
		
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		Benutzer angebotsersteller = angebot.getBenutzer();
		if (!user.getId().equals(angebotsersteller.getId())) {
			return "redirect:../" + id + "/ausleihen";
		}
		
		BewertungDTO bewertung = new BewertungDTO();
		
		model.addAttribute("bewertung", bewertung);
		model.addAttribute("angebot", angebot);
		
		return "bewerten";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/angebot/{id}/rate")
	public String saveRating(
			Principal principal,
			@PathVariable("id") String id,
			@ModelAttribute("bewertung") @Valid BewertungDTO bewertungDTO) {
		
		bewertungDTO.setDatum(new Date(System.currentTimeMillis()));
		
		Benutzer user = benutzerService.getUserByPrincipal(principal);
		bewertungDTO.setBewerter(user);
		
		Angebot angebot = angebotService.getAngebotByIdAndType(Long.valueOf(id), "ausleihen");
//		Angebot angebot = angebotService.getAngebotById(Long.valueOf(id));
		bewertungDTO.setAngebot(angebot);

		bewertungService.createBewertung(bewertungDTO);
		// TODO Redirect nur durch Angebotsid ('ausleihen' löschen)
		return "redirect:../" + id + "/ausleihen";
	}

	
	private void angebotActivation(String id, String type, Boolean enable) {
		switch (type) {
		case "ausleihen":
			ausleihartikelActivation(id, enable);
			break;
		case "tauschen":
			tauschartikelActivation(id, enable);
			break;
		case "helfen":
			hilfsartikelActivation(id, enable);
			break;
		}
	}

	private void ausleihartikelActivation(String id, Boolean enable) {
		Ausleihartikel ausleihartikel = ausleihartikelService.findById(Long
				.valueOf(id));
		ausleihartikel.setEnabled(enable);
		ausleihartikelService.update(ausleihartikel);
	}

	private void tauschartikelActivation(String id, Boolean enable) {
		Tauschartikel tauschartikel = tauschartikelService.findById(Long
				.valueOf(id));
		tauschartikel.setEnabled(enable);
		tauschartikelService.update(tauschartikel);
	}

	private void hilfsartikelActivation(String id, Boolean enable) {
		Hilfeleistung hilfeleistung = hilfeleistungService.findById(Long
				.valueOf(id));
		hilfeleistung.setEnabled(enable);
		hilfeleistungService.update(hilfeleistung);
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

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		newAngebot.setBenutzer(user);

		Long id = ausleihartikelService.createAusleihartikel(newAngebot);

		Ausleihartikel angebot = ausleihartikelService.findById(id);

		saveImageIfExists(image, angebot);

		return "redirect:../angebote/" + user.getId();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotNeu/tauschen")
	public String saveNewTauschen(
			@ModelAttribute("newAngebot") @Valid TauschartikelDTO newAngebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		newAngebot.setBenutzer(user);

		Long id = tauschartikelService.createTauschartikel(newAngebot);

		Tauschartikel angebot = tauschartikelService.findById(id);

		saveImageIfExists(image, angebot);

		return "redirect:../angebote/" + user.getId();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/angebotNeu/helfen")
	public String saveNewHelfen(
			@ModelAttribute("newAngebot") @Valid HilfeleistungDTO newAngebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		Benutzer user = benutzerService.getUserByPrincipal(principal);

		newAngebot.setBenutzer(user);

		Long id = hilfeleistungService.createHilfeleistung(newAngebot);

		Hilfeleistung angebot = hilfeleistungService.findById(id);

		saveImageIfExists(image, angebot);

		return "redirect:../angebote/" + user.getId();

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
