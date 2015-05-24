package pandha.swe.localsharing.controller.angebot.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.angebot.backend.ErlaubeAnfrageVonAdmin;
import pandha.swe.localsharing.controller.angebot.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeAlleDeaktiviertenAngebote;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.angebot.sites.ZeigeSeite_Benutzer;

@Controller
public class GET_ZeigeAlleDeaktiviertenAngebote extends ZeigeSeite_Benutzer {

	private static final String REQUEST_URL = "/angebote/disabled";
	private static final String SUCCESS_VIEW = "angebote";
	private static final String ERROR_VIEW = "redirect:../angebote";

	@Autowired
	private LadeAlleDeaktiviertenAngebote ladeAlleDeaktiviertenAngebote;

	@Autowired
	private ErlaubeAnfrageVonAdmin erlaubeAnfrageVonAdmin;

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Model model, Principal principal) {
		return bearbeiteAnfrageIntern(model, principal);
	}

	@Override
	protected LadeDaten attribute() {
		return ladeAlleDeaktiviertenAngebote;
	}

	@Override
	protected IstAnfrageErlaubt getAnfrageErlaubt() {
		erlaubeAnfrageVonAdmin.setAnfragendenBenutzer(anfragenderBenutzer);
		return erlaubeAnfrageVonAdmin;
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
