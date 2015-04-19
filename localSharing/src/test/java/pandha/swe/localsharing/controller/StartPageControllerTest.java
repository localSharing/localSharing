package pandha.swe.localsharing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;

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
import pandha.swe.localsharing.model.dto.BenutzerDTO;

public class StartPageControllerTest {

	String URL = "/startPage";

	@InjectMocks
	StartPageController controller;

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

	}

	@Test
	public void testStartPageWithPrincipal() throws Exception {
		mockMvc.perform(get(URL).principal(principal))
				.andExpect(status().isOk()).andExpect(view().name("startPage"));
	}

	@Test
	public void testStartPageWithOutPrincipal() throws Exception {
		mockMvc.perform(get(URL)).andExpect(status().isOk())
				.andExpect(view().name("startPage"));
	}

}
