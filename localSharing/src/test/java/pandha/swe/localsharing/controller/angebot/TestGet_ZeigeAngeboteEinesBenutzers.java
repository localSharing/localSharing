package pandha.swe.localsharing.controller.angebot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;

import pandha.swe.localsharing.controller.angebot.sites.get.GET_ZeigeAngeboteEinesBenutzers;

public class TestGet_ZeigeAngeboteEinesBenutzers extends TestAngebote {

	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new GET_ZeigeAngeboteEinesBenutzers());

		resetAndInitAllServices();
	}

	@Test
	public void testAktiviereAusleihartikel() throws Exception {

		String url = "/angebote/42";
		String response = "angebote";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response));
	}

}
