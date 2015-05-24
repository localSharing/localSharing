package pandha.swe.localsharing.controller.angebot.sites.post;

import pandha.swe.localsharing.controller.angebot.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.angebot.sites.EmpfangeDatenMitBild;

public abstract class POST_NeuesAngebot<E, T> extends
		EmpfangeDatenMitBild<E, T> {

	private static final String ERROR_VIEW = "redirect:angebote";

	public POST_NeuesAngebot() {
		super();
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
		return "redirect:/angebote/" + anfragenderBenutzer.getId();
	}

	@Override
	protected String getErrorView() {
		return ERROR_VIEW;
	}

}