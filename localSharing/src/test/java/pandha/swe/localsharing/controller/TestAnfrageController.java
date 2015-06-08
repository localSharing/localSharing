package pandha.swe.localsharing.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.Anfrage;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AnfrageDTO;
import pandha.swe.localsharing.model.dto.AngebotDTO;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.BenutzerDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.model.enums.AnfrageStatus;
import pandha.swe.localsharing.model.enums.Geschlecht;
import pandha.swe.localsharing.model.enums.Rollen;
import pandha.swe.localsharing.service.AnfrageService;
import pandha.swe.localsharing.service.AngebotService;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public class TestAnfrageController {
	@InjectMocks
	AnfrageController controller;

	@Mock
	AnfrageService anfrageService;

	@Mock
	BenutzerService benutzerService;

	@Mock
	AngebotService angebotService;

	@Mock
	AusleihartikelService ausleihartikelService;

	@Mock
	TauschartikelService tauschartikelService;

	@Mock
	HilfeleistungService hilfeleistungService;

	@Mock
	View mockView;
	MockMvc mockMvc;

	Principal principal;

	Benutzer benutzer;
	BenutzerDTO dto;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setViewResolvers(viewResolver).build();

		principal = new Principal() {

			@Override
			public String getName() {
				return "12345678";
			}
		};

		Set<BenutzerRolle> rollen = new HashSet<>();
		benutzer = new Benutzer(new Long(203), "12345678", true,
				Geschlecht.MANN, "Peter", "Hans", "Erzbergerstraße", "123",
				76137, "Karlsruhe", "unittest@localsharing.de", "12345678",
				rollen);

		dto = new BenutzerDTO(true, Geschlecht.MANN, "Peter", "Hans",
				"Erzbergerstraße", "123", "76137", "Karlsruhe",
				"unittest@localsharing.de", "12345678");

		rollen.add(new BenutzerRolle(new Long(13), null, Rollen.USER));

		initServices();

	}

	@SuppressWarnings("unchecked")
	private void initServices() {
		when(benutzerService.findByEmail(principal.getName())).thenReturn(
				benutzer);

		when(benutzerService.getUserByPrincipal(principal))
				.thenReturn(benutzer);

		when(benutzerService.benutzer_TO_BenutzerDTO(benutzer)).thenReturn(dto);

		when(anfrageService.findAllBySender(benutzer)).thenReturn(
				new ArrayList<Anfrage>());
		when(anfrageService.findAllByEmpfaenger(benutzer)).thenReturn(
				new ArrayList<Anfrage>());
		when(anfrageService.list_Anfrage_TO_AnfrageDTO(any(List.class)))
				.thenReturn(new ArrayList<AnfrageDTO>());

	}

	private void resetAndInitAll() {
		reset(benutzerService);
		reset(anfrageService);
		reset(angebotService);
		initServices();

	}

	@Test
	public void zeigeAnfragen() throws Exception {
		resetAndInitAll();

		mockMvc.perform(get("/anfragen").principal(principal))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("anfragenListG"))
				.andExpect(model().attributeExists("anfragenListE"))
				.andExpect(view().name("anfragen"));
	}

	@Test
	public void zeigeNeueAnfrageGleicheBenutzer() throws Exception {
		resetAndInitAll();

		AngebotDTO angebot = new AngebotDTO();
		angebot.setBenutzer(benutzer);

		when(angebotService.angebot_TO_AngebotDTO(any(Angebot.class)))
				.thenReturn(angebot);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);

		mockMvc.perform(get("/angebot/111/inquire").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/angebot/111"));
	}

	@Test
	public void zeigeNeueAnfrageUngleicheBenutzer() throws Exception {
		resetAndInitAll();

		AngebotDTO angebot = new AngebotDTO();
		angebot.setBenutzer(benutzer);

		when(angebotService.angebot_TO_AngebotDTO(any(Angebot.class)))
				.thenReturn(angebot);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.FALSE);

		mockMvc.perform(get("/angebot/111/inquire").principal(principal))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("anfrage"))
				.andExpect(view().name("anfrageSenden"));
	}

	@Test
	public void zeigeNeueAnfrageUngleicheBenutzerAusleihartikel()
			throws Exception {
		resetAndInitAll();

		AusleihartikelDTO angebot = new AusleihartikelDTO();
		angebot.setBenutzer(benutzer);

		when(
				ausleihartikelService
						.angebot_TO_AngebotDTO(any(Ausleihartikel.class)))
				.thenReturn(angebot);
		when(angebotService.angebot_TO_AngebotDTO(any(Angebot.class)))
				.thenReturn(angebot);
		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.FALSE);

		mockMvc.perform(get("/angebot/111/inquire").principal(principal))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("anfrage"))
				.andExpect(view().name("anfrageSenden"));
	}

	@Test
	public void zeigeNeueAnfrageUngleicheBenutzerTauschartikel()
			throws Exception {
		resetAndInitAll();

		TauschartikelDTO angebot = new TauschartikelDTO();
		angebot.setBenutzer(benutzer);

		when(
				tauschartikelService
						.angebot_TO_AngebotDTO(any(Tauschartikel.class)))
				.thenReturn(angebot);
		when(angebotService.angebot_TO_AngebotDTO(any(Angebot.class)))
				.thenReturn(angebot);
		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.FALSE);

		mockMvc.perform(get("/angebot/111/inquire").principal(principal))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("anfrage"))
				.andExpect(view().name("anfrageSenden"));
	}

	@Test
	public void zeigeNeueAnfrageUngleicheBenutzerHilfeleistung()
			throws Exception {
		resetAndInitAll();

		HilfeleistungDTO angebot = new HilfeleistungDTO();
		angebot.setBenutzer(benutzer);

		when(
				hilfeleistungService
						.angebot_TO_AngebotDTO(any(Hilfeleistung.class)))
				.thenReturn(angebot);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.FALSE);
		when(angebotService.angebot_TO_AngebotDTO(any(Angebot.class)))
				.thenReturn(angebot);
		mockMvc.perform(get("/angebot/111/inquire").principal(principal))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("anfrage"))
				.andExpect(view().name("anfrageSenden"));
	}

	@Test
	public void sendeAnfrage() throws Exception {
		resetAndInitAll();

		HilfeleistungDTO angebot = new HilfeleistungDTO();
		angebot.setBenutzer(benutzer);

		when(
				hilfeleistungService
						.angebot_TO_AngebotDTO(any(Hilfeleistung.class)))
				.thenReturn(angebot);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.FALSE);
		when(angebotService.angebot_TO_AngebotDTO(any(Angebot.class)))
				.thenReturn(angebot);
		mockMvc.perform(post("/angebot/111/inquire").principal(principal))
				.andExpect(status().isFound())
				.andExpect(model().attributeExists("anfrage"))
				.andExpect(view().name("redirect:/angebot/111"));

		verify(anfrageService, times(1)).createAnfrage(any(AnfrageDTO.class));
	}

	@Test
	public void ZeigeAnfrage() throws Exception {
		resetAndInitAll();

		Angebot angebot = new Angebot();
		Anfrage anfrage = new Anfrage();
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);
		mockMvc.perform(get("/angebot/111/inquiry/1").principal(principal))
				.andExpect(status().isOk()).andExpect(view().name("anfrage"));

	}

	@Test
	public void ZeigeAnfrageAusleihartikel() throws Exception {
		resetAndInitAll();

		Ausleihartikel angebot = new Ausleihartikel();
		Anfrage anfrage = new Anfrage();
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);
		mockMvc.perform(get("/angebot/111/inquiry/1").principal(principal))
				.andExpect(status().isOk()).andExpect(view().name("anfrage"));

	}

	@Test
	public void ZeigeAnfrageTauschartikel() throws Exception {
		resetAndInitAll();

		Tauschartikel angebot = new Tauschartikel();
		Anfrage anfrage = new Anfrage();
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);
		mockMvc.perform(get("/angebot/111/inquiry/1").principal(principal))
				.andExpect(status().isOk()).andExpect(view().name("anfrage"));

	}

	@Test
	public void ZeigeAnfrageHilfeleistung() throws Exception {
		resetAndInitAll();

		Hilfeleistung angebot = new Hilfeleistung();
		Anfrage anfrage = new Anfrage();
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);
		mockMvc.perform(get("/angebot/111/inquiry/1").principal(principal))
				.andExpect(status().isOk()).andExpect(view().name("anfrage"));

	}

	@Test
	public void AcceptAnfrage() throws Exception {
		resetAndInitAll();

		Hilfeleistung angebot = new Hilfeleistung();
		angebot.setAngebotsid(111l);
		Anfrage anfrage = new Anfrage();
		anfrage.setStatus(AnfrageStatus.offen);
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);
		mockMvc.perform(post("/inquiry/1/accept").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:../../angebot/111/inquiry/1"));

		verify(anfrageService, times(1)).update(anfrage);

	}

	@Test
	public void AcceptAnfrageAnfrageNichtOffen() throws Exception {
		resetAndInitAll();

		Hilfeleistung angebot = new Hilfeleistung();
		angebot.setAngebotsid(111l);
		Anfrage anfrage = new Anfrage();
		anfrage.setStatus(AnfrageStatus.gesperrt);
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);
		mockMvc.perform(post("/inquiry/1/accept").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:../../anfragen"));

		verify(anfrageService, times(0)).update(anfrage);

	}

	@Test
	public void AcceptAnfragePrincipalIstNichtEmpfaenger() throws Exception {
		resetAndInitAll();

		Hilfeleistung angebot = new Hilfeleistung();
		angebot.setAngebotsid(111l);
		Anfrage anfrage = new Anfrage();
		anfrage.setStatus(AnfrageStatus.offen);
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.FALSE);
		mockMvc.perform(post("/inquiry/1/accept").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:../../anfragen"));

		verify(anfrageService, times(0)).update(anfrage);

	}

	@Test
	public void DeclineAnfrage() throws Exception {
		resetAndInitAll();

		Hilfeleistung angebot = new Hilfeleistung();
		angebot.setAngebotsid(111l);
		Anfrage anfrage = new Anfrage();
		anfrage.setStatus(AnfrageStatus.offen);
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);
		mockMvc.perform(get("/inquiry/1/decline").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:../../angebot/111/inquiry/1"));

		verify(anfrageService, times(1)).update(anfrage);

	}

	@Test
	public void DeclineAnfrageAnfrageNichtOffen() throws Exception {
		resetAndInitAll();

		Hilfeleistung angebot = new Hilfeleistung();
		angebot.setAngebotsid(111l);
		Anfrage anfrage = new Anfrage();
		anfrage.setStatus(AnfrageStatus.gesperrt);
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.TRUE);
		mockMvc.perform(get("/inquiry/1/decline").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:../../anfragen"));

		verify(anfrageService, times(0)).update(anfrage);

	}

	@Test
	public void DeclineAnfragePrincipalIstNichtEmpfaenger() throws Exception {
		resetAndInitAll();

		Hilfeleistung angebot = new Hilfeleistung();
		angebot.setAngebotsid(111l);
		Anfrage anfrage = new Anfrage();
		anfrage.setStatus(AnfrageStatus.offen);
		anfrage.setAngebot(angebot);
		benutzer.setVorname("Peter");
		anfrage.setSender(benutzer);
		when(anfrageService.findById(Long.valueOf(1l))).thenReturn(anfrage);

		when(
				benutzerService.sindDieBenutzerGleich(any(Benutzer.class),
						any(Benutzer.class))).thenReturn(Boolean.FALSE);
		mockMvc.perform(get("/inquiry/1/decline").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:../../anfragen"));

		verify(anfrageService, times(0)).update(anfrage);

	}
}
