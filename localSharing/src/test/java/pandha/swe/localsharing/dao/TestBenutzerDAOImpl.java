package pandha.swe.localsharing.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.dao.BenutzerDAOImpl;
import pandha.swe.localsharing.model.enums.Geschlecht;
import pandha.swe.localsharing.model.enums.Rollen;

public class TestBenutzerDAOImpl {

	@InjectMocks
	BenutzerDAOImpl dao;

	MockMvc mockMvc;
	Session mockedSession;

	@Mock
	private HibernateTemplate hibernateTemplate;

	@Mock
	private SessionFactory sessionFactory;

	private Benutzer benutzer;

	private Long benutzerId;
	Transaction mockedTransaction;

	private List<Benutzer> alle;
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

		benutzerId = new Long(42);
		benutzer = new Benutzer(benutzerId, "12345678", true, Geschlecht.MANN,
				"Peter", "Hans", "Erzbergerstraße", "123", 76137, "Karlsruhe",
				"unitTest@localsharing.de", "12345678", null);

		Set<BenutzerRolle> rollen = new HashSet<>();
		rollen.add(new BenutzerRolle(benutzerId, benutzer, Rollen.USER));
		benutzer.setBenutzerRolle(rollen);

		alle = new ArrayList<Benutzer>();

		alle.add(0, benutzer);

		mockedSession = Mockito.mock(Session.class);
		mockedTransaction = Mockito.mock(Transaction.class);
		mockedSQlQuery = Mockito.mock(SQLQuery.class);

		when(hibernateTemplate.getSessionFactory()).thenReturn(sessionFactory);
		when(sessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.createSQLQuery(any(String.class))).thenReturn(
				mockedSQlQuery);

		when(mockedSQlQuery.executeUpdate()).thenReturn(1);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		when(mockedSession.save(benutzer)).thenReturn(new Long(10));

		when(hibernateTemplate.get(Benutzer.class, benutzerId)).thenReturn(
				benutzer);

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

		Assert.assertEquals(benutzer, dao.findById(benutzerId));

		verify(hibernateTemplate, times(1)).get(Benutzer.class, benutzerId);

	}

	@Test
	public void testFindAll() {

		resetAndInitAll();

		when(hibernateTemplate.loadAll(Benutzer.class)).thenReturn(alle);

		Assert.assertEquals(alle, dao.findAll());

	}

	private void resetAndInitAll() {
		resetAll();
		initAll();
	}

	@Test
	public void testSave() {

		resetAndInitAll();

		dao.save(benutzer);
		verify(mockedSession, times(1)).saveOrUpdate(benutzer);
		verifyOpenAndClosedDBConnection();
	}

	@Test
	public void testSaveNull() {

		resetAndInitAll();

		dao.save(null);
		verify(mockedSession, times(0)).save(benutzer);
		verifyNotOpenAndNotClosedDBConnection();
	}

	@Test
	public void testUpdate() {

		resetAndInitAll();

		dao.update(benutzer);
		verify(mockedSession, times(1)).saveOrUpdate(benutzer);
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

		dao.delete(benutzer);
		verify(mockedSession, times(1)).delete(benutzer);
		verifyOpenAndClosedDBConnection();
	}

	@Test
	public void testDeleteNull() {

		resetAndInitAll();

		dao.delete(null);
		verify(mockedSession, times(0)).delete(benutzer);
		verifyNotOpenAndNotClosedDBConnection();
	}

	@Test
	public void testShutdown() {

		resetAndInitAll();

		dao.shutdown();

		verify(mockedSQlQuery, times(1)).executeUpdate();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFindByEmail() {

		resetAndInitAll();

		List allBenutzer = alle;

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(allBenutzer);

		Assert.assertEquals(benutzer, dao.findByEmail(benutzer.getEmail()));

		verify(hibernateTemplate, times(1)).findByCriteria(
				any(DetachedCriteria.class));

	}

	@Test
	public void testFindByEmailNull() {

		resetAndInitAll();

		Assert.assertNull(dao.findByEmail(null));

		verify(hibernateTemplate, times(0)).findByCriteria(
				any(DetachedCriteria.class));

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFindByEmailListEmpty() {

		resetAndInitAll();

		resetAndInitAll();

		List allBenutzer = new ArrayList<>();

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(allBenutzer);

		Assert.assertNull(dao.findByEmail(benutzer.getEmail()));

		verify(hibernateTemplate, times(1)).findByCriteria(
				any(DetachedCriteria.class));

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFindByEmailListWrongObject() {

		resetAndInitAll();

		resetAndInitAll();
		List allBenutzer = new ArrayList<>();
		allBenutzer.add(0, new Long(12));

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(allBenutzer);

		Assert.assertNull(dao.findByEmail(benutzer.getEmail()));

		verify(hibernateTemplate, times(1)).findByCriteria(
				any(DetachedCriteria.class));

	}

}
