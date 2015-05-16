package pandha.swe.localsharing.controller.pattern.sites.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeDaten;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.NeuesAngebot;
import pandha.swe.localsharing.controller.pattern.sites.ZeigeSeite;

@Controller
public class GET_Zeige_NeueAngebotSeite extends ZeigeSeite {

	private final static String REQUEST_URL = "/angebotNeu/{type}";
	private final static String SUCCESS_VIEW = "angebotNeu";
	private final static String ERROR_VIEW = "redirect:angebote";

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
