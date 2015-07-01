package pandha.swe.localsharing.controller.angebot.sites.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.angebot.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeAlleAktiviertenAngebote;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.angebot.sites.ZeigeSeite;

@Controller
public class GET_ZeigeAlleAktiviertenAngebote extends ZeigeSeite {

	private static final String REQUEST_URL = "/angebote";
	private static final String SUCCESS_VIEW = "angebote";
	private static final String ERROR_VIEW = "redirect:startPage";

	@Autowired
	private LadeAlleAktiviertenAngebote ladeAlleAktiviertenAngebote;

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Model model) {
		return bearbeiteAnfrageIntern(model);
	}

	@Override
	protected IstAnfrageErlaubt getAnfrageErlaubt() {

		return new IstAnfrageErlaubt() {

			@Override
			public Boolean istAnfrageErlaubt() {
				return Boolean.TRUE;
			}
		};

	}

	@Override
	protected String getSuccessView() {
		return SUCCESS_VIEW;
	}

	@Override
	protected String getErrorView() {
		return ERROR_VIEW;
	}

	@Override
	protected LadeDaten attribute() {
		model.addAttribute("titel", "Alle");
		return ladeAlleAktiviertenAngebote;
	}

}
