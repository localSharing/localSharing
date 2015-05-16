package pandha.swe.localsharing.controller.pattern.backend.holeDaten;

import java.util.HashMap;
import java.util.Map;

public abstract class EinAngebot extends LadeDaten {

	protected static final String TYPE_AUSLEIHEN = "ausleihen";
	protected static final String TYPE_TAUSCHEN = "tauschen";
	protected static final String TYPE_HELFEN = "helfen";

	private String type;

	public EinAngebot() {
	}

	@Override
	public Map<String, Object> ladeDaten() {

		Map<String, Object> daten = new HashMap<String, Object>();

		switch (getType()) {
		case TYPE_AUSLEIHEN:
			addAusleihartikel(daten);
			break;
		case TYPE_TAUSCHEN:
			addTauschartikel(daten);
			break;
		case TYPE_HELFEN:
			addHilfeleistung(daten);
			break;
		default:
			daten.put(null, null);
		}
		return daten;
	}

	protected abstract void addAusleihartikel(Map<String, Object> daten);

	protected abstract void addTauschartikel(Map<String, Object> daten);

	protected abstract void addHilfeleistung(Map<String, Object> daten);

	protected String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
