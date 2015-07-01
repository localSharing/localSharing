package pandha.swe.localsharing.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dao.TauschartikelDAOImpl;
import pandha.swe.localsharing.model.enums.Geschlecht;

public class TestTauschartikelDAOImpl {

	@InjectMocks
	TauschartikelDAOImpl dao;

	MockMvc mockMvc;
	Session mockedSession;

	@Mock
	private HibernateTemplate hibernateTemplate;

	@Mock
	private SessionFactory sessionFactory;

	private Benutzer benutzer;

	private Tauschartikel tauschartikel;

	private Long artikelId;
	Transaction mockedTransaction;

	private List<Tauschartikel> alle;
	SQLQuery mockedSQlQuery;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(dao)
				.setViewResolvers(viewResolver).build();

	}

	private void initAll() {
		benutzer = new Benutzer(new Long(42), "12345678", true,
				Geschlecht.MANN, "Peter", "Hans", "Erzbergerstraße", "123",
				76137, "Karlsruhe", "unitTest@localsharing.de", "12345678",
				null);

		alle = new ArrayList<Tauschartikel>();

		artikelId = new Long(222);
		tauschartikel = new Tauschartikel();
		tauschartikel.setAngebotsid(artikelId);
		tauschartikel.setBenutzer(benutzer);
		tauschartikel.setBeschreibung("Test");
		tauschartikel.setKategorie("Test");
		try {
			tauschartikel.setStartDatum(new SimpleDateFormat("yyyy-MM-dd")
					.parse("2011-01-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alle.add(0, tauschartikel);

		mockedSession = Mockito.mock(Session.class);
		mockedTransaction = Mockito.mock(Transaction.class);
		mockedSQlQuery = Mockito.mock(SQLQuery.class);

		when(hibernateTemplate.getSessionFactory()).thenReturn(sessionFactory);
		when(sessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.createSQLQuery(any(String.class))).thenReturn(
				mockedSQlQuery);

		when(mockedSQlQuery.executeUpdate()).thenReturn(1);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		when(mockedSession.save(tauschartikel)).thenReturn(new Long(10));

		when(hibernateTemplate.get(Tauschartikel.class, artikelId)).thenReturn(
				tauschartikel);

	}

	private void resetAll() {
		reset(hibernateTemplate);
		reset(sessionFactory);
	}

	private void verifyOpenAndClosedDBConnection() {
		verify(mockedSession, times(1)).beginTransaction();
		verify(mockedTransaction, times(1)).commit();
		verify(mockedSession, times(1)).close();
	}

	private void verifyNotOpenAndNotClosedDBConnection() {
		verify(mockedSession, times(0)).beginTransaction();
		verify(mockedTransaction, times(0)).commit();
		verify(mockedSession, times(0)).close();
	}

	@Test
	public void testFindById() {
		resetAndInitAll();

		Assert.assertEquals(tauschartikel, dao.findById(artikelId));

		verify(hibernateTemplate, times(1)).get(Tauschartikel.class, artikelId);

	}

	@Test
	public void testFindAll() {

		resetAndInitAll();

		when(hibernateTemplate.loadAll(Tauschartikel.class)).thenReturn(alle);

		Assert.assertEquals(alle, dao.findAll());

	}

	private void resetAndInitAll() {
		resetAll();
		initAll();
	}

	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testFindAllByBenutzer() {

		resetAndInitAll();

		List alle2 = alle;

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(alle2);

		Assert.assertEquals(alle, dao.findAllByBenutzer(benutzer));

	}

	@Test
	public void testSave() {

		resetAndInitAll();

		Assert.assertEquals(new Long(10), (Long) dao.save(tauschartikel));
		verify(mockedSession, times(1)).save(tauschartikel);
		verifyOpenAndClosedDBConnection();
	}

	@Test
	public void testSaveNull() {

		resetAndInitAll();

		Assert.assertNull(dao.save(null));
		verify(mockedSession, times(0)).save(tauschartikel);
		verifyNotOpenAndNotClosedDBConnection();
	}

	@Test
	public void testUpdate() {

		resetAndInitAll();

		dao.update(tauschartikel);
		verify(mockedSession, times(1)).saveOrUpdate(tauschartikel);
		verifyOpenAndClosedDBConnection();

	}

	@Test
	public void testUpdateNull() {

		resetAndInitAll();

		dao.update(null);
		verify(mockedSession, times(0)).saveOrUpdate(null);
		verifyNotOpenAndNotClosedDBConnection();

	}

	@Test
	public void testDelete() {

		resetAndInitAll();

		dao.delete(tauschartikel);
		verify(mockedSession, times(1)).delete(tauschartikel);
		verifyOpenAndClosedDBConnection();
	}

	@Test
	public void testDeleteNull() {

		resetAndInitAll();

		dao.delete(null);
		verify(mockedSession, times(0)).delete(tauschartikel);
		verifyNotOpenAndNotClosedDBConnection();
	}

	@Test
	public void testShutdown() {

		resetAndInitAll();

		dao.shutdown();

		verify(mockedSQlQuery, times(1)).executeUpdate();

	}
}
