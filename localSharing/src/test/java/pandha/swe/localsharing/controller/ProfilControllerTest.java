//package pandha.swe.localsharing.controller;
//
//import static org.junit.Assert.fail;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.get;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.servlet.View;
//
//import pandha.swe.localsharing.service.BenutzerService;
//import pandha.swe.localsharing.service.FileService;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ProfilControllerTest {
//
//	@InjectMocks
//	ProfilController controller;
//
//	@Mock
//	FileService fileService;
//
//	@Mock
//	BenutzerService benutzerService;
//
//	@Mock
//	View mockView;
//
//	MockMvc mockMvc;
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = standaloneSetup(controller).setSingleView(mockView).build();
//	}
//
//	@Test
//	public void testListPeopleInGroup() throws Exception {
////		List<Person> expectedPeople = asList(new Person());
////		when(mockPeopleService.listPeople("someGroup")).thenReturn(
////				expectedPeople);
//
//	}
//
//	@Test
//	public void testShowProfil() {
//		mockMvc.perform(get("/people/someGroup")).andExpect(status().isOk())
//				.andExpect(model().attribute("people", expectedPeople))
//				.andExpect(view().name("peopleList"));
//	}
//
//	@Test
//	public void testShowProfilEdit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEditProfil() {
//		fail("Not yet implemented");
//	}
//
//}
