package pandha.swe.localsharing.controller.angebot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;

import pandha.swe.localsharing.controller.angebot.sites.get.GET_LoescheEinAngebot;

public class TestLoescheEinAngebot extends TestAngebote {

	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new GET_LoescheEinAngebot());

		resetAndInitAllServices();
	}

	@Test
	public void testLoescheAusleihartikel() throws Exception {

		String url = "/delete/111/ausleihen";
		String response = "redirect:/angebote/42";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

	@Test
	public void testLoescheTauschartikel() throws Exception {

		String url = "/delete/222/tauschen";
		String response = "redirect:/angebote/42";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

	@Test
	public void testLoescheHilfeleistung() throws Exception {

		String url = "/delete/333/helfen";
		String response = "redirect:/angebote/42";

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

}
