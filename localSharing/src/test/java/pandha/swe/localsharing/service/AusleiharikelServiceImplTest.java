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

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;

public class AusleiharikelServiceImplTest {

	@Mock
	private AusleihartikelDAO ausleihartikelDao;

	@InjectMocks
	private AusleihartikelServiceImpl service;

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

		reset(ausleihartikelDao);

		Long id = new Long(222);
		Ausleihartikel a = new Ausleihartikel();
		a.setAngebotsid(id);
		when(ausleihartikelDao.findById(id)).thenReturn(a);

		Assert.assertEquals(a, service.findById(id));

		verify(ausleihartikelDao, times(1)).findById(id);
	}

	@Test
	public void testFindAll() {

		reset(ausleihartikelDao);

		List<Ausleihartikel> alle = new ArrayList<Ausleihartikel>();

		Long id = new Long(222);
		Ausleihartikel a = new Ausleihartikel();
		a.setAngebotsid(id);
		alle.add(0, a);

		when(ausleihartikelDao.findAll()).thenReturn(alle);

		Assert.assertEquals(alle, service.findAll());

		verify(ausleihartikelDao, times(1)).findAll();

	}

	@Test
	public void testFindAllByBenutzer() throws ParseException {
		reset(ausleihartikelDao);

		Benutzer testUser = new Benutzer();
		testUser.setId(new Long(111));

		List<Ausleihartikel> alle = new ArrayList<Ausleihartikel>();

		Long id = new Long(222);
		Ausleihartikel a = new Ausleihartikel();
		a.setAngebotsid(id);
		a.setBenutzer(testUser);
		a.setBeschreibung("Test");
		a.setDauer(3);
		a.setKategorie("Test");
		a.setStartDatum(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));
		a.setEndDatum(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));
		alle.add(0, a);

		when(ausleihartikelDao.findAllByBenutzer(testUser)).thenReturn(alle);

		List<AusleihartikelDTO> list = service.findAllByBenutzer(testUser);

		Assert.assertEquals(a.getAngebotsid(), list.get(0).getId());

		verify(ausleihartikelDao, times(1)).findAllByBenutzer(testUser);
	}

	@Test
	public void testSave() {

		reset(ausleihartikelDao);

		Long id = new Long(222);
		Ausleihartikel a = new Ausleihartikel();
		a.setAngebotsid(id);

		when(ausleihartikelDao.save(a)).thenReturn(id);

		Assert.assertEquals(id, service.save(a));

		verify(ausleihartikelDao, times(1)).save(a);

	}

	@Test
	public void testUpdate() {

		reset(ausleihartikelDao);

		Long id = new Long(222);
		Ausleihartikel a = new Ausleihartikel();
		a.setAngebotsid(id);

		service.update(a);
		verify(ausleihartikelDao, times(1)).update(a);

	}

	@Test
	public void testDelete() {

		reset(ausleihartikelDao);

		Long id = new Long(222);
		Ausleihartikel a = new Ausleihartikel();
		a.setAngebotsid(id);

		service.delete(a);
		verify(ausleihartikelDao, times(1)).delete(a);

	}

	@Test
	public void testCreateAusleihartikel() {

		reset(ausleihartikelDao);

		Long id = new Long(222);
		Ausleihartikel a = new Ausleihartikel();
		a.setAngebotsid(id);

		Long long1 = new Long(111);

		when(ausleihartikelDao.save(any(Ausleihartikel.class))).thenReturn(
				long1);

		Assert.assertEquals(long1, service.save(a));

		verify(ausleihartikelDao, times(1)).save(a);

	}

	@Test
	public void testShutdown() {

		reset(ausleihartikelDao);

		service.shutdown();
		verify(ausleihartikelDao, times(1)).shutdown();

	}

}
