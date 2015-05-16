package pandha.swe.localsharing.controller.pattern.sites;

import pandha.swe.localsharing.controller.pattern.backend.IstAnfrageErlaubt;

public abstract class LocalSharingControllerTemplate {

	protected abstract void bearbeiteAnfrageIntern()
			throws GoToErrorViewException;

	protected abstract IstAnfrageErlaubt getAnfrageErlaubt();

	protected abstract String getSuccessView();

	protected abstract String getErrorView();

	protected String bearbeiteAnfrageLogik() {

		try {
			istAnfrageErlaubt();
			bearbeiteAnfrageIntern();

		} catch (GoToErrorViewException e) {
			return getErrorView();
		}

		return getSuccessView();
	}

	private void istAnfrageErlaubt() throws GoToErrorViewException {
		if (!getAnfrageErlaubt().istAnfrageErlaubt()) {
			throw new GoToErrorViewException();
		}
	}

}
