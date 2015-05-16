package pandha.swe.localsharing.controller.pattern.sites.get;

import static pandha.swe.localsharing.util.VornameAngebotsseiteWandler.erzeugeVornameFuerAngebotsseite;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.pattern.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeAlleAngeboteEinesBenutzers;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeAlleEigenenAngebote;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeDaten;
import pandha.swe.localsharing.controller.pattern.sites.ZeigeSeite_Benutzer;
import pandha.swe.localsharing.model.Benutzer;

@Controller
public class GET_ZeigeAngeboteEinesBenutzersController extends
		ZeigeSeite_Benutzer {

	private final static String REQUEST_URL = "/angebote/{id}";
	private final static String SUCCESS_VIEW = "angebote";
	private final static String ERROR_VIEW = "redirect:angebote";

	@Autowired
	private LadeAlleEigenenAngebote eigeneAngebote;

	@Autowired
	private LadeAlleAngeboteEinesBenutzers alleAngebote;

	private Long userId;

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Model model, Principal principal,
			@PathVariable("id") String userid) {
		this.userId = Long.valueOf(userid);
		return bearbeiteAnfrageIntern(model, principal);
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
	protected LadeDaten attribute() {
		if (anfragenderBenutzer.getId().equals(userId)) {
			model.addAttribute("titel", "Deine");
			eigeneAngebote.setUser(anfragenderBenutzer);
			return eigeneAngebote;

		} else {
			Benutzer angebotsersteller = benutzerService.findById(Long
					.valueOf(userId));

			model.addAttribute("titel",
					erzeugeVornameFuerAngebotsseite(angebotsersteller
							.getVorname()));

			alleAngebote.setUser(angebotsersteller);

			return alleAngebote;

		}
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
