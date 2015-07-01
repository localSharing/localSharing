package pandha.swe.localsharing.controller.angebot.backend;

public abstract class Speicherer {

	public Long speicher() {
		return speichereIntern();
	}

	protected abstract Long speichereIntern();

}
