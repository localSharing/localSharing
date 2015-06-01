package pandha.swe.localsharing.controller.angebot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;

import pandha.swe.localsharing.controller.angebot.sites.get.GET_ZeigeBearbeitenAnsichtAngebot;

public class TestGET_ZeigeBearbeitenAnsichtAngebot extends TestAngebote {

	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new GET_ZeigeBearbeitenAnsichtAngebot());

		resetAndInitAllServices();
	}

	@Test
	public void testAktiviereAusleihartikel() throws Exception {

		String url = "/angebotEdit/111/ausleihen";
		String response = "angebotEdit";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response));
	}

	@Test
	public void testAktiviereTauschartikel() throws Exception {

		String url = "/angebotEdit/222/tauschen";
		String response = "angebotEdit";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response));
	}

	@Test
	public void testAktiviereHilfeleistung() throws Exception {

		String url = "/angebotEdit/333/helfen";
		String response = "angebotEdit";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response));
	}

}
