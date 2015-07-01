package pandha.swe.localsharing.controller.angebot.backend;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BearbeiteEtwasAdmin extends BearbeiteEtwas {

	@Autowired
	private ErlaubeAnfrageVonAdmin erlaubeAnfrageVonAdmin;

	public BearbeiteEtwasAdmin() {
		super();
	}

	@Override
	protected IstAnfrageErlaubt getAnfrageErlaubt() {
		erlaubeAnfrageVonAdmin.setAnfragendenBenutzer(anfragenderBenutzer);
		return erlaubeAnfrageVonAdmin;
	}

}