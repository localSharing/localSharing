package pandha.swe.localsharing.controller.angebot.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.angebot.backend.BearbeiteDaten;
import pandha.swe.localsharing.controller.angebot.backend.speichereDaten.B_DeaktiviereEinAngebot;

@Controller
public class GET_DeaktiviereEinAngebot extends AktivierenDeaktiveren {

	private static final String REQUEST_URL = "/disable/{id}/{type}";

	@Autowired
	private B_DeaktiviereEinAngebot bDeaktiviereEinAngebot;

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Principal principal,
			@PathVariable("id") String angebotsId,
			@PathVariable("type") String type) {

		return bearbeiteAnfrageIntern(principal, angebotsId, type);
	}

	@Override
	protected BearbeiteDaten getBearbeiter() {
		return bDeaktiviereEinAngebot;
	}

}
