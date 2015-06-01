package pandha.swe.localsharing.controller.angebot;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.controller.angebot.backend.CreateArtikelInService;
import pandha.swe.localsharing.controller.angebot.backend.DtoToModelUmwander;
import pandha.swe.localsharing.controller.angebot.backend.ErlaubeAnfrageVonAdmin;
import pandha.swe.localsharing.controller.angebot.backend.ErlaubeAnfrageVonBesitzer;
import pandha.swe.localsharing.controller.angebot.backend.UpdateArtikelInService;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeAlleAktiviertenAngebote;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeAlleAngeboteEinesBenutzers;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeAlleDeaktiviertenAngebote;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeAlleEigenenAngebote;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeEinAngebot;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeEinAngebotDTO;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.NeuesAngebot;
import pandha.swe.localsharing.controller.angebot.backend.speichereDaten.B_AktiviereEinAngebot;
import pandha.swe.localsharing.controller.angebot.backend.speichereDaten.B_DeaktiviereEinAngebot;
import pandha.swe.localsharing.controller.angebot.backend.speichereDaten.LoescheEinAngebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.model.enums.Geschlecht;
import pandha.swe.localsharing.service.AngebotService;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.BewertungService;
import pandha.swe.localsharing.service.FileService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public class TestAngebote {

	protected MockMvc mockMvc;

	@Mock
	protected AusleihartikelService ausleihartikelService;

	@Mock
	protected TauschartikelService tauschartikelService;

	@Mock
	protected HilfeleistungService hilfeleistungService;

	@Mock
	protected BenutzerService benutzerService;

	@Mock
	protected FileService fileService;

	@Mock
	protected AngebotService angebotService;

	@Mock
	protected LadeEinAngebotDTO ladeEinAngebotDTO;

	@Mock
	protected LadeEinAngebot ladeEinAngebot;

	@Mock
	protected BewertungService bewertungService;

	@Mock
	protected ErlaubeAnfrageVonAdmin erlaubeAnfrageVonAdmin;

	@Mock
	protected ErlaubeAnfrageVonBesitzer erlaubeAnfrageVonBesitzer;

	@Mock
	protected LoescheEinAngebot loeescheEinAngebot;

	@Mock
	protected B_AktiviereEinAngebot bAktiviereEinAngebot;

	@Mock
	protected B_DeaktiviereEinAngebot b_DeaktiviereEinAngebot;

	@Mock
	private LadeAlleEigenenAngebote eigeneAngebote;

	@Mock
	private LadeAlleAngeboteEinesBenutzers alleAngebote;

	@Mock
	protected NeuesAngebot neuesAngebot;

	@Mock
	protected DtoToModelUmwander<HilfeleistungDTO, Hilfeleistung> umwandlerHilfeleistung;

	@Mock
	protected DtoToModelUmwander<AusleihartikelDTO, Ausleihartikel> umwandlerAusleihartikel;

	@Mock
	protected DtoToModelUmwander<TauschartikelDTO, Hilfeleistung> umwandlerTauschartikel;

	@Mock
	protected CreateArtikelInService<HilfeleistungDTO> createArtikelInServiceHilfeleistung;
	@Mock
	protected CreateArtikelInService<TauschartikelDTO> createArtikelInServiceTauschartikel;
	@Mock
	protected CreateArtikelInService<AusleihartikelDTO> createArtikelInServiceAusleihartikel;

	@Mock
	protected UpdateArtikelInService<Hilfeleistung> updateArtikelInServiceHilfeleistung;

	@Mock
	protected UpdateArtikelInService<Ausleihartikel> updateArtikelInServiceAusleihartikel;

	@Mock
	protected UpdateArtikelInService<Tauschartikel> updateArtikelInServiceTauschartikel;

	@Mock
	protected LadeAlleDeaktiviertenAngebote ladeAlleDeaktiviertenAngebote;
	@Mock
	protected LadeAlleAktiviertenAngebote ladeAlleAktiviertenAngebote;

	protected Principal testUser;

	protected Benutzer benutzer;

	protected List<TauschartikelDTO> tausch;
	protected List<HilfeleistungDTO> hilf;
	protected List<AusleihartikelDTO> ausleih;

	protected Ausleihartikel ausleihartikel;
	protected AusleihartikelDTO ausleihartikelDTO;

	protected Tauschartikel tauschartikel;
	protected TauschartikelDTO tauschartikelDTO;

	protected Hilfeleistung hilfeleistung;
	protected HilfeleistungDTO hilfeleistungDTO;

	MockMultipartFile file = new MockMultipartFile("testBild",
			"Tolles Bild".getBytes());

	@InjectMocks
	protected Object controller;

	public void initTestsAngebote(Object controller) {

		this.controller = controller;
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
				76137, "Karlsruhe", "unittest@localsharing.de", "12345678",
				null);

	}

	private void initBenutzerService() {
		when(benutzerService.findByEmail(testUser.getName())).thenReturn(
				benutzer);

		when(benutzerService.getUserByPrincipal(testUser)).thenReturn(benutzer);
	}

	private void initBenutzerServiceGetBenutzerOverAngebotIdAndType(Long id,
			String type) {
		when(benutzerService.findByAngebotsIdAndType(id, type)).thenReturn(
				benutzer);
	}

	private void initAusleihartikelService() {

		ausleihartikel = new Ausleihartikel();
		ausleihartikel.setAngebotsid(new Long(111));
		ausleihartikel.setTitel("Ich teste deinen Code");
		ausleihartikel.setBenutzer(benutzer);
		ausleihartikel.setEnabled(Boolean.TRUE);

		when(ausleihartikelService.findById(ausleihartikel.getAngebotsid()))
				.thenReturn(ausleihartikel);

		ausleihartikelDTO = new AusleihartikelDTO();
		ausleihartikelDTO.setId(ausleihartikel.getAngebotsid());
		ausleihartikelDTO.setTitel(ausleihartikel.getTitel());
		ausleihartikelDTO.setBenutzer(benutzer);

		when(ausleihartikelService.angebot_TO_AngebotDTO(ausleihartikel))
				.thenReturn(ausleihartikelDTO);

		when(
				ausleihartikelService
						.angebotDTO_TO_Angebot(any(AusleihartikelDTO.class)))
				.thenReturn(ausleihartikel);

		ausleih = new ArrayList<AusleihartikelDTO>();
		AusleihartikelDTO ausleihDTO = new AusleihartikelDTO();
		ausleihDTO.setId(new Long(111));
		ausleih.add(0, ausleihDTO);

		when(ausleihartikelService.findAllByBenutzer(benutzer)).thenReturn(
				ausleih);

		when(ausleihartikelService.findById(new Long(112))).thenReturn(null);

		when(ausleihartikelService.createAngebot(any(AusleihartikelDTO.class)))
				.thenReturn(new Long(111));

		initBenutzerServiceGetBenutzerOverAngebotIdAndType(
				ausleihartikel.getAngebotsid(), "ausleihen");

	}

	private void initTauschartikelService() {

		tauschartikel = new Tauschartikel();
		tauschartikel.setAngebotsid(new Long(222));
		tauschartikel.setTitel("Ich teste deinen Code");
		tauschartikel.setBenutzer(benutzer);
		tauschartikel.setEnabled(Boolean.TRUE);

		when(tauschartikelService.findById(new Long(222))).thenReturn(
				tauschartikel);

		tauschartikelDTO = new TauschartikelDTO();
		tauschartikelDTO.setId(tauschartikel.getAngebotsid());
		tauschartikelDTO.setTitel(tauschartikel.getTitel());
		tauschartikelDTO.setBenutzer(benutzer);

		when(tauschartikelService.angebot_TO_AngebotDTO(tauschartikel))
				.thenReturn(tauschartikelDTO);

		when(
				tauschartikelService
						.angebotDTO_TO_Angebot(any(TauschartikelDTO.class)))
				.thenReturn(tauschartikel);

		tausch = new ArrayList<TauschartikelDTO>();
		TauschartikelDTO tauschDTO = new TauschartikelDTO();
		tauschDTO.setId(new Long(222));
		tausch.add(0, tauschDTO);

		when(tauschartikelService.findAllByBenutzer(benutzer)).thenReturn(
				tausch);

		when(tauschartikelService.findById(new Long(223))).thenReturn(null);

		when(tauschartikelService.createAngebot(any(TauschartikelDTO.class)))
				.thenReturn(new Long(222));

		initBenutzerServiceGetBenutzerOverAngebotIdAndType(
				tauschartikel.getAngebotsid(), "tauschen");

	}

	private void initHilfService() {
		hilfeleistung = new Hilfeleistung();
		hilfeleistung.setAngebotsid(new Long(333));
		hilfeleistung.setTitel("Ich teste deinen Code");
		hilfeleistung.setBenutzer(benutzer);
		hilfeleistung.setEnabled(Boolean.TRUE);

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfeleistung);

		hilfeleistungDTO = new HilfeleistungDTO();
		hilfeleistungDTO.setId(hilfeleistung.getAngebotsid());
		hilfeleistungDTO.setTitel(hilfeleistung.getTitel());
		hilfeleistungDTO.setBenutzer(benutzer);

		when(hilfeleistungService.angebot_TO_AngebotDTO(hilfeleistung))
				.thenReturn(hilfeleistungDTO);

		when(
				hilfeleistungService
						.angebotDTO_TO_Angebot(any(HilfeleistungDTO.class)))
				.thenReturn(hilfeleistung);

		hilf = new ArrayList<HilfeleistungDTO>();
		HilfeleistungDTO hilfDTO = new HilfeleistungDTO();
		hilfDTO.setId(new Long(333));
		hilf.add(0, hilfDTO);

		when(hilfeleistungService.findAllByBenutzer(benutzer)).thenReturn(hilf);

		when(hilfeleistungService.findById(new Long(334))).thenReturn(null);

		when(hilfeleistungService.findById(new Long(333))).thenReturn(
				hilfeleistung);

		when(hilfeleistungService.createAngebot(any(HilfeleistungDTO.class)))
				.thenReturn(new Long(333));

		initBenutzerServiceGetBenutzerOverAngebotIdAndType(
				hilfeleistung.getAngebotsid(), "helfen");

	}

	protected void resetAndInitAllServices() {
		resetAllServices();

		initAllServices();
	}

	private void initAngebotService() {
		when(angebotService.findAngebotByIdAndType(333l, "helfen")).thenReturn(
				hilfeleistung);

		when(angebotService.findAngebotByIdAndType(111l, "ausleihen"))
				.thenReturn(ausleihartikel);

		when(angebotService.findAngebotByIdAndType(222l, "tauschen"))
				.thenReturn(tauschartikel);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("angebot", ausleihartikel);

		when(ladeEinAngebotDTO.ladeDaten()).thenReturn(map);

		when(erlaubeAnfrageVonAdmin.istAnfrageErlaubt()).thenReturn(
				Boolean.TRUE);

		when(erlaubeAnfrageVonBesitzer.istAnfrageErlaubt()).thenReturn(
				Boolean.TRUE);

		map.put("newAngebot", new AusleihartikelDTO());
		when(neuesAngebot.ladeDaten()).thenReturn(map);

	}

	private void initAllServices() {
		initBenutzerService();
		initAusleihartikelService();
		initHilfService();
		initTauschartikelService();
		initAngebotService();
	}

	private void resetAllServices() {
		reset(benutzerService);
		reset(ausleihartikelService);
		reset(tauschartikelService);
		reset(hilfeleistungService);
		reset(angebotService);
	}
}
