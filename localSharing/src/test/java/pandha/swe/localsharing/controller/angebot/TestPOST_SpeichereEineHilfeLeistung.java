package pandha.swe.localsharing.controller.angebot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import pandha.swe.localsharing.controller.angebot.sites.post.POST_SpeichereEineHilfeLeistung;

public class TestPOST_SpeichereEineHilfeLeistung extends TestAngebote {
	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new POST_SpeichereEineHilfeLeistung());

		resetAndInitAllServices();
	}

	@Test
	public void testNeueHilfeleistung() throws Exception {

		String url = "/angebotEdit/333/helfen";
		String response = "redirect:../../angebote/42";

		resetAndInitAllServices();

		mockMvc.perform(
				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.param("enabled", "true").principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}
}
