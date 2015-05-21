package pandha.swe.localsharing.controller.pattern.sites;

import org.springframework.ui.Model;

import pandha.swe.localsharing.controller.pattern.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeDaten;

public abstract class ZeigeSeite extends LocalSharingControllerTemplate {

	protected Model model;

	protected String bearbeiteAnfrageIntern(Model model) {
		this.model = model;
		return bearbeiteAnfrageLogik();
	}

	@Override
	protected void bearbeiteAnfrageIntern() throws GoToErrorViewException {
		model.addAllAttributes(attribute().ladeDaten());
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
