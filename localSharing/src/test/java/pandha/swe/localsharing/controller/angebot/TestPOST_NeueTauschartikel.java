package pandha.swe.localsharing.controller.angebot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import pandha.swe.localsharing.controller.angebot.sites.post.POST_SpeichereEinenNeuenTauschartikel;

public class TestPOST_NeueTauschartikel extends TestAngebote {

	@Before
	public void setUp() throws Exception {
		initTestsAngebote(new POST_SpeichereEinenNeuenTauschartikel());

		resetAndInitAllServices();
	}

	@Test
	public void testNeuerTauschartikel() throws Exception {

		String url = "/angebotNeu/tauschen";
		String response = "redirect:/angebote/42";

		resetAndInitAllServices();

		mockMvc.perform(
				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

	}
}
