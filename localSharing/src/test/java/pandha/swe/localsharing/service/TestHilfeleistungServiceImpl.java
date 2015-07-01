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
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;

public class TestHilfeleistungServiceImpl {

	@Mock
	private HilfeleistungDAO HilfeleistungDao;

	@InjectMocks
	private HilfeleistungServiceImpl service;

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

		reset(HilfeleistungDao);

		Long id = new Long(222);
		Hilfeleistung t = new Hilfeleistung();
		t.setAngebotsid(id);
		when(HilfeleistungDao.findById(id)).thenReturn(t);

		Assert.assertEquals(t, service.findById(id));

		verify(HilfeleistungDao, times(1)).findById(id);
	}

	@Test
	public void testFindAll() {

		reset(HilfeleistungDao);

		List<Hilfeleistung> alle = new ArrayList<Hilfeleistung>();

		Long id = new Long(222);
		Hilfeleistung a = new Hilfeleistung();
		a.setAngebotsid(id);
		alle.add(0, a);

		when(HilfeleistungDao.findAll()).thenReturn(alle);

		Assert.assertEquals(alle, service.findAll());

		verify(HilfeleistungDao, times(1)).findAll();

	}

	@Test
	public void testFindAllByBenutzer() throws ParseException {

		reset(HilfeleistungDao);

		Benutzer testUser = new Benutzer();
		testUser.setId(new Long(111));

		List<Hilfeleistung> alle = new ArrayList<Hilfeleistung>();

		Long id = new Long(222);
		Hilfeleistung a = new Hilfeleistung();
		a.setAngebotsid(id);
		a.setBenutzer(testUser);
		a.setBeschreibung("Test");
		a.setStartDatum(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));
		a.setEndDatum(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));
		alle.add(0, a);

		when(HilfeleistungDao.findAllByBenutzer(testUser)).thenReturn(alle);

		List<HilfeleistungDTO> list = service.findAllByBenutzer(testUser);

		Assert.assertEquals(a.getAngebotsid(), list.get(0).getId());

		verify(HilfeleistungDao, times(1)).findAllByBenutzer(testUser);

	}

	@Test
	public void testSave() {
		reset(HilfeleistungDao);

		Long id = new Long(222);
		Hilfeleistung a = new Hilfeleistung();
		a.setAngebotsid(id);

		when(HilfeleistungDao.save(a)).thenReturn(id);

		Assert.assertEquals(id, service.save(a));

		verify(HilfeleistungDao, times(1)).save(a);

	}

	@Test
	public void testUpdate() {
		reset(HilfeleistungDao);

		Long id = new Long(222);
		Hilfeleistung a = new Hilfeleistung();
		a.setAngebotsid(id);

		service.update(a);
		verify(HilfeleistungDao, times(1)).update(a);

	}

	@Test
	public void testDelete() {
		reset(HilfeleistungDao);

		Long id = new Long(222);
		Hilfeleistung a = new Hilfeleistung();
		a.setAngebotsid(id);

		service.delete(a);
		verify(HilfeleistungDao, times(1)).delete(a);
	}

	@Test
	public void testCreateHilfeleistung() {
		reset(HilfeleistungDao);

		Long id = new Long(222);
		HilfeleistungDTO a = new HilfeleistungDTO();
		a.setId(null);
		a.setBenutzer(new Benutzer());
		a.setBeschreibung("Test");
		a.setStartDatum("11.11.1999");
		a.setEndDatum("11.12.1999");

		when(HilfeleistungDao.save(any(Hilfeleistung.class))).thenReturn(id);

		Assert.assertEquals(id, service.createAngebot(a));

		verify(HilfeleistungDao, times(1)).save(any(Hilfeleistung.class));

	}

	@Test
	public void testHilfeleistungDTO_TO_Hilfeleistung() throws ParseException {
		Long id = new Long(222);
		Benutzer testUser = new Benutzer();

		HilfeleistungDTO dto = new HilfeleistungDTO();
		dto.setId(id);
		dto.setBenutzer(testUser);
		dto.setBeschreibung("Test");
		dto.setStartDatum("11.11.1999");
		dto.setEndDatum("01.01.2011");

		Hilfeleistung aReturn = new Hilfeleistung();
		aReturn.setAngebotsid(id);
		aReturn.setBenutzer(testUser);
		aReturn.setBeschreibung("Test");
		aReturn.setStartDatum(new SimpleDateFormat("dd-MM-yyyy")
				.parse("11-11-1999"));
		aReturn.setEndDatum(new SimpleDateFormat("yyyy-MM-dd")
				.parse("2011-01-01"));

		when(HilfeleistungDao.findById(id)).thenReturn(aReturn);

		Hilfeleistung a = service.angebotDTO_TO_Angebot(dto);

		Assert.assertEquals(dto.getId(), a.getAngebotsid());
		Assert.assertEquals(dto.getBenutzer(), a.getBenutzer());
		Assert.assertEquals(dto.getBeschreibung(), a.getBeschreibung());
		Assert.assertEquals(dto.getStartDatum(), new SimpleDateFormat(
				"dd.MM.yyyy").format(a.getStartDatum()));
		Assert.assertEquals(dto.getEndDatum(), new SimpleDateFormat(
				"dd.MM.yyyy").format(a.getEndDatum()));

	}

	@Test
	public void testHilfeleistung_TO_HilfeleistungDTO() throws ParseException {
		Long id = new Long(222);
		Benutzer testUser = new Benutzer();

		Hilfeleistung a = new Hilfeleistung();
		a.setAngebotsid(id);
		a.setBenutzer(testUser);
		a.setBeschreibung("Test");
		a.setStartDatum(new SimpleDateFormat("dd-MM-yyyy").parse("11-11-1999"));
		a.setEndDatum(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));

		HilfeleistungDTO dto = service.angebot_TO_AngebotDTO(a);

		Assert.assertEquals(a.getAngebotsid(), dto.getId());
		Assert.assertEquals(a.getBenutzer(), dto.getBenutzer());
		Assert.assertEquals(a.getBeschreibung(), dto.getBeschreibung());
		Assert.assertEquals(
				new SimpleDateFormat("dd.MM.yyyy").format(a.getStartDatum()),
				dto.getStartDatum());
	}

	@Test
	public void testShutdown() {
		reset(HilfeleistungDao);

		service.shutdown();
		verify(HilfeleistungDao, times(1)).shutdown();

	}

}
