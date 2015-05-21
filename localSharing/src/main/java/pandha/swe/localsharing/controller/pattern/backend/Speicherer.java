package pandha.swe.localsharing.controller.pattern.backend;

public abstract class Speicherer {

	public Long speicher() {
		return speichereIntern();
	}

	protected abstract Long speichereIntern();

}
