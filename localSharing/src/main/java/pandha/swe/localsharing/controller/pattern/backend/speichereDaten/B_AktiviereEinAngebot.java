package pandha.swe.localsharing.controller.pattern.backend.speichereDaten;

import pandha.swe.localsharing.controller.pattern.backend.BearbeiteEinAngebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;

public class B_AktiviereEinAngebot extends BearbeiteEinAngebot {

	@Override
	protected void bearbeiteEinenAusleihartikel(Ausleihartikel ausleihartikel) {
		ausleihartikel.setEnabled(Boolean.TRUE);
		ausleihartikelService.update(ausleihartikel);
	}

	@Override
	protected void bearbeiteEinenTauschartikel(Tauschartikel tauschartikel) {
		tauschartikel.setEnabled(Boolean.TRUE);
		tauschartikelService.update(tauschartikel);
	}

	@Override
	protected void bearbeiteEineHilfeleistung(Hilfeleistung hilfeleistung) {
		hilfeleistung.setEnabled(Boolean.TRUE);
		hilfeleistungService.update(hilfeleistung);
	}

}
