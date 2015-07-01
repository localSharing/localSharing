package pandha.swe.localsharing.controller.angebot.backend.speichereDaten;

import pandha.swe.localsharing.controller.angebot.backend.BearbeiteEinAngebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;

public class B_DeaktiviereEinAngebot extends BearbeiteEinAngebot {


	@Override
	protected void bearbeiteEinenAusleihartikel(Ausleihartikel ausleihartikel) {
		ausleihartikel.setEnabled(Boolean.FALSE);
		ausleihartikelService.update(ausleihartikel);
	}

	@Override
	protected void bearbeiteEinenTauschartikel(Tauschartikel tauschartikel) {
		tauschartikel.setEnabled(Boolean.FALSE);
		tauschartikelService.update(tauschartikel);
	}

	@Override
	protected void bearbeiteEineHilfeleistung(Hilfeleistung hilfeleistung) {
		hilfeleistung.setEnabled(Boolean.FALSE);
		hilfeleistungService.update(hilfeleistung);
	}

}
