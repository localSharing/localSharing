package pandha.swe.localsharing.controller.angebot.sites;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.controller.angebot.backend.Speicherer;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.service.BenutzerService;

public abstract class EmpfangeDaten<T, E> extends
		LocalSharingControllerTemplate {

	@Autowired
	protected BenutzerService benutzerService;

	protected Benutzer anfragenderBenutzer;

	protected String bearbeiteAnfrageIntern(Principal principal) {
		this.anfragenderBenutzer = benutzerService
				.getUserByPrincipal(principal);
		return bearbeiteAnfrageLogik();
	}

	@Override
	protected void bearbeiteAnfrageIntern() throws GoToErrorViewException {
		getSpeicherer(wandleUm(getArtikel())).speicher();
	}

	protected abstract T getArtikel();

	protected abstract E wandleUm(T artikel);

	protected abstract Speicherer getSpeicherer(E e);

}
