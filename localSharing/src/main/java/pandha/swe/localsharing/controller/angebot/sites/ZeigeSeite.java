package pandha.swe.localsharing.controller.angebot.sites;

import java.util.Map;

import org.springframework.ui.Model;

import pandha.swe.localsharing.controller.angebot.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeDaten;

public abstract class ZeigeSeite extends LocalSharingControllerTemplate {

	protected Model model;

	protected String bearbeiteAnfrageIntern(Model model) {
		this.model = model;
		return bearbeiteAnfrageLogik();
	}

	@Override
	protected void bearbeiteAnfrageIntern() throws GoToErrorViewException {
		Map<String, Object> daten = attribute().ladeDaten();
		model.addAllAttributes(daten);
		setzeWeitereModelAttribute();

		if (model.asMap().containsValue(null)) {
			throw new GoToErrorViewException();
		}
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

	protected abstract LadeDaten attribute();

	protected void setzeWeitereModelAttribute() throws GoToErrorViewException {
	}

}
