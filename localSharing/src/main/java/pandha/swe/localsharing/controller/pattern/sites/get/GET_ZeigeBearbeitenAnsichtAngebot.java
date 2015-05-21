package pandha.swe.localsharing.controller.pattern.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.pattern.backend.ErlaubeAnfrageVonBesitzer;
import pandha.swe.localsharing.controller.pattern.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeEinAngebotDTO;
import pandha.swe.localsharing.controller.pattern.sites.ZeigeSeite_Benutzer;

@Controller
public class GET_ZeigeBearbeitenAnsichtAngebot extends ZeigeSeite_Benutzer {

	private static final String REQUEST_URL = "/angebotEdit/{id}/{type}";
	private static final String SUCCESS_VIEW = "angebotEdit";
	private static String ERROR_VIEW = "redirect:angebote";

	private Long angebotsId;
	private String type;

	@Autowired
	private LadeEinAngebotDTO ladeEinAngebot;

	@Autowired
	private ErlaubeAnfrageVonBesitzer erlaubeAnfrageVonBesitzer;

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Model model, Principal principal,
			@PathVariable("id") String angebotsId,
			@PathVariable("type") String type) {
		ERROR_VIEW = "redirect:../../angebot/" + angebotsId + "/" + type;

		this.angebotsId = Long.valueOf(angebotsId);
		this.type = type;

		return bearbeiteAnfrageIntern(model, principal);
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
	protected IstAnfrageErlaubt getAnfrageErlaubt() {
		erlaubeAnfrageVonBesitzer.setAnfragendenBenutzer(anfragenderBenutzer);
		erlaubeAnfrageVonBesitzer.setBesitzer(benutzerService
				.findByAngebotsIdAndType(angebotsId, type));
		return erlaubeAnfrageVonBesitzer;
	}

	@Override
	protected LadeDaten attribute() {
		ladeEinAngebot.setId(angebotsId);
		ladeEinAngebot.setType(type);
		return ladeEinAngebot;
	}

	@Override
	protected void setzeWeitereModelAttribute() {
		model.addAttribute("userid", anfragenderBenutzer.getId());
	}

}
