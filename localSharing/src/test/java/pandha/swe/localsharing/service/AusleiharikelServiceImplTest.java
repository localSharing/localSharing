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
	public void testDTO_TO_Ausleihartikel() throws ParseException {

		Long id = new Long(222);
		Benutzer testUser = new Benutzer();

		AusleihartikelDTO dto = new AusleihartikelDTO();
		dto.setId(id);
		dto.setBenutzer(testUser);
		dto.setBeschreibung("Test");
		dto.setDauer(2);
		dto.setStartDatum("11.11.1999");
		dto.setEndDatum("11.11.2000");
		dto.setKategorie("Test");

		Ausleihartikel aReturn = new Ausleihartikel();
		aReturn.setAngebotsid(id);
		aReturn.setBenutzer(testUser);
		aReturn.setBeschreibung("Test");
		aReturn.setDauer(2);
		aReturn.setStartDatum(new SimpleDateFormat("dd-MM-yyyy")
				.parse("11-11-1999"));
		aReturn.setEndDatum(new SimpleDateFormat("dd-MM-yyyy")
				.parse("11-11-2000"));
		aReturn.setKategorie("Test");

		when(ausleihartikelDao.findById(id)).thenReturn(aReturn);

		Ausleihartikel a = service.angebotDTO_TO_Angebot(dto);

		Assert.assertEquals(dto.getId(), a.getAngebotsid());
		Assert.assertEquals(dto.getBenutzer(), a.getBenutzer());
		Assert.assertEquals(dto.getBeschreibung(), a.getBeschreibung());
		Assert.assertEquals(dto.getDauer(), a.getDauer());
		Assert.assertEquals(dto.getStartDatum(), new SimpleDateFormat(
				"dd.MM.yyyy").format(a.getStartDatum()));
		Assert.assertEquals(dto.getEndDatum(), new SimpleDateFormat(
				"dd.MM.yyyy").format(a.getEndDatum()));
		Assert.assertEquals(dto.getKategorie(), a.getKategorie());
	}

	@Test
	public void testAusleihartikel_TO_DTO() throws ParseException {

		Long id = new Long(222);
		Benutzer testUser = new Benutzer();

		Ausleihartikel a = new Ausleihartikel();
		a.setAngebotsid(id);
		a.setBenutzer(testUser);
		a.setBeschreibung("Test");
		a.setDauer(2);
		a.setStartDatum(new SimpleDateFormat("dd-MM-yyyy").parse("11-11-1999"));
		a.setEndDatum(new SimpleDateFormat("dd-MM-yyyy").parse("11-11-2000"));
		a.setKategorie("Test");

		AusleihartikelDTO dto = service.angebot_TO_AngebotDTO(a);

		Assert.assertEquals(a.getAngebotsid(), dto.getId());
		Assert.assertEquals(a.getBenutzer(), dto.getBenutzer());
		Assert.assertEquals(a.getBeschreibung(), dto.getBeschreibung());
		Assert.assertEquals(a.getDauer(), dto.getDauer());
		Assert.assertEquals(
				new SimpleDateFormat("dd.MM.yyyy").format(a.getStartDatum()),
				dto.getStartDatum());
		Assert.assertEquals(
				new SimpleDateFormat("dd.MM.yyyy").format(a.getEndDatum()),
				dto.getEndDatum());
		Assert.assertEquals(a.getKategorie(), dto.getKategorie());
	}

	@Test
	public void testCreateAusleihartikel() {

		reset(ausleihartikelDao);

		Long id = new Long(222);
		AusleihartikelDTO a = new AusleihartikelDTO();
		a.setId(null);
		a.setBenutzer(new Benutzer());
		a.setBeschreibung("Test");
		a.setDauer(2);
		a.setStartDatum("11.11.1999");
		a.setEndDatum("11.11.2000");
		a.setKategorie("Test");

		when(ausleihartikelDao.save(any(Ausleihartikel.class))).thenReturn(id);

		Assert.assertEquals(id, service.createAngebot(a));

		verify(ausleihartikelDao, times(1)).save(any(Ausleihartikel.class));

	}

	@Test
	public void testShutdown() {

		reset(ausleihartikelDao);

		service.shutdown();
		verify(ausleihartikelDao, times(1)).shutdown();

	}

}
