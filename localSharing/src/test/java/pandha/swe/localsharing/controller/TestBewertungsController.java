package pandha.swe.localsharing.controller;

import static org.mockito.Matchers.any;
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
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.Bewertung;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.BenutzerDTO;
import pandha.swe.localsharing.model.dto.BewertungDTO;
import pandha.swe.localsharing.model.enums.Geschlecht;
import pandha.swe.localsharing.model.enums.Rollen;
import pandha.swe.localsharing.service.AngebotService;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.BewertungService;

public class TestBewertungsController {

	@InjectMocks
	BewertungsController controller;

	@Mock
	AngebotService angebotService;

	@Mock
	BenutzerService benutzerService;

	@Mock
	BewertungService bewertungService;

	@Mock
	View mockView;
	MockMvc mockMvc;

	Principal principal;

	Benutzer benutzer;
	BenutzerDTO dto;

	private AusleihartikelDTO ausleihartikelDTO;

	private Ausleihartikel ausleihartikel;

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

		ausleihartikel = new Ausleihartikel();
		ausleihartikel.setAngebotsid(new Long(111));
		ausleihartikel.setTitel("Ich teste deinen Code");
		ausleihartikel.setBenutzer(benutzer);

		ausleihartikelDTO = new AusleihartikelDTO();
		ausleihartikelDTO.setId(111l);
		ausleihartikelDTO.setTitel("Titel");
		ausleihartikelDTO.setBenutzer(benutzer);

		initServices();

	}

	private void initServices() {
		when(benutzerService.findByEmail(principal.getName())).thenReturn(
				benutzer);

		when(benutzerService.getUserByPrincipal(principal))
				.thenReturn(benutzer);

		when(benutzerService.benutzer_TO_BenutzerDTO(benutzer)).thenReturn(dto);

		when(bewertungService.findAllByEmpfaengerId(Long.valueOf(203)))
				.thenReturn(new ArrayList<Bewertung>());

		when(
				bewertungService.list_Bewertung_TO_BewertungDTO(Matchers
						.anyListOf(Bewertung.class))).thenReturn(
				new ArrayList<BewertungDTO>());
	}

	@Test
	public void testBewerteAngebotBesitzer() throws Exception {

		when(angebotService.findAngebotById(111l)).thenReturn(ausleihartikel);
		when(angebotService.angebot_TO_AngebotDTO(ausleihartikel)).thenReturn(
				ausleihartikelDTO);
		when(benutzerService.getUserByPrincipal(principal))
				.thenReturn(benutzer);

		mockMvc.perform(get("/angebot/111/rate").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/angebot/111"));

	}

	@Test
	public void testBewerteAngebotNotBesitzer() throws Exception {

		when(angebotService.findAngebotById(111l)).thenReturn(ausleihartikel);
		when(angebotService.angebot_TO_AngebotDTO(ausleihartikel)).thenReturn(
				ausleihartikelDTO);

		Benutzer benutzer2 = new Benutzer();
		benutzer2.setId(12l);
		when(benutzerService.getUserByPrincipal(principal)).thenReturn(
				benutzer2);

		mockMvc.perform(get("/angebot/111/rate").principal(principal))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("bewertung"))
				.andExpect(model().attributeExists("angebot"))
				.andExpect(view().name("bewerten"));
	}

	@Test
	public void testSaveRating() throws Exception {

		when(benutzerService.getUserByPrincipal(principal))
				.thenReturn(benutzer);
		when(angebotService.findAngebotById(111l)).thenReturn(ausleihartikel);

		mockMvc.perform(post("/angebot/111/rate").principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/angebot/111"));

		verify(bewertungService, times(1)).createBewertung(
				any(BewertungDTO.class));

	}
}
