package pandha.swe.localsharing.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
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
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
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
	public void testShowAngebotNull() throws Exception {

		String url = "/angebot/123/''";
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
	public void testShowEditAngebotDefault() throws Exception {

		String url = "/angebotEdit/123/Peter";
		String response = "redirect:angebote";

		resetAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

	@Test
	public void testShowEditAngebotAusleihangebotExists() throws Exception {

		String url = "/angebotEdit/111/ausleihen";
		String response = "angebotEdit";

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
	public void testShowEditAngebotAusleihangebotNotExists() throws Exception {
		String url = "/angebotEdit/112/ausleihen";
		String response = "redirect:angebote";

		resetAllServices();

		when(ausleihartikelService.findById(new Long(112))).thenReturn(null);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

	}

	@Test
	public void testShowEditAngebotTauschangebotExists() throws Exception {

		String url = "/angebotEdit/222/tauschen";
		String response = "angebotEdit";

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
	public void testShowEditAngebotTauschangebotNotExists() throws Exception {

		String url = "/angebotEdit/223/tauschen";
		String response = "redirect:angebote";

		resetAllServices();

		when(ausleihartikelService.findById(new Long(223))).thenReturn(null);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

	@Test
	public void testShowEditAngebotHilfsangebotExists() throws Exception {
		String url = "/angebotEdit/333/helfen";
		String response = "angebotEdit";

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
	public void testShowEditAngebotHilfsangebotNotExists() throws Exception {

		String url = "/angebotEdit/334/helfen";
		String response = "redirect:angebote";

		resetAllServices();

		when(ausleihartikelService.findById(new Long(334))).thenReturn(null);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));
	}

	@Test
	public void testDeleteAusleihartikel() throws Exception {

		String url = "/delete/111/ausleihen";
		String response = "redirect:../../angebote";

		resetAllServices();

		Ausleihartikel ausleihAngebot = new Ausleihartikel();
		ausleihAngebot.setAngebotsid(new Long(111));
		ausleihAngebot.setTitel("Ich teste deinen Code");

		when(ausleihartikelService.findById(new Long(111))).thenReturn(
				ausleihAngebot);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

		verify(ausleihartikelService, times(1)).delete(ausleihAngebot);
	}

	@Test
	public void testDeleteTauschartikel() throws Exception {

		String url = "/delete/222/tauschen";
		String response = "redirect:../../angebote";

		resetAllServices();

		Tauschartikel tauschAngebot = new Tauschartikel();
		tauschAngebot.setAngebotsid(new Long(222));
		tauschAngebot.setTitel("Ich teste deinen Code");

		when(tauschartikelService.findById(new Long(222))).thenReturn(
				tauschAngebot);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

		verify(tauschartikelService, times(1)).delete(tauschAngebot);

	}

	@Test
	public void testDeleteHilfeleistung() throws Exception {

		String url = "/delete/333/helfen";
		String response = "redirect:../../angebote";

		resetAllServices();

		Hilfeleistung helfen = new Hilfeleistung();
		helfen.setAngebotsid(new Long(333));
		helfen.setTitel("Ich teste deinen Code");

		when(hilfeleistungService.findById(new Long(333))).thenReturn(helfen);

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isFound()).andExpect(view().name(response));

		verify(hilfeleistungService, times(1)).delete(helfen);

	}

	@Test
	public void testNewAusleihen() throws Exception {

		String url = "/angebotNeu/ausleihen";
		String response = "angebotNeu";

		resetAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("newAngebot"))
				.andExpect(model().attribute("ausleihen", "ausleihen"));

	}

	@Test
	public void testNewTauschen() throws Exception {

		String url = "/angebotNeu/tauschen";
		String response = "angebotNeu";

		resetAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("newAngebot"))
				.andExpect(model().attribute("tauschen", "tauschen"));

	}

	@Test
	public void testNewHelfen() throws Exception {

		String url = "/angebotNeu/helfen";
		String response = "angebotNeu";

		resetAllServices();

		mockMvc.perform(get(url).principal(testUser))
				.andExpect(status().isOk()).andExpect(view().name(response))
				.andExpect(model().attributeExists("newAngebot"))
				.andExpect(model().attribute("helfen", "helfen"));
	}

	@Test
	public void testSaveNewAusleihen() throws Exception {

		String url = "/angebotNeu/ausleihen";
		String response = "redirect:../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				ausleihartikelService
						.createAusleihartikel(any(AusleihartikelDTO.class)))
				.thenReturn(new Long(111));

		Ausleihartikel ausleihAngebot = new Ausleihartikel();
		ausleihAngebot.setAngebotsid(new Long(111));
		ausleihAngebot.setTitel("Ich teste deinen Code");

		when(ausleihartikelService.findById(new Long(111))).thenReturn(
				ausleihAngebot);

		mockMvc.perform(
				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(ausleihartikelService, times(1)).createAusleihartikel(
				any(AusleihartikelDTO.class));
		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(ausleihAngebot), any(MultipartFile.class));
	}

	@Test
	public void testSaveNewAusleihenWithEmptyImage() throws Exception {

		String url = "/angebotNeu/ausleihen";
		String response = "redirect:../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				ausleihartikelService
						.createAusleihartikel(any(AusleihartikelDTO.class)))
				.thenReturn(new Long(111));

		Ausleihartikel ausleihAngebot = new Ausleihartikel();
		ausleihAngebot.setAngebotsid(new Long(111));
		ausleihAngebot.setTitel("Ich teste deinen Code");

		when(ausleihartikelService.findById(new Long(111))).thenReturn(
				ausleihAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", "".getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(ausleihartikelService, times(1)).createAusleihartikel(
				any(AusleihartikelDTO.class));
		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(ausleihAngebot), any(MultipartFile.class));
	}

	@Test
	public void testSaveNewAusleihenWithImage() throws Exception {

		String url = "/angebotNeu/ausleihen";
		String response = "redirect:../angebote";

		resetAllServices();

		MockMultipartFile file = new MockMultipartFile("testBild",
				"Tolles Bild".getBytes());

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				ausleihartikelService
						.createAusleihartikel(any(AusleihartikelDTO.class)))
				.thenReturn(new Long(111));

		Ausleihartikel ausleihAngebot = new Ausleihartikel();
		ausleihAngebot.setAngebotsid(new Long(111));
		ausleihAngebot.setTitel("Ich teste deinen Code");

		when(ausleihartikelService.findById(new Long(111))).thenReturn(
				ausleihAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", file.getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(ausleihartikelService, times(1)).createAusleihartikel(
				any(AusleihartikelDTO.class));
		verify(fileService, times(1)).save(eq(ausleihAngebot),
				any(MultipartFile.class));
	}

	@Test
	public void testSaveNewTauschen() throws Exception {
		String url = "/angebotNeu/tauschen";
		String response = "redirect:../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				tauschartikelService
						.createTauschartikel(any(TauschartikelDTO.class)))
				.thenReturn(new Long(222));

		Tauschartikel tauschAngebot = new Tauschartikel();
		tauschAngebot.setAngebotsid(new Long(222));
		tauschAngebot.setTitel("Ich teste deinen Code");

		when(tauschartikelService.findById(new Long(111))).thenReturn(
				tauschAngebot);

		mockMvc.perform(
				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(tauschartikelService, times(1)).createTauschartikel(
				any(TauschartikelDTO.class));

		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(tauschAngebot), any(MultipartFile.class));
	}

	@Test
	public void testSaveNewTauschenWithEmptyImage() throws Exception {
		String url = "/angebotNeu/tauschen";
		String response = "redirect:../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				tauschartikelService
						.createTauschartikel(any(TauschartikelDTO.class)))
				.thenReturn(new Long(222));

		Tauschartikel tauschAngebot = new Tauschartikel();
		tauschAngebot.setAngebotsid(new Long(222));
		tauschAngebot.setTitel("Ich teste deinen Code");

		when(tauschartikelService.findById(new Long(111))).thenReturn(
				tauschAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", "".getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(tauschartikelService, times(1)).createTauschartikel(
				any(TauschartikelDTO.class));

		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(tauschAngebot), any(MultipartFile.class));
	}

	@Test
	public void testSaveNewTauschenWithImage() throws Exception {
		String url = "/angebotNeu/tauschen";
		String response = "redirect:../angebote";

		resetAllServices();

		MockMultipartFile file = new MockMultipartFile("testBild",
				"Tolles Bild".getBytes());

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				tauschartikelService
						.createTauschartikel(any(TauschartikelDTO.class)))
				.thenReturn(new Long(222));

		Tauschartikel tauschAngebot = new Tauschartikel();
		tauschAngebot.setAngebotsid(new Long(222));
		tauschAngebot.setTitel("Ich teste deinen Code");

		when(tauschartikelService.findById(new Long(222))).thenReturn(
				tauschAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", file.getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(tauschartikelService, times(1)).createTauschartikel(
				any(TauschartikelDTO.class));

		verify(fileService, times(1)).save(eq(tauschAngebot),
				any(MultipartFile.class));
	}

	@Test
	public void testSaveNewHelfen() throws Exception {

		String url = "/angebotNeu/helfen";
		String response = "redirect:../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				hilfeleistungService
						.createHilfeleistung(any(HilfeleistungDTO.class)))
				.thenReturn(new Long(333));

		Hilfeleistung hilfAngebot = new Hilfeleistung();
		hilfAngebot.setAngebotsid(new Long(333));
		hilfAngebot.setTitel("Ich teste deinen Code");

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfAngebot);

		mockMvc.perform(
				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(hilfeleistungService, times(1)).createHilfeleistung(
				any(HilfeleistungDTO.class));

		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(hilfAngebot), any(MultipartFile.class));

	}

	@Test
	public void testSaveNewHelfenWithEmptyImage() throws Exception {

		String url = "/angebotNeu/helfen";
		String response = "redirect:../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				hilfeleistungService
						.createHilfeleistung(any(HilfeleistungDTO.class)))
				.thenReturn(new Long(333));

		Hilfeleistung hilfAngebot = new Hilfeleistung();
		hilfAngebot.setAngebotsid(new Long(333));
		hilfAngebot.setTitel("Ich teste deinen Code");

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", "".getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(hilfeleistungService, times(1)).createHilfeleistung(
				any(HilfeleistungDTO.class));

		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(hilfAngebot), any(MultipartFile.class));

	}

	@Test
	public void testSaveNewHelfenWithImage() throws Exception {

		String url = "/angebotNeu/helfen";
		String response = "redirect:../angebote";

		resetAllServices();

		MockMultipartFile file = new MockMultipartFile("testBild",
				"Tolles Bild".getBytes());

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				hilfeleistungService
						.createHilfeleistung(any(HilfeleistungDTO.class)))
				.thenReturn(new Long(333));

		Hilfeleistung hilfAngebot = new Hilfeleistung();
		hilfAngebot.setAngebotsid(new Long(333));
		hilfAngebot.setTitel("Ich teste deinen Code");

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", file.getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(hilfeleistungService, times(1)).createHilfeleistung(
				any(HilfeleistungDTO.class));

		verify(fileService, times(1)).save(eq(hilfAngebot),
				any(MultipartFile.class));

	}

	@Test
	public void testSaveEditAusleiharikel() throws Exception {

		String url = "/angebotEdit/111/ausleihen";
		String response = "redirect:../../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		Ausleihartikel ausleihAngebot = new Ausleihartikel();
		ausleihAngebot.setAngebotsid(new Long(111));
		ausleihAngebot.setTitel("Ich teste deinen Code");

		when(
				ausleihartikelService
						.ausleihartikelDTO_TO_Ausleihartikel(any(AusleihartikelDTO.class)))
				.thenReturn(ausleihAngebot);

		when(ausleihartikelService.findById(new Long(111))).thenReturn(
				ausleihAngebot);

		mockMvc.perform(
				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(ausleihartikelService, times(1)).update(ausleihAngebot);
		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(ausleihAngebot), any(MultipartFile.class));

	}

	@Test
	public void testSaveEditAusleiharikelWithEmptyImage() throws Exception {

		String url = "/angebotEdit/111/ausleihen";
		String response = "redirect:../../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		Ausleihartikel ausleihAngebot = new Ausleihartikel();
		ausleihAngebot.setAngebotsid(new Long(111));
		ausleihAngebot.setTitel("Ich teste deinen Code");

		when(
				ausleihartikelService
						.ausleihartikelDTO_TO_Ausleihartikel(any(AusleihartikelDTO.class)))
				.thenReturn(ausleihAngebot);

		when(ausleihartikelService.findById(new Long(111))).thenReturn(
				ausleihAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", "".getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(ausleihartikelService, times(1)).update(ausleihAngebot);
		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(ausleihAngebot), any(MultipartFile.class));

	}

	@Test
	public void testSaveEditAusleiharikelWithImage() throws Exception {

		String url = "/angebotEdit/111/ausleihen";
		String response = "redirect:../../angebote";

		resetAllServices();

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		Ausleihartikel ausleihAngebot = new Ausleihartikel();
		ausleihAngebot.setAngebotsid(new Long(111));
		ausleihAngebot.setTitel("Ich teste deinen Code");

		when(
				ausleihartikelService
						.ausleihartikelDTO_TO_Ausleihartikel(any(AusleihartikelDTO.class)))
				.thenReturn(ausleihAngebot);

		when(ausleihartikelService.findById(new Long(111))).thenReturn(
				ausleihAngebot);

		MockMultipartFile file = new MockMultipartFile("testBild",
				"Tolles Bild".getBytes());

		mockMvc.perform(
				fileUpload(url).file("angebotImage", file.getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(ausleihartikelService, times(1)).update(ausleihAngebot);
		verify(fileService, times(1)).save(eq(ausleihAngebot),
				any(MultipartFile.class));

	}

	@Test
	public void testSaveEditTauscharikel() throws Exception {

		String url = "/angebotEdit/222/tauschen";
		String response = "redirect:../../angebote";

		resetAllServices();

		Tauschartikel tauschAngebot = new Tauschartikel();
		tauschAngebot.setAngebotsid(new Long(222));
		tauschAngebot.setTitel("Ich teste deinen Code");

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				tauschartikelService
						.tauschartikelDTO_TO_Tauschartikel(any(TauschartikelDTO.class)))
				.thenReturn(tauschAngebot);

		when(tauschartikelService.findById(new Long(222))).thenReturn(
				tauschAngebot);

		mockMvc.perform(
				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(tauschartikelService, times(1)).update(tauschAngebot);

		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(tauschAngebot), any(MultipartFile.class));

	}

	@Test
	public void testSaveEditTauscharikelWithEmptyImage() throws Exception {

		String url = "/angebotEdit/222/tauschen";
		String response = "redirect:../../angebote";

		resetAllServices();

		Tauschartikel tauschAngebot = new Tauschartikel();
		tauschAngebot.setAngebotsid(new Long(222));
		tauschAngebot.setTitel("Ich teste deinen Code");

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				tauschartikelService
						.tauschartikelDTO_TO_Tauschartikel(any(TauschartikelDTO.class)))
				.thenReturn(tauschAngebot);

		when(tauschartikelService.findById(new Long(222))).thenReturn(
				tauschAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", "".getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(tauschartikelService, times(1)).update(tauschAngebot);

		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(tauschAngebot), any(MultipartFile.class));

	}

	@Test
	public void testSaveEditTauscharikelWithImage() throws Exception {

		String url = "/angebotEdit/222/tauschen";
		String response = "redirect:../../angebote";

		resetAllServices();

		Tauschartikel tauschAngebot = new Tauschartikel();
		tauschAngebot.setAngebotsid(new Long(222));
		tauschAngebot.setTitel("Ich teste deinen Code");

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				tauschartikelService
						.tauschartikelDTO_TO_Tauschartikel(any(TauschartikelDTO.class)))
				.thenReturn(tauschAngebot);

		when(tauschartikelService.findById(new Long(222))).thenReturn(
				tauschAngebot);

		MockMultipartFile file = new MockMultipartFile("testBild",
				"Tolles Bild".getBytes());

		mockMvc.perform(
				fileUpload(url).file("angebotImage", file.getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(tauschartikelService, times(1)).update(tauschAngebot);

		verify(fileService, times(1)).save(eq(tauschAngebot),
				any(MultipartFile.class));

	}

	@Test
	public void testSaveEditHilfsangebot() throws Exception {
		String url = "/angebotEdit/333/helfen";
		String response = "redirect:../../angebote";

		resetAllServices();
		Hilfeleistung hilfAngebot = new Hilfeleistung();
		hilfAngebot.setAngebotsid(new Long(333));
		hilfAngebot.setTitel("Ich teste deinen Code");

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				hilfeleistungService
						.hilfeleistungDTO_TO_Hilfeleistung(any(HilfeleistungDTO.class)))
				.thenReturn(hilfAngebot);

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfAngebot);

		mockMvc.perform(
				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(hilfeleistungService, times(1)).update(hilfAngebot);

		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(hilfAngebot), any(MultipartFile.class));

	}

	@Test
	public void testSaveEditHilfsangebotWithEmptyImage() throws Exception {

		String url = "/angebotEdit/333/helfen";
		String response = "redirect:../../angebote";

		resetAllServices();
		Hilfeleistung hilfAngebot = new Hilfeleistung();
		hilfAngebot.setAngebotsid(new Long(333));
		hilfAngebot.setTitel("Ich teste deinen Code");

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				hilfeleistungService
						.hilfeleistungDTO_TO_Hilfeleistung(any(HilfeleistungDTO.class)))
				.thenReturn(hilfAngebot);

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfAngebot);

		mockMvc.perform(
				fileUpload(url).file("angebotImage", "".getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(hilfeleistungService, times(1)).update(hilfAngebot);

		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
				eq(hilfAngebot), any(MultipartFile.class));

	}

	@Test
	public void testSaveEditHilfsangebotWithImage() throws Exception {

		String url = "/angebotEdit/333/helfen";
		String response = "redirect:../../angebote";

		resetAllServices();
		Hilfeleistung hilfAngebot = new Hilfeleistung();
		hilfAngebot.setAngebotsid(new Long(333));
		hilfAngebot.setTitel("Ich teste deinen Code");

		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(
				hilfeleistungService
						.hilfeleistungDTO_TO_Hilfeleistung(any(HilfeleistungDTO.class)))
				.thenReturn(hilfAngebot);

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfAngebot);

		MockMultipartFile file = new MockMultipartFile("testBild",
				"Tolles Bild".getBytes());

		mockMvc.perform(
				fileUpload(url).file("angebotImage", file.getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("titel", "Test Arikel")
						.param("kategorie", "Test Kategorie")
						.param("beschreibung", "Test Beschreibung")
						.param("startDatum", "01.01.2000")
						.param("endDatum", "01.01.2001").param("dauer", "12")
						.principal(testUser)).andExpect(status().isFound())
				.andExpect(view().name(response));

		verify(hilfeleistungService, times(1)).update(hilfAngebot);

		verify(fileService, times(1)).save(eq(hilfAngebot),
				any(MultipartFile.class));

	}

	private void resetAllServices() {
		reset(benutzerService);
		reset(ausleihartikelService);
		reset(tauschartikelService);
		reset(hilfeleistungService);
	}

}
