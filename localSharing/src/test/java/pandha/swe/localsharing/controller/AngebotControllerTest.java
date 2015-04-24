package pandha.swe.localsharing.controller;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.model.enums.Geschlecht;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public class AngebotControllerTest {

	@InjectMocks
	AngebotController controller;

	MockMvc mockMvc;

	@Mock
	private AusleihartikelService ausleihartikelService;

	@Mock
	private TauschartikelService tauschartikelService;

	@Mock
	private HilfeleistungService hilfeleistungService;

	@Mock
	private BenutzerService benutzerService;

	@Mock
	private FileService fileService;

	Principal testUser;

	Benutzer benutzer;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setViewResolvers(viewResolver).build();

		testUser = new Principal() {

			@Override
			public String getName() {
				return "user@localsharing.de";
			}
		};

		benutzer = new Benutzer(new Long(42), "12345678", true,
				Geschlecht.MANN, "Peter", "Hans", "Erzbergerstraße", "123",
				76137, "Karlsruhe", "unitTest@localsharing.de", "12345678",
				null);

	}

	@Test
	public void testShowAngebote() throws Exception {
		String url = "/angebote";
		String response = "angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		List<AusleihartikelDTO> ausleih = new ArrayList<AusleihartikelDTO>();
		AusleihartikelDTO ausleihDTO = new AusleihartikelDTO();
		ausleihDTO.setId(new Long(111));
		ausleih.add(0, ausleihDTO);

		when(ausleihartikelService.findAllByBenutzer(benutzer)).thenReturn(
				ausleih);

		List<TauschartikelDTO> tausch = new ArrayList<TauschartikelDTO>();
		TauschartikelDTO tauschDTO = new TauschartikelDTO();
		tauschDTO.setId(new Long(222));
		tausch.add(0, tauschDTO);

		when(tauschartikelService.findAllByBenutzer(benutzer)).thenReturn(
				tausch);

		List<HilfeleistungDTO> hilf = new ArrayList<HilfeleistungDTO>();
		HilfeleistungDTO hilfDTO = new HilfeleistungDTO();
		hilfDTO.setId(new Long(333));
		hilf.add(0, hilfDTO);

		when(hilfeleistungService.findAllByBenutzer(benutzer)).thenReturn(hilf);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("artikelListA"))
				.andExpect(model().attributeExists("artikelListT"))
				.andExpect(model().attributeExists("artikelListH"))
				.andExpect(model().attribute("artikelListA", ausleih))
				.andExpect(model().attribute("artikelListT", tausch))
				.andExpect(model().attribute("artikelListH", hilf));

	}

	@Test
	public void testShowAngebotDefault() throws Exception {

		String url = "/angebot/123/Peter";
		String response = "redirect:angebote";

		resetAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowAngebotAusleihenExists() throws Exception {

		String url = "/angebot/111/ausleihen";
		String response = "angebot";

		resetAllServices();

		Ausleihartikel ausleihAngebot = new Ausleihartikel();
		ausleihAngebot.setAngebotsid(new Long(111));
		ausleihAngebot.setTitel("Ich teste deinen Code");

		when(ausleihartikelService.findById(new Long(111))).thenReturn(
				ausleihAngebot);

		AusleihartikelDTO dto = new AusleihartikelDTO();
		dto.setId(ausleihAngebot.getAngebotsid());
		dto.setTitel(ausleihAngebot.getTitel());

		when(
				ausleihartikelService
						.ausleihartikel_TO_AusleihartikelDTO(ausleihAngebot))
				.thenReturn(dto);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("angebot"))
				.andExpect(model().attribute("angebot", dto));

	}

	@Test
	public void testShowAngebotTauschenExists() throws Exception {

		String url = "/angebot/222/tauschen";
		String response = "angebot";

		resetAllServices();

		Tauschartikel tauschAngebot = new Tauschartikel();
		tauschAngebot.setAngebotsid(new Long(222));
		tauschAngebot.setTitel("Ich teste deinen Code");

		when(tauschartikelService.findById(new Long(222))).thenReturn(
				tauschAngebot);

		TauschartikelDTO dto = new TauschartikelDTO();
		dto.setId(tauschAngebot.getAngebotsid());
		dto.setTitel(tauschAngebot.getTitel());

		when(
				tauschartikelService
						.tauschartikel_TO_TauschartikelDTO(tauschAngebot))
				.thenReturn(dto);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("angebot"))
				.andExpect(model().attribute("angebot", dto));

	}

	@Test
	public void testShowAngebotHilfeLeistungExists() throws Exception {

		String url = "/angebot/333/helfen";
		String response = "angebot";

		resetAllServices();

		Hilfeleistung hilfAngebot = new Hilfeleistung();
		hilfAngebot.setAngebotsid(new Long(333));
		hilfAngebot.setTitel("Ich teste deinen Code");

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfAngebot);

		HilfeleistungDTO dto = new HilfeleistungDTO();
		dto.setId(hilfAngebot.getAngebotsid());
		dto.setTitel(hilfAngebot.getTitel());

		when(
				hilfeleistungService
						.hilfeleistung_TO_HilfeleistungDTO(hilfAngebot))
				.thenReturn(dto);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("angebot"))
				.andExpect(model().attribute("angebot", dto));

	}

	@Test
	public void testShowAngebotAusleihenNotExists() throws Exception {

		String url = "/angebot/112/ausleihen";
		String response = "redirect:angebote";

		resetAllServices();

		when(ausleihartikelService.findById(new Long(112))).thenReturn(null);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowAngebotTauschenNotExists() throws Exception {

		String url = "/angebot/223/tauschen";
		String response = "redirect:angebote";

		resetAllServices();

		when(ausleihartikelService.findById(new Long(223))).thenReturn(null);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowAngebotHilfeleistungNotExists() throws Exception {

		String url = "/angebot/334/helfen";
		String response = "redirect:angebote";

		resetAllServices();

		when(ausleihartikelService.findById(new Long(334))).thenReturn(null);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testEditAngebot() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAusleihartikelAusleihartikelDTOStringPrincipalMultipartFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAusleihartikelTauschartikelDTOStringPrincipalMultipartFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAusleihartikelHilfeleistungDTOStringPrincipalMultipartFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAusleihartikel() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTauschartikelTauschartikelDTOStringModelPrincipal() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTauschartikelStringModelPrincipal() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteHilfeleistungHilfeleistungDTOPrincipal() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteHilfeleistungStringModelPrincipal() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewAusleihen() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewTauschen() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewHelfen() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveNewAusleihen() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveNewTauschen() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveNewHelfen() {
		fail("Not yet implemented");
	}

	private void resetAllServices() {
		reset(benutzerService);
		reset(ausleihartikelService);
		reset(tauschartikelService);
		reset(hilfeleistungService);
	}

}
