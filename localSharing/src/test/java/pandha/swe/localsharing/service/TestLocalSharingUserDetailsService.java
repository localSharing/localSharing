package pandha.swe.localsharing.service;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.dao.BenutzerDAO;
import pandha.swe.localsharing.model.enums.Rollen;

public class TestLocalSharingUserDetailsService {

	@Mock
	private BenutzerDAO benutzerDao;

	@InjectMocks
	private LocalSharingUserDetailsService service;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(service)
				.setViewResolvers(viewResolver).build();
	}

	@Test
	public void testLoadUserByUsername() {

		reset(benutzerDao);

		String email = "testUser@localsharing.com";

		Long id = new Long(222);
		Benutzer a = new Benutzer();
		a.setId(id);
		a.setEmail(email);
		a.setPasswort("12345678");
		a.setEnabled(true);

		HashSet<BenutzerRolle> rollen = new HashSet<BenutzerRolle>();

		a.setBenutzerRolle(rollen);

		when(benutzerDao.findByEmail(email)).thenReturn(a);

		Assert.assertEquals(email, service.loadUserByUsername(email)
				.getUsername());

	}

}
