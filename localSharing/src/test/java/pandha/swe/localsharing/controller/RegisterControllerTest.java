package pandha.swe.localsharing.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.BenutzerRegisterDTO;
import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;

public class RegisterControllerTest {

	private String URL = "/register";

	@InjectMocks
	RegisterController controller;

	@Mock
	BenutzerService benutzerService;

	@Mock
	FileService fileService;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setViewResolvers(viewResolver).build();

	}

	@Test
	public void testRegisterForm() throws Exception {
		mockMvc.perform(get(URL)).andExpect(status().isOk())
				.andExpect(view().name("register"))
				.andExpect(model().attributeExists("newUser"));

	}

	@Test
	public void testSubmitRegisterForm() throws Exception {
		reset(benutzerService);
		reset(fileService);

		when(benutzerService.findByEmail("nouser@localsharing.de")).thenReturn(
				null);
		when(benutzerService.findByEmail("user@localsharing.de")).thenReturn(
				new Benutzer());

		mockMvc.perform(
				fileUpload("/register")
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("geschlecht", "MANN")
						.param("vorname", "Johannes").param("nachname", "Blau")
						.param("strasse", "Erzbergerstrasse")
						.param("hausnummer", "13").param("plz", "69115")
						.param("stadt", "Heidelberg")
						.param("telefonNummer", "12345678")
						.param("email", "nouser@localsharing.de")
						.param("password1", "12345678")
						.param("password2", "12345678"))
				.andExpect(status().isFound()).andExpect(model().hasNoErrors())
				.andExpect(view().name("redirect:login"));

		verify(benutzerService, times(1)).registerBenutzer(
				any(BenutzerRegisterDTO.class));
		verify(fileService, times(0)).save(any(Benutzer.class),
				any(MultipartFile.class));
	}

	@Test
	public void testSubmitRegisterFormWithResultHasErrors() throws Exception {
		reset(benutzerService);
		reset(fileService);

		when(benutzerService.findByEmail("nouser@localsharing.de")).thenReturn(
				null);
		when(benutzerService.findByEmail("user@localsharing.de")).thenReturn(
				new Benutzer());

		mockMvc.perform(
				fileUpload("/register")
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("geschlecht", "PETER123")
						// <-Erzeuge Error
						.param("vorname", "Johannes").param("nachname", "Blau")
						.param("strasse", "Erzbergerstrasse")
						.param("hausnummer", "13").param("plz", "69115")
						.param("stadt", "Heidelberg")
						.param("telefonNummer", "12345678")
						.param("email", "nouser@localsharing.de")
						.param("password1", "12345678")
						.param("password2", "12345678"))
				.andExpect(status().isOk()).andExpect(model().hasErrors())
				.andExpect(view().name("register"));

		verify(benutzerService, times(0)).registerBenutzer(
				any(BenutzerRegisterDTO.class));
		verify(fileService, times(0)).save(any(Benutzer.class),
				any(MultipartFile.class));

	}

	@Test
	public void testSubmitRegisterFormPasswoerterUngleich() throws Exception {
		reset(benutzerService);
		reset(fileService);

		when(benutzerService.findByEmail("nouser@localsharing.de")).thenReturn(
				null);
		when(benutzerService.findByEmail("user@localsharing.de")).thenReturn(
				new Benutzer());

		mockMvc.perform(
				fileUpload("/register")
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("geschlecht", "MANN")
						.param("vorname", "Johannes").param("nachname", "Blau")
						.param("strasse", "Erzbergerstrasse")
						.param("hausnummer", "13").param("plz", "69115")
						.param("stadt", "Heidelberg")
						.param("telefonNummer", "12345678")
						.param("email", "nouser@localsharing.de")
						.param("password1", "00000000")// <-Erzeuge Error
						.param("password2", "11111111"))
				.andExpect(status().isOk()).andExpect(model().hasErrors())
				.andExpect(view().name("register"));

		verify(benutzerService, times(0)).registerBenutzer(
				any(BenutzerRegisterDTO.class));
		verify(fileService, times(0)).save(any(Benutzer.class),
				any(MultipartFile.class));

	}

	@Test
	public void testSubmitRegisterFormEmailBereitsInVerwedung()
			throws Exception {
		reset(benutzerService);
		reset(fileService);

		when(benutzerService.findByEmail("nouser@localsharing.de")).thenReturn(
				null);
		when(benutzerService.findByEmail("user@localsharing.de")).thenReturn(
				new Benutzer());

		mockMvc.perform(
				fileUpload("/register")
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("geschlecht", "MANN")
						.param("vorname", "Johannes").param("nachname", "Blau")
						.param("strasse", "Erzbergerstrasse")
						.param("hausnummer", "13").param("plz", "69115")
						.param("stadt", "Heidelberg")
						.param("telefonNummer", "12345678")
						.param("email", "user@localsharing.de")
						.param("password1", "12345678")
						.param("password2", "12345678"))
				.andExpect(status().isOk()).andExpect(model().hasErrors())
				.andExpect(view().name("register"));

		verify(benutzerService, times(0)).registerBenutzer(
				any(BenutzerRegisterDTO.class));
		verify(fileService, times(0)).save(any(Benutzer.class),
				any(MultipartFile.class));
	}

	@Test
	public void testSubmitRegisterFormWithImage() throws Exception {
		reset(benutzerService);
		reset(fileService);

		when(benutzerService.findByEmail("nouser@localsharing.de")).thenReturn(
				null);
		when(benutzerService.findByEmail("user@localsharing.de")).thenReturn(
				new Benutzer());

		mockMvc.perform(
				fileUpload("/register").file("userImage", "123456".getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("geschlecht", "MANN")
						.param("vorname", "Johannes").param("nachname", "Blau")
						.param("strasse", "Erzbergerstrasse")
						.param("hausnummer", "13").param("plz", "69115")
						.param("stadt", "Heidelberg")
						.param("telefonNummer", "12345678")
						.param("email", "nouser@localsharing.de")
						.param("password1", "12345678")
						.param("password2", "12345678"))
				.andExpect(status().isFound()).andExpect(model().hasNoErrors())
				.andExpect(view().name("redirect:login"));

		verify(benutzerService, times(1)).registerBenutzer(
				any(BenutzerRegisterDTO.class));
		verify(fileService, times(1)).save(any(Benutzer.class),
				any(MultipartFile.class));
	}

	@Test
	public void testSubmitRegisterFormWithEmptyImage() throws Exception {
		reset(benutzerService);
		reset(fileService);

		when(benutzerService.findByEmail("nouser@localsharing.de")).thenReturn(
				null);
		when(benutzerService.findByEmail("user@localsharing.de")).thenReturn(
				new Benutzer());

		mockMvc.perform(
				fileUpload("/register").file("userImage", "".getBytes())
						.contentType(MediaType.MULTIPART_FORM_DATA)
						.param("geschlecht", "MANN")
						.param("vorname", "Johannes").param("nachname", "Blau")
						.param("strasse", "Erzbergerstrasse")
						.param("hausnummer", "13").param("plz", "69115")
						.param("stadt", "Heidelberg")
						.param("telefonNummer", "12345678")
						.param("email", "nouser@localsharing.de")
						.param("password1", "12345678")
						.param("password2", "12345678"))
				.andExpect(status().isFound()).andExpect(model().hasNoErrors())
				.andExpect(view().name("redirect:login"));

		verify(benutzerService, times(1)).registerBenutzer(
				any(BenutzerRegisterDTO.class));
		verify(fileService, times(0)).save(any(Benutzer.class),
				any(MultipartFile.class));
	}

}
