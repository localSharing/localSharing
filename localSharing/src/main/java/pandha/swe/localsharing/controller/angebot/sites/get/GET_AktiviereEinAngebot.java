package pandha.swe.localsharing.controller.angebot.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.angebot.backend.BearbeiteDaten;
import pandha.swe.localsharing.controller.angebot.backend.speichereDaten.B_AktiviereEinAngebot;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;

@Controller
public class GET_AktiviereEinAngebot extends AktivierenDeaktiveren {

	private static final String REQUEST_URL = "/enable/angebot/{id}/{type}";

	@Autowired
	private B_AktiviereEinAngebot bAktiviereEinAngebot;

	@RequestMapping(method = RequestMethod.GET, value = "/enable/angebot/{id}")
	protected String redirectAnfrage(Model model, Principal principal,
			@PathVariable("id") String angebotsId) {

		this.angebotsId = Long.valueOf(angebotsId);

		Angebot angebot = angebotService.findAngebotById(this.angebotsId);

		if (angebot instanceof Ausleihartikel) {
			return "redirect:/angebot/enable/" + angebotsId + "/ausleihen";
		} else if (angebot instanceof Tauschartikel) {
			return "redirect:/angebot/enable/" + angebotsId + "/tauschen";
		} else if (angebot instanceof Hilfeleistung) {
			return "redirect:/angebot/enable/" + angebotsId + "/tauschen";
		}

		return "redirect:/angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Principal principal,
			@PathVariable("id") String angebotsId,
			@PathVariable("type") String type) {
		return bearbeiteAnfrageIntern(principal, angebotsId, type);
	}

	@Override
	protected BearbeiteDaten getBearbeiter() {
		return bAktiviereEinAngebot;
	}

}
