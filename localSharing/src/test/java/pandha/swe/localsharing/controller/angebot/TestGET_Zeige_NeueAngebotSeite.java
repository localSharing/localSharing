package pandha.swe.localsharing.controller.angebot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;

import pandha.swe.localsharing.controller.angebot.sites.get.GET_Zeige_NeueAngebotSeite;

public class TestGET_Zeige_NeueAngebotSeite extends TestAngebote {

	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new GET_Zeige_NeueAngebotSeite());
		resetAndInitAllServices();
	}

	@Test
	public void testAktiviereAusleihartikel() throws Exception {

		String url = "/angebotNeu/ausleihen";
		String response = "angebotNeu";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("newAngebot"))
				.andExpect(view().name(response));
	}

	@Test
	public void testAktiviereTauschartikel() throws Exception {

		String url = "/angebotNeu/tauschen";
		String response = "angebotNeu";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("newAngebot"))
				.andExpect(view().name(response));
	}

	@Test
	public void testAktiviereHilfeleistung() throws Exception {

		String url = "/angebotNeu/helfen";
		String response = "angebotNeu";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("newAngebot"))
				.andExpect(view().name(response));
	}

}
