package pandha.swe.localsharing.controller.pattern.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.pattern.backend.BearbeiteDaten;
import pandha.swe.localsharing.controller.pattern.backend.BearbeiteEtwasBesitzer;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeEinAngebot;
import pandha.swe.localsharing.controller.pattern.backend.speichereDaten.LoescheEinAngebot;
import pandha.swe.localsharing.model.Benutzer;

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
