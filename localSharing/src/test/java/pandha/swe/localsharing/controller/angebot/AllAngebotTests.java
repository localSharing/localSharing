package pandha.swe.localsharing.controller.angebot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGET_AktiviereEinAngebot.class,
		TestGET_DeaktiviereEinAngebot.class,
		TestGET_Zeige_NeueAngebotSeite.class,
		TestGet_ZeigeAlleAktiviertenAngebote.class,
		TestGET_ZeigeAlleDeaktiviertenAngebot.class,
		TestGET_ZeigeAngebote.class, TestGet_ZeigeAngeboteEinesBenutzers.class,
		TestGET_ZeigeBearbeitenAnsichtAngebot.class,
		TestLoescheEinAngebot.class, TestPOST_NeueAusleihartikel.class,
		TestPOST_NeueHilfeLeistung.class, TestPOST_NeueTauschartikel.class,
		TestPOST_SpeichereEineHilfeLeistung.class,
		TestPOST_SpeichereEinenAusleihartikel.class,
		TestPOST_SpeichereEinenTauschartikel.class })
public class AllAngebotTests {

}
