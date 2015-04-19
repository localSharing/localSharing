package pandha.swe.localsharing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class LoginControllerTest {

	String URL = "/login";

	@InjectMocks
	LoginController controller;

	MockMvc mockMvc;

	Principal principal;

	@Before
	public void setUp() throws Exception {

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
	public void getLoginPageWithPrincipal() throws Exception {

		mockMvc.perform(get(URL).principal(principal))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:startPage"));

	}

	@Test
	public void getLoginPageWithOutPrincipal() throws Exception {

		mockMvc.perform(get(URL)).andExpect(status().isOk())
				.andExpect(view().name("login"));
	}

}
