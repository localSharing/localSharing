package pandha.swe.localsharing.controller.pattern.sites.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.pattern.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeAlleAktiviertenAngebote;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeDaten;
import pandha.swe.localsharing.controller.pattern.sites.ZeigeSeite;

@Controller
public class GET_ZeigeAlleAktiviertenAngebote extends ZeigeSeite {

	private final static String REQUEST_URL = "/angebote";
	private final static String SUCCESS_VIEW = "angebote";
	private final static String ERROR_VIEW = "redirect:startPage";

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
