package pandha.swe.localsharing.controller.angebot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;

import pandha.swe.localsharing.controller.angebot.sites.get.GET_ZeigeAlleDeaktiviertenAngebote;

public class TestGET_DeaktiviereEinAngebot extends TestAngebote {

	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new GET_ZeigeAlleDeaktiviertenAngebote());

		resetAndInitAllServices();
	}

	@Test
	public void testAlleDeaktiviertenAngebote() throws Exception {

		String url = "/angebote/disabled";
		String response = "angebote";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response));
	}

}
