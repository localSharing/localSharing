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

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dao.AusleihartikelDAOImpl;
import pandha.swe.localsharing.model.enums.Geschlecht;

public class TestAusleihartikelDAOImpl {

	@InjectMocks
	AusleihartikelDAOImpl dao;

	MockMvc mockMvc;
	Session mockedSession;

	@Mock
	private HibernateTemplate hibernateTemplate;

	@Mock
	private SessionFactory sessionFactory;

	private Benutzer benutzer;

	private Ausleihartikel ausleihartikel;

	private Long artikelId;
	Transaction mockedTransaction;

	private List<Ausleihartikel> alle;
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

		alle = new ArrayList<Ausleihartikel>();

		artikelId = new Long(222);
		ausleihartikel = new Ausleihartikel();
		ausleihartikel.setAngebotsid(artikelId);
		ausleihartikel.setBenutzer(benutzer);
		ausleihartikel.setBeschreibung("Test");
		ausleihartikel.setDauer(3);
		ausleihartikel.setKategorie("Test");
		try {
			ausleihartikel.setStartDatum(new SimpleDateFormat("yyyy-MM-dd")
					.parse("2011-01-01"));
			ausleihartikel.setEndDatum(new SimpleDateFormat("yyyy-MM-dd")
					.parse("2011-01-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alle.add(0, ausleihartikel);

		mockedSession = Mockito.mock(Session.class);
		mockedTransaction = Mockito.mock(Transaction.class);
		mockedSQlQuery = Mockito.mock(SQLQuery.class);

		when(hibernateTemplate.getSessionFactory()).thenReturn(sessionFactory);
		when(sessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.createSQLQuery(any(String.class))).thenReturn(
				mockedSQlQuery);

		when(mockedSQlQuery.executeUpdate()).thenReturn(1);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		when(mockedSession.save(ausleihartikel)).thenReturn(new Long(10));

		when(hibernateTemplate.get(Ausleihartikel.class, artikelId))
				.thenReturn(ausleihartikel);

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

		Assert.assertEquals(ausleihartikel, dao.findById(artikelId));

		verify(hibernateTemplate, times(1))
				.get(Ausleihartikel.class, artikelId);

	}

	@Test
	public void testFindAll() {

		resetAndInitAll();

		when(hibernateTemplate.loadAll(Ausleihartikel.class)).thenReturn(alle);

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

		Assert.assertEquals(new Long(10), (Long) dao.save(ausleihartikel));
		verify(mockedSession, times(1)).save(ausleihartikel);
		verifyOpenAndClosedDBConnection();
	}

	@Test
	public void testSaveNull() {

		resetAndInitAll();

		Assert.assertNull(dao.save(null));
		verify(mockedSession, times(0)).save(ausleihartikel);
		verifyNotOpenAndNotClosedDBConnection();
	}

	@Test
	public void testUpdate() {

		resetAndInitAll();

		dao.update(ausleihartikel);
		verify(mockedSession, times(1)).saveOrUpdate(ausleihartikel);
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

		dao.delete(ausleihartikel);
		verify(mockedSession, times(1)).delete(ausleihartikel);
		verifyOpenAndClosedDBConnection();
	}

	@Test
	public void testDeleteNull() {

		resetAndInitAll();

		dao.delete(null);
		verify(mockedSession, times(0)).delete(ausleihartikel);
		verifyNotOpenAndNotClosedDBConnection();
	}

	@Test
	public void testShutdown() {

		resetAndInitAll();

		dao.shutdown();

		verify(mockedSQlQuery, times(1)).executeUpdate();

	}
}
