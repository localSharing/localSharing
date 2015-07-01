package pandha.swe.localsharing.controller.angebot.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.angebot.backend.BearbeiteDaten;
import pandha.swe.localsharing.controller.angebot.backend.BearbeiteEtwasBesitzer;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeEinAngebot;
import pandha.swe.localsharing.controller.angebot.backend.speichereDaten.LoescheEinAngebot;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;

@Controller
public class GET_LoescheEinAngebot extends BearbeiteEtwasBesitzer {

	private static final String REQUEST_URL = "/delete/{id}/{type}";
	private static String ERROR_VIEW = "redirect:angebote";

	private Long angebotsId;
	private String type;

	@Autowired
	private LoescheEinAngebot loeescheEinAngebot;

	@Autowired
	private LadeEinAngebot ladeEinAngebot;

	@RequestMapping(method = RequestMethod.GET, value = "/delete/angebot/{id}")
	protected String redirectAnfrage(Model model, Principal principal,
			@PathVariable("id") String angebotsId) {

		this.angebotsId = Long.valueOf(angebotsId);

		Angebot angebot = angebotService.findAngebotById(this.angebotsId);

		if (angebot instanceof Ausleihartikel) {
			return "redirect:/delete/angebot/" + angebotsId + "/ausleihen";
		} else if (angebot instanceof Tauschartikel) {
			return "redirect:/delete/angebot/" + angebotsId + "/tauschen";
		} else if (angebot instanceof Hilfeleistung) {
			return "redirect:/delete/angebot/" + angebotsId + "/tauschen";
		}

		return "redirect:/angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Principal principal,
			@PathVariable("id") String angebotsId,
			@PathVariable("type") String type) {
		this.angebotsId = Long.valueOf(angebotsId);
		this.type = type;
		ERROR_VIEW = "redirect:../../angebot/" + angebotsId + "/" + type;

		return bearbeiteAnfrageIntern(principal);
	}

	@Override
	protected LadeDaten attribute() {
		ladeEinAngebot.setId(angebotsId);
		ladeEinAngebot.setType(type);
		return ladeEinAngebot;
	}

	@Override
	protected String getSuccessView() {
		return "redirect:/angebote/" + anfragenderBenutzer.getId();
	}

	@Override
	protected String getErrorView() {
		return ERROR_VIEW;
	}

	@Override
	protected BearbeiteDaten getBearbeiter() {
		return loeescheEinAngebot;
	}

	@Override
	protected Benutzer getBesitzer() {
		return benutzerService.findByAngebotsIdAndType(angebotsId, type);
	}
}
