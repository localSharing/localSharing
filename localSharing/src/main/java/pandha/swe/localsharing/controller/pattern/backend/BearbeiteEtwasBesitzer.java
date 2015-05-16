package pandha.swe.localsharing.controller.pattern.backend;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.Benutzer;

public abstract class BearbeiteEtwasBesitzer extends BearbeiteEtwas {

	@Autowired
	private ErlaubeAnfrageVonBesitzer erlaubeAnfrageVonBesitzer;

	public BearbeiteEtwasBesitzer() {
		super();
	}

	@Override
	protected IstAnfrageErlaubt getAnfrageErlaubt() {
		erlaubeAnfrageVonBesitzer.setAnfragendenBenutzer(anfragenderBenutzer);
		erlaubeAnfrageVonBesitzer.setBesitzer(getBesitzer());
		return erlaubeAnfrageVonBesitzer;
	}

	protected abstract Benutzer getBesitzer();

}