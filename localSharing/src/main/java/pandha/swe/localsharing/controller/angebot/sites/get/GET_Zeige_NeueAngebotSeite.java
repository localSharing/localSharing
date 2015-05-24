package pandha.swe.localsharing.controller.angebot.sites.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.NeuesAngebot;
import pandha.swe.localsharing.controller.angebot.sites.ZeigeSeite;

@Controller
public class GET_Zeige_NeueAngebotSeite extends ZeigeSeite {

	private static final String REQUEST_URL = "/angebotNeu/{type}";
	private static final String SUCCESS_VIEW = "angebotNeu";
	private static final String ERROR_VIEW = "redirect:angebote";

	private String type;

	@Autowired
	private NeuesAngebot neuesAngebot;

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Model model,
			@PathVariable("type") String type) {
		this.type = type;
		return bearbeiteAnfrageIntern(model);
	}

	@Override
	protected LadeDaten attribute() {
		neuesAngebot.setType(type);
		return neuesAngebot;
	}

	@Override
	protected String getSuccessView() {
		return SUCCESS_VIEW;
	}

	@Override
	protected String getErrorView() {
		return ERROR_VIEW;
	}

}
