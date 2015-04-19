package pandha.swe.localsharing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.HashSet;
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

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.dto.BenutzerDTO;
import pandha.swe.localsharing.model.enums.Geschlecht;
import pandha.swe.localsharing.model.enums.Rollen;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;

//@RunWith(SpringJUnit4ClassRunner.class)
public class ProfilControllerTest {

	@InjectMocks
	ProfilController controller;

	@Mock
	FileService fileService;

	@Mock
	BenutzerService benutzerService;

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
				76137, "Karlsruhe", "unitTest@localsharing.de", "12345678",
				rollen);
		
		
		dto = new BenutzerDTO(Geschlecht.MANN, "Peter", "Hans", "Erzbergerstraße", "123",
				"76137", "Karlsruhe", "unitTest@localsharing.de", "12345678");

		rollen.add(new BenutzerRolle(new Long(13), null, Rollen.USER));

		when(benutzerService.findByEmail(principal.getName())).thenReturn(
				benutzer);
		
		when(benutzerService.benutzer_TO_BenutzerDTO(benutzer)).thenReturn(
				dto);

		// when(benutzerService.getUser(principal)).thenReturn(
		// new BenutzerDTO(Geschlecht.MANN, "123", "Peter", "Blau", "123",
		// "12", "69115", "Heidelberg", "123"));

	}

	@Test
	public void testShowProfil() throws Exception {
		mockMvc.perform(get("/profil").principal(principal))
				.andExpect(status().isOk()).andExpect(view().name("profil"))
				.andExpect(model().attribute("user", dto));
	}
	
	
	 @Test
	 public void testShowProfilEdit() throws Exception {
		 mockMvc.perform(get("/profilEdit").principal(principal))
			.andExpect(status().isOk()).andExpect(view().name("profilEdit"))
			.andExpect(model().attribute("user", dto));
	 }
	
	 @Test
	 public void testEditProfilNoParameters() throws Exception {
		 mockMvc.perform(post("/profilEdit").principal(principal))
			.andExpect(status().is(400));
	 }
	 
	 //TODO Test für Profil Bearbeiten schreiben
//	 @Test
//	 public void testEditProfilWithParameters() throws Exception {
//		 mockMvc.perform(post("/profilEdit").param("user", dto.toString()).principal(principal))
//			.andExpect(status().isOk());
//	 }
	 
	 

}
