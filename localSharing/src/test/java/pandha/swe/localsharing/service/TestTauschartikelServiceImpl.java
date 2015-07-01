package pandha.swe.localsharing.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class TestTauschartikelServiceImpl {

	@Mock
	private TauschartikelDAO tauschartikelDao;

	@InjectMocks
	private TauschartikelServiceImpl service;

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
	public void testFindById() {

		reset(tauschartikelDao);

		Long id = new Long(222);
		Tauschartikel t = new Tauschartikel();
		t.setAngebotsid(id);
		when(tauschartikelDao.findById(id)).thenReturn(t);

		Assert.assertEquals(t, service.findById(id));

		verify(tauschartikelDao, times(1)).findById(id);
	}

	@Test
	public void testFindAll() {

		reset(tauschartikelDao);

		List<Tauschartikel> alle = new ArrayList<Tauschartikel>();

		Long id = new Long(222);
		Tauschartikel a = new Tauschartikel();
		a.setAngebotsid(id);
		alle.add(0, a);

		when(tauschartikelDao.findAll()).thenReturn(alle);

		Assert.assertEquals(alle, service.findAll());

		verify(tauschartikelDao, times(1)).findAll();

	}

	@Test
	public void testFindAllByBenutzer() throws ParseException {

		reset(tauschartikelDao);

		Benutzer testUser = new Benutzer();
		testUser.setId(new Long(111));

		List<Tauschartikel> alle = new ArrayList<Tauschartikel>();

		Long id = new Long(222);
		Tauschartikel a = new Tauschartikel();
		a.setAngebotsid(id);
		a.setBenutzer(testUser);
		a.setBeschreibung("Test");
		a.setKategorie("Test");
		a.setStartDatum(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));
		alle.add(0, a);

		when(tauschartikelDao.findAllByBenutzer(testUser)).thenReturn(alle);

		List<TauschartikelDTO> list = service.findAllByBenutzer(testUser);

		Assert.assertEquals(a.getAngebotsid(), list.get(0).getId());

		verify(tauschartikelDao, times(1)).findAllByBenutzer(testUser);

	}

	@Test
	public void testSave() {
		reset(tauschartikelDao);

		Long id = new Long(222);
		Tauschartikel a = new Tauschartikel();
		a.setAngebotsid(id);

		when(tauschartikelDao.save(a)).thenReturn(id);

		Assert.assertEquals(id, service.save(a));

		verify(tauschartikelDao, times(1)).save(a);

	}

	@Test
	public void testUpdate() {
		reset(tauschartikelDao);

		Long id = new Long(222);
		Tauschartikel a = new Tauschartikel();
		a.setAngebotsid(id);

		service.update(a);
		verify(tauschartikelDao, times(1)).update(a);

	}

	@Test
	public void testDelete() {
		reset(tauschartikelDao);

		Long id = new Long(222);
		Tauschartikel a = new Tauschartikel();
		a.setAngebotsid(id);

		service.delete(a);
		verify(tauschartikelDao, times(1)).delete(a);
	}

	@Test
	public void testCreateTauschartikel() {
		reset(tauschartikelDao);

		Long id = new Long(222);
		TauschartikelDTO a = new TauschartikelDTO();
		a.setId(null);
		a.setBenutzer(new Benutzer());
		a.setBeschreibung("Test");
		a.setStartDatum("11.11.1999");
		a.setKategorie("Test");

		when(tauschartikelDao.save(any(Tauschartikel.class))).thenReturn(id);

		Assert.assertEquals(id, service.createAngebot(a));

		verify(tauschartikelDao, times(1)).save(any(Tauschartikel.class));

	}

	@Test
	public void testTauschartikelDTO_TO_Tauschartikel() throws ParseException {
		Long id = new Long(222);
		Benutzer testUser = new Benutzer();

		TauschartikelDTO dto = new TauschartikelDTO();
		dto.setId(id);
		dto.setBenutzer(testUser);
		dto.setBeschreibung("Test");
		dto.setStartDatum("11.11.1999");
		dto.setKategorie("Test");

		Tauschartikel aReturn = new Tauschartikel();
		aReturn.setAngebotsid(id);
		aReturn.setBenutzer(testUser);
		aReturn.setBeschreibung("Test");
		aReturn.setStartDatum(new SimpleDateFormat("dd-MM-yyyy")
				.parse("11-11-1999"));
		aReturn.setKategorie("Test");

		when(tauschartikelDao.findById(id)).thenReturn(aReturn);

		Tauschartikel a = service.angebotDTO_TO_Angebot(dto);

		Assert.assertEquals(dto.getId(), a.getAngebotsid());
		Assert.assertEquals(dto.getBenutzer(), a.getBenutzer());
		Assert.assertEquals(dto.getBeschreibung(), a.getBeschreibung());
		Assert.assertEquals(dto.getStartDatum(), new SimpleDateFormat(
				"dd.MM.yyyy").format(a.getStartDatum()));
		Assert.assertEquals(dto.getKategorie(), a.getKategorie());

	}

	@Test
	public void testTauschartikel_TO_TauschartikelDTO() throws ParseException {
		Long id = new Long(222);
		Benutzer testUser = new Benutzer();

		Tauschartikel a = new Tauschartikel();
		a.setAngebotsid(id);
		a.setBenutzer(testUser);
		a.setBeschreibung("Test");
		a.setStartDatum(new SimpleDateFormat("dd-MM-yyyy").parse("11-11-1999"));
		a.setKategorie("Test");

		TauschartikelDTO dto = service.angebot_TO_AngebotDTO(a);

		Assert.assertEquals(a.getAngebotsid(), dto.getId());
		Assert.assertEquals(a.getBenutzer(), dto.getBenutzer());
		Assert.assertEquals(a.getBeschreibung(), dto.getBeschreibung());
		Assert.assertEquals(
				new SimpleDateFormat("dd.MM.yyyy").format(a.getStartDatum()),
				dto.getStartDatum());
		Assert.assertEquals(a.getKategorie(), dto.getKategorie());

	}

	@Test
	public void testShutdown() {
		reset(tauschartikelDao);

		service.shutdown();
		verify(tauschartikelDao, times(1)).shutdown();

	}

}
