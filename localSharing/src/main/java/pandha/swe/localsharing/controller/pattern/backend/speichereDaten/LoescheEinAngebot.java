package pandha.swe.localsharing.controller.pattern.backend.speichereDaten;

import pandha.swe.localsharing.controller.pattern.backend.BearbeiteEinAngebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;

public class LoescheEinAngebot extends BearbeiteEinAngebot {

	@Override
	protected void bearbeiteEinenAusleihartikel(Ausleihartikel ausleihartikel) {
		ausleihartikelService.delete(ausleihartikel);
	}

	@Override
	protected void bearbeiteEinenTauschartikel(Tauschartikel tauschartikel) {
		tauschartikelService.delete(tauschartikel);
	}

	@Override
	protected void bearbeiteEineHilfeleistung(Hilfeleistung hilfeleistung) {
		hilfeleistungService.delete(hilfeleistung);
	}

}
