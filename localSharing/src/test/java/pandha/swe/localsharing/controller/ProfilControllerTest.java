package pandha.swe.localsharing.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;

//@RunWith(MockitoJUnitRunner.class)
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


    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

	@Test
	public void testShowProfil() throws Exception {
		mockMvc.perform(get("/profil")).andExpect(status().isOk());
	}

//	@Test
//	public void testShowProfilEdit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEditProfil() {
//		fail("Not yet implemented");
//	}

}
