package pandha.swe.localsharing.util;

import org.junit.Assert;
import org.junit.Test;

public class TestVornameAngebotsseiteWandler {

	@Test
	public void testErzeugeVornameFuerAngebotsseite() {

		String vornameNotNeedS = "Johannes";
		String vornameNeedS = "Corinna";

		Assert.assertEquals(vornameNotNeedS, VornamenWandler
				.erzeugeVornameFuerAngebotsseite(vornameNotNeedS));

		Assert.assertEquals(vornameNeedS + "s", VornamenWandler
				.erzeugeVornameFuerAngebotsseite(vornameNeedS));

	}

}
