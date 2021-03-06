package pandha.swe.localsharing.controller.angebot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;

import pandha.swe.localsharing.controller.angebot.sites.get.GET_AktiviereEinAngebot;

public class TestGET_AktiviereEinAngebot extends TestAngebote {

	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new GET_AktiviereEinAngebot());
		resetAndInitAllServices();
	}

	@Test
	public void testAktiviereAusleihartikel() throws Exception {

		String url = "/angebot/enable/111/ausleihen";
		String response = "redirect:/angebot/111/ausleihen";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

	@Test
	public void testAktiviereTauschartikel() throws Exception {

		String url = "/angebot/enable/222/tauschen";
		String response = "redirect:/angebot/222/tauschen";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

	@Test
	public void testAktiviereHilfeleistung() throws Exception {

		String url = "/angebot/enable/333/helfen";
		String response = "redirect:/angebot/333/helfen";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

}
