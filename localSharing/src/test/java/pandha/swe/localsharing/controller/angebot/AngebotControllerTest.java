//package pandha.swe.localsharing.controller.angebot;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.eq;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.internal.verification.VerificationModeFactory;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import pandha.swe.localsharing.controller.angebot.sites.get.GET_AktiviereEinAngebot;
//import pandha.swe.localsharing.model.Ausleihartikel;
//import pandha.swe.localsharing.model.Benutzer;
//import pandha.swe.localsharing.model.Hilfeleistung;
//import pandha.swe.localsharing.model.Tauschartikel;
//import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
//import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
//import pandha.swe.localsharing.model.dto.TauschartikelDTO;
//import pandha.swe.localsharing.model.enums.Geschlecht;
//import pandha.swe.localsharing.service.AusleihartikelService;
//import pandha.swe.localsharing.service.BenutzerService;
//import pandha.swe.localsharing.service.FileService;
//import pandha.swe.localsharing.service.HilfeleistungService;
//import pandha.swe.localsharing.service.TauschartikelService;
//
//public class AngebotControllerTest {
//
//	@InjectMocks
//	GET_AktiviereEinAngebot controller;
//	
//	
//
//	MockMvc mockMvc;
//
//	@Mock
//	private AusleihartikelService ausleihartikelService;
//
//	@Mock
//	private TauschartikelService tauschartikelService;
//
//	@Mock
//	private HilfeleistungService hilfeleistungService;
//
//	@Mock
//	private BenutzerService benutzerService;
//
//	@Mock
//	private FileService fileService;
//
//	Principal testUser;
//
//	Benutzer benutzer;
//
//	List<TauschartikelDTO> tausch;
//	List<HilfeleistungDTO> hilf;
//	List<AusleihartikelDTO> ausleih;
//
//	Ausleihartikel ausleihartikel;
//	AusleihartikelDTO ausleihartikelDTO;
//
//	Tauschartikel tauschartikel;
//	TauschartikelDTO tauschartikelDTO;
//
//	Hilfeleistung hilfeleistung;
//	HilfeleistungDTO hilfeleistungDTO;
//
//	MockMultipartFile file = new MockMultipartFile("testBild",
//			"Tolles Bild".getBytes());
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/templates/");
//		viewResolver.setSuffix(".html");
//
//		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
//				.setViewResolvers(viewResolver).build();
//
//		testUser = new Principal() {
//
//			@Override
//			public String getName() {
//				return "user@localsharing.de";
//			}
//		};
//
//		benutzer = new Benutzer(new Long(42), "12345678", true,
//				Geschlecht.MANN, "Peter", "Hans", "Erzbergerstraße", "123",
//				76137, "Karlsruhe", "unittest@localsharing.de", "12345678",
//				null);
//
//	}
//
//	private void initBenutzerService() {
//		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
//				benutzer);
//
//		when(benutzerService.getUserByPrincipal(testUser)).thenReturn(benutzer);
//	}
//
//	private void initBenutzerServiceGetBenutzerOverAngebotIdAndType(Long id,
//			String type) {
//		when(benutzerService.findByAngebotsIdAndType(id, type)).thenReturn(
//				benutzer);
//	}
//
//	private void initAusleihartikelService() {
//
//		ausleihartikel = new Ausleihartikel();
//		ausleihartikel.setAngebotsid(new Long(111));
//		ausleihartikel.setTitel("Ich teste deinen Code");
//		ausleihartikel.setBenutzer(benutzer);
//
//		when(ausleihartikelService.findById(ausleihartikel.getAngebotsid()))
//				.thenReturn(ausleihartikel);
//
//		ausleihartikelDTO = new AusleihartikelDTO();
//		ausleihartikelDTO.setId(ausleihartikel.getAngebotsid());
//		ausleihartikelDTO.setTitel(ausleihartikel.getTitel());
//		ausleihartikelDTO.setBenutzer(benutzer);
//
//		when(
//				ausleihartikelService
//						.ausleihartikel_TO_AusleihartikelDTO(ausleihartikel))
//				.thenReturn(ausleihartikelDTO);
//
//		when(
//				ausleihartikelService
//						.ausleihartikelDTO_TO_Ausleihartikel(any(AusleihartikelDTO.class)))
//				.thenReturn(ausleihartikel);
//
//		ausleih = new ArrayList<AusleihartikelDTO>();
//		AusleihartikelDTO ausleihDTO = new AusleihartikelDTO();
//		ausleihDTO.setId(new Long(111));
//		ausleih.add(0, ausleihDTO);
//
//		when(ausleihartikelService.findAllByBenutzer(benutzer)).thenReturn(
//				ausleih);
//
//		when(ausleihartikelService.findById(new Long(112))).thenReturn(null);
//
//		when(
//				ausleihartikelService
//						.createAusleihartikel(any(AusleihartikelDTO.class)))
//				.thenReturn(new Long(111));
//
//		initBenutzerServiceGetBenutzerOverAngebotIdAndType(
//				ausleihartikel.getAngebotsid(), "ausleihen");
//
//	}
//
//	private void initTauschartikelService() {
//
//		tauschartikel = new Tauschartikel();
//		tauschartikel.setAngebotsid(new Long(222));
//		tauschartikel.setTitel("Ich teste deinen Code");
//		tauschartikel.setBenutzer(benutzer);
//
//		when(tauschartikelService.findById(new Long(222))).thenReturn(
//				tauschartikel);
//
//		tauschartikelDTO = new TauschartikelDTO();
//		tauschartikelDTO.setId(tauschartikel.getAngebotsid());
//		tauschartikelDTO.setTitel(tauschartikel.getTitel());
//		tauschartikelDTO.setBenutzer(benutzer);
//
//		when(
//				tauschartikelService
//						.tauschartikel_TO_TauschartikelDTO(tauschartikel))
//				.thenReturn(tauschartikelDTO);
//
//		when(
//				tauschartikelService
//						.tauschartikelDTO_TO_Tauschartikel(any(TauschartikelDTO.class)))
//				.thenReturn(tauschartikel);
//
//		tausch = new ArrayList<TauschartikelDTO>();
//		TauschartikelDTO tauschDTO = new TauschartikelDTO();
//		tauschDTO.setId(new Long(222));
//		tausch.add(0, tauschDTO);
//
//		when(tauschartikelService.findAllByBenutzer(benutzer)).thenReturn(
//				tausch);
//
//		when(tauschartikelService.findById(new Long(223))).thenReturn(null);
//
//		when(
//				tauschartikelService
//						.createTauschartikel(any(TauschartikelDTO.class)))
//				.thenReturn(new Long(222));
//
//		initBenutzerServiceGetBenutzerOverAngebotIdAndType(
//				tauschartikel.getAngebotsid(), "tauschen");
//
//	}
//
//	private void initHilfService() {
//		hilfeleistung = new Hilfeleistung();
//		hilfeleistung.setAngebotsid(new Long(333));
//		hilfeleistung.setTitel("Ich teste deinen Code");
//		hilfeleistung.setBenutzer(benutzer);
//
//		when(hilfeleistungService.findById(new Long(333))).thenReturn(
//				hilfeleistung);
//
//		hilfeleistungDTO = new HilfeleistungDTO();
//		hilfeleistungDTO.setId(hilfeleistung.getAngebotsid());
//		hilfeleistungDTO.setTitel(hilfeleistung.getTitel());
//		hilfeleistungDTO.setBenutzer(benutzer);
//
//		when(
//				hilfeleistungService
//						.hilfeleistung_TO_HilfeleistungDTO(hilfeleistung))
//				.thenReturn(hilfeleistungDTO);
//
//		when(
//				hilfeleistungService
//						.hilfeleistungDTO_TO_Hilfeleistung(any(HilfeleistungDTO.class)))
//				.thenReturn(hilfeleistung);
//
//		hilf = new ArrayList<HilfeleistungDTO>();
//		HilfeleistungDTO hilfDTO = new HilfeleistungDTO();
//		hilfDTO.setId(new Long(333));
//		hilf.add(0, hilfDTO);
//
//		when(hilfeleistungService.findAllByBenutzer(benutzer)).thenReturn(hilf);
//
//		when(hilfeleistungService.findById(new Long(334))).thenReturn(null);
//
//		when(
//				hilfeleistungService
//						.createHilfeleistung(any(HilfeleistungDTO.class)))
//				.thenReturn(new Long(333));
//
//		initBenutzerServiceGetBenutzerOverAngebotIdAndType(
//				hilfeleistung.getAngebotsid(), "helfen");
//
//	}
//
//	private void resetAndInitAllServices() {
//		resetAllServices();
//
//		initAllServices();
//	}
//
//	private void initAllServices() {
//		initBenutzerService();
//		initAusleihartikelService();
//		initHilfService();
//		initTauschartikelService();
//	}
//
//	private void resetAllServices() {
//		reset(benutzerService);
//		reset(ausleihartikelService);
//		reset(tauschartikelService);
//		reset(hilfeleistungService);
//	}
//
//	@Test
//	public void testShowAngebote() throws Exception {
//		String url = "/angebote/" + benutzer.getId();
//		String response = "angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("artikelListA"))
//				.andExpect(model().attributeExists("artikelListT"))
//				.andExpect(model().attributeExists("artikelListH"))
//				.andExpect(model().attribute("artikelListA", ausleih))
//				.andExpect(model().attribute("artikelListT", tausch))
//				.andExpect(model().attribute("artikelListH", hilf));
//
//	}
//
//	@Test
//	public void testShowAngebotDefault() throws Exception {
//
//		String url = "/angebot/123/Peter";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//	}
//
//	@Test
//	public void testShowAngebotNull() throws Exception {
//
//		String url = "/angebot/123/''";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//	}
//
//	@Test
//	public void testShowAngebotAusleihenExists() throws Exception {
//
//		String url = "/angebot/111/ausleihen";
//		String response = "angebot";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("angebot"))
//				.andExpect(model().attribute("angebot", ausleihartikelDTO));
//
//	}
//
//	@Test
//	public void testShowAngebotTauschenExists() throws Exception {
//
//		String url = "/angebot/222/tauschen";
//		String response = "angebot";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("angebot"))
//				.andExpect(model().attribute("angebot", tauschartikelDTO));
//
//	}
//
//	@Test
//	public void testShowAngebotHilfeLeistungExists() throws Exception {
//
//		String url = "/angebot/333/helfen";
//		String response = "angebot";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("angebot"))
//				.andExpect(model().attribute("angebot", hilfeleistungDTO));
//
//	}
//
//	@Test
//	public void testShowAngebotAusleihenNotExists() throws Exception {
//
//		String url = "/angebot/112/ausleihen";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//	}
//
//	@Test
//	public void testShowAngebotTauschenNotExists() throws Exception {
//
//		String url = "/angebot/223/tauschen";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//	}
//
//	@Test
//	public void testShowAngebotHilfeleistungNotExists() throws Exception {
//
//		String url = "/angebot/334/helfen";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//	}
//
//	@Test
//	public void testShowEditAngebotDefault() throws Exception {
//
//		String url = "/angebotEdit/123/Peter";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//	}
//
//	@Test
//	public void testShowEditAngebotAusleihangebotExists() throws Exception {
//
//		String url = "/angebotEdit/111/ausleihen";
//		String response = "angebotEdit";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("angebot"))
//				.andExpect(model().attribute("angebot", ausleihartikelDTO));
//
//	}
//
//	@Test
//	public void testShowEditAngebotAusleihangebotNotExists() throws Exception {
//		String url = "/angebotEdit/112/ausleihen";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//	}
//
//	@Test
//	public void testShowEditAngebotTauschangebotExists() throws Exception {
//
//		String url = "/angebotEdit/222/tauschen";
//		String response = "angebotEdit";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("angebot"))
//				.andExpect(model().attribute("angebot", tauschartikelDTO));
//
//	}
//
//	@Test
//	public void testShowEditAngebotTauschangebotNotExists() throws Exception {
//
//		String url = "/angebotEdit/223/tauschen";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//	}
//
//	@Test
//	public void testShowEditAngebotHilfsangebotExists() throws Exception {
//		String url = "/angebotEdit/333/helfen";
//		String response = "angebotEdit";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("angebot"))
//				.andExpect(model().attribute("angebot", hilfeleistungDTO));
//
//	}
//
//	@Test
//	public void testShowEditAngebotHilfsangebotNotExists() throws Exception {
//
//		String url = "/angebotEdit/334/helfen";
//		String response = "redirect:angebote";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//	}
//
//	@Test
//	public void testDeleteAusleihartikel() throws Exception {
//
//		String url = "/delete/111/ausleihen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//		verify(ausleihartikelService, times(1)).delete(ausleihartikel);
//	}
//
//	@Test
//	public void testDeleteTauschartikel() throws Exception {
//
//		String url = "/delete/222/tauschen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//		verify(tauschartikelService, times(1)).delete(tauschartikel);
//
//	}
//
//	@Test
//	public void testDeleteHilfeleistung() throws Exception {
//
//		String url = "/delete/333/helfen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//		verify(hilfeleistungService, times(1)).delete(hilfeleistung);
//
//	}
//
//	@Test
//	public void testNewAusleihen() throws Exception {
//
//		String url = "/angebotNeu/ausleihen";
//		String response = "angebotNeu";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("newAngebot"))
//				.andExpect(model().attribute("ausleihen", "ausleihen"));
//
//	}
//
//	@Test
//	public void testNewTauschen() throws Exception {
//
//		String url = "/angebotNeu/tauschen";
//		String response = "angebotNeu";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("newAngebot"))
//				.andExpect(model().attribute("tauschen", "tauschen"));
//
//	}
//
//	@Test
//	public void testNewHelfen() throws Exception {
//
//		String url = "/angebotNeu/helfen";
//		String response = "angebotNeu";
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(get(url).principal(testUser))
//				.andExpect(status().isOk()).andExpect(view().name(response))
//				.andExpect(model().attributeExists("newAngebot"))
//				.andExpect(model().attribute("helfen", "helfen"));
//	}
//
//	@Test
//	public void testSaveNewAusleihen() throws Exception {
//
//		String url = "/angebotNeu/ausleihen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(ausleihartikelService, times(1)).createAusleihartikel(
//				any(AusleihartikelDTO.class));
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(ausleihartikel), any(MultipartFile.class));
//	}
//
//	@Test
//	public void testSaveNewAusleihenWithEmptyImage() throws Exception {
//
//		String url = "/angebotNeu/ausleihen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", "".getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(ausleihartikelService, times(1)).createAusleihartikel(
//				any(AusleihartikelDTO.class));
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(ausleihartikel), any(MultipartFile.class));
//	}
//
//	@Test
//	public void testSaveNewAusleihenWithImage() throws Exception {
//
//		String url = "/angebotNeu/ausleihen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		MockMultipartFile file = new MockMultipartFile("testBild",
//				"Tolles Bild".getBytes());
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", file.getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(ausleihartikelService, times(1)).createAusleihartikel(
//				any(AusleihartikelDTO.class));
//		verify(fileService, times(1)).save(eq(ausleihartikel),
//				any(MultipartFile.class));
//	}
//
//	@Test
//	public void testSaveNewTauschen() throws Exception {
//		String url = "/angebotNeu/tauschen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(tauschartikelService, times(1)).createTauschartikel(
//				any(TauschartikelDTO.class));
//
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(tauschartikel), any(MultipartFile.class));
//	}
//
//	@Test
//	public void testSaveNewTauschenWithEmptyImage() throws Exception {
//		String url = "/angebotNeu/tauschen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", "".getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(tauschartikelService, times(1)).createTauschartikel(
//				any(TauschartikelDTO.class));
//
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(tauschartikel), any(MultipartFile.class));
//	}
//
//	@Test
//	public void testSaveNewTauschenWithImage() throws Exception {
//		String url = "/angebotNeu/tauschen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", file.getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(tauschartikelService, times(1)).createTauschartikel(
//				any(TauschartikelDTO.class));
//
//		verify(fileService, times(1)).save(eq(tauschartikel),
//				any(MultipartFile.class));
//	}
//
//	@Test
//	public void testSaveNewHelfen() throws Exception {
//
//		String url = "/angebotNeu/helfen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(hilfeleistungService, times(1)).createHilfeleistung(
//				any(HilfeleistungDTO.class));
//
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(hilfeleistung), any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveNewHelfenWithEmptyImage() throws Exception {
//
//		String url = "/angebotNeu/helfen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", "".getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(hilfeleistungService, times(1)).createHilfeleistung(
//				any(HilfeleistungDTO.class));
//
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(hilfeleistung), any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveNewHelfenWithImage() throws Exception {
//
//		String url = "/angebotNeu/helfen";
//		String response = "redirect:../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", file.getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(hilfeleistungService, times(1)).createHilfeleistung(
//				any(HilfeleistungDTO.class));
//
//		verify(fileService, times(1)).save(eq(hilfeleistung),
//				any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditAusleiharikel() throws Exception {
//
//		String url = "/angebotEdit/111/ausleihen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(ausleihartikelService, times(1)).update(ausleihartikel);
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(ausleihartikel), any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditAusleiharikelWithEmptyImage() throws Exception {
//
//		String url = "/angebotEdit/111/ausleihen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		when(ausleihartikelService.findById(new Long(111))).thenReturn(
//				ausleihartikel);
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", "".getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(ausleihartikelService, times(1)).update(ausleihartikel);
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(ausleihartikel), any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditAusleiharikelWithImage() throws Exception {
//
//		String url = "/angebotEdit/111/ausleihen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", file.getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(ausleihartikelService, times(1)).update(ausleihartikel);
//		verify(fileService, times(1)).save(eq(ausleihartikel),
//				any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditTauscharikel() throws Exception {
//
//		String url = "/angebotEdit/222/tauschen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(tauschartikelService, times(1)).update(tauschartikel);
//
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(tauschartikel), any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditTauscharikelWithEmptyImage() throws Exception {
//
//		String url = "/angebotEdit/222/tauschen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", "".getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(tauschartikelService, times(1)).update(tauschartikel);
//
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(tauschartikel), any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditTauscharikelWithImage() throws Exception {
//
//		String url = "/angebotEdit/222/tauschen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", file.getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(tauschartikelService, times(1)).update(tauschartikel);
//
//		verify(fileService, times(1)).save(eq(tauschartikel),
//				any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditHilfsangebot() throws Exception {
//		String url = "/angebotEdit/333/helfen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//		mockMvc.perform(
//				fileUpload(url).contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.param("enabled", "true").principal(testUser))
//				.andExpect(status().isFound()).andExpect(view().name(response));
//
//		verify(hilfeleistungService, times(1)).update(hilfeleistung);
//
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(hilfeleistung), any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditHilfsangebotWithEmptyImage() throws Exception {
//
//		String url = "/angebotEdit/333/helfen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", "".getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(hilfeleistungService, times(1)).update(hilfeleistung);
//
//		verify(fileService, VerificationModeFactory.noMoreInteractions()).save(
//				eq(hilfeleistung), any(MultipartFile.class));
//
//	}
//
//	@Test
//	public void testSaveEditHilfsangebotWithImage() throws Exception {
//
//		String url = "/angebotEdit/333/helfen";
//		String response = "redirect:../../angebote/" + benutzer.getId();
//
//		resetAndInitAllServices();
//
//		mockMvc.perform(
//				fileUpload(url).file("angebotImage", file.getBytes())
//						.contentType(MediaType.MULTIPART_FORM_DATA)
//						.param("titel", "Test Arikel")
//						.param("kategorie", "Test Kategorie")
//						.param("beschreibung", "Test Beschreibung")
//						.param("startDatum", "01.01.2000")
//						.param("endDatum", "01.01.2001").param("dauer", "12")
//						.principal(testUser)).andExpect(status().isFound())
//				.andExpect(view().name(response));
//
//		verify(hilfeleistungService, times(1)).update(hilfeleistung);
//
//		verify(fileService, times(1)).save(eq(hilfeleistung),
//				any(MultipartFile.class));
//
//	}
//
// }
