package pandha.swe.localsharing.controller.angebot;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;

import pandha.swe.localsharing.controller.angebot.sites.get.GET_ZeigeAngebot;

public class TestGET_ZeigeAngebote extends TestAngebote {

	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new GET_ZeigeAngebot());

		resetAndInitAllServices();
	}

	@Test
	public void testGetSuccessView() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetErrorView() {
		fail("Not yet implemented");
	}

	@Test
	public void testAttribute() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetzeWeitereModelAttribute() {
		fail("Not yet implemented");
	}

	@Test
	public void testRedirectAnfrage() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowAngebotDefault() throws Exception {

		String url = "/angebot/123/Peter";
		String response = "redirect:/angebote";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowAngebotNull() throws Exception {

		String url = "/angebot/123/''";
		String response = "redirect:/angebote";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowAngebotAusleihenExists() throws Exception {

		String url = "/angebot/111/ausleihen";
		String response = "angebot";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("angebot"))
				.andExpect(model().attribute("angebot", ausleihartikelDTO));

	}

	@Test
	public void testShowAngebotTauschenExists() throws Exception {

		String url = "/angebot/222/tauschen";
		String response = "angebot";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("angebot"))
				.andExpect(model().attribute("angebot", tauschartikelDTO));

	}

	@Test
	public void testShowAngebotHilfeLeistungExists() throws Exception {

		String url = "/angebot/333/helfen";
		String response = "angebot";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("angebot"))
				.andExpect(model().attribute("angebot", hilfeleistungDTO));

	}

	@Test
	public void testShowAngebotAusleihenNotExists() throws Exception {

		String url = "/angebot/112/ausleihen";
		String response = "redirect:/angebote";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowAngebotTauschenNotExists() throws Exception {

		String url = "/angebot/223/tauschen";
		String response = "redirect:/angebote";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowAngebotHilfeleistungNotExists() throws Exception {

		String url = "/angebot/334/helfen";
		String response = "redirect:/angebote";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowEditAngebotDefault() throws Exception {

		String url = "/angebotEdit/123/Peter";

		resetAndInitAllServices();

		mockMvc.perform(get(url).principal(testUser)).andExpect(
				status().isNotFound());
	}
}
