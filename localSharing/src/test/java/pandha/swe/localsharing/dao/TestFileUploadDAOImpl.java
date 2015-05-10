package pandha.swe.localsharing.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.dao.FileUploadDAOImpl;
import pandha.swe.localsharing.model.enums.FileUploadType;
import pandha.swe.localsharing.model.enums.Geschlecht;

public class TestFileUploadDAOImpl {

	@InjectMocks
	FileUploadDAOImpl dao;

	MockMvc mockMvc;
	Session mockedSession;

	@Mock
	private HibernateTemplate hibernateTemplate;

	@Mock
	private SessionFactory sessionFactory;

	private Benutzer benutzer;

	private FileUpload fileUpload;

	private Long fileUploadId;
	Transaction mockedTransaction;

	private List<FileUpload> alle;
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

		alle = new ArrayList<FileUpload>();

		fileUploadId = new Long(222);
		fileUpload = new FileUpload();
		fileUpload.setFileID(fileUploadId);
		fileUpload.setAssID(benutzer.getId());
		fileUpload.setFileUploadType(FileUploadType.USER);
		alle.add(0, fileUpload);

		mockedSession = Mockito.mock(Session.class);
		mockedTransaction = Mockito.mock(Transaction.class);
		mockedSQlQuery = Mockito.mock(SQLQuery.class);

		when(hibernateTemplate.getSessionFactory()).thenReturn(sessionFactory);
		when(sessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.createSQLQuery(any(String.class))).thenReturn(
				mockedSQlQuery);

		when(mockedSQlQuery.executeUpdate()).thenReturn(1);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		when(mockedSession.save(fileUpload)).thenReturn(new Long(10));

		when(hibernateTemplate.get(FileUpload.class, fileUploadId)).thenReturn(
				fileUpload);

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
	public void testFindAll() {

		resetAndInitAll();

		when(hibernateTemplate.loadAll(FileUpload.class)).thenReturn(alle);

		Assert.assertTrue(dao.findAll().contains(fileUpload));

	}

	@Test
	public void testFindAllNull() {

		resetAndInitAll();

		when(hibernateTemplate.loadAll(FileUpload.class)).thenReturn(null);

		Assert.assertTrue(dao.findAll().isEmpty());

	}

	@Test
	public void testFindAllIsEmpty() {

		resetAndInitAll();

		when(hibernateTemplate.loadAll(FileUpload.class)).thenReturn(
				new ArrayList<>());

		Assert.assertTrue(dao.findAll().isEmpty());

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFindAllWrongObject() {

		resetAndInitAll();

		List list = new ArrayList<>();
		list.add(0, new Long(12));

		when(hibernateTemplate.loadAll(FileUpload.class)).thenReturn(list);

		Assert.assertTrue(dao.findAll().isEmpty());

	}

	private void resetAndInitAll() {
		resetAll();
		initAll();
	}

	@Test
	public void testSave() {

		resetAndInitAll();

		dao.save(fileUpload);
		verify(mockedSession, times(1)).saveOrUpdate(fileUpload);
		verifyOpenAndClosedDBConnection();
	}

	@Test
	public void testSaveNull() {

		resetAndInitAll();

		dao.save(null);
		verify(mockedSession, times(0)).saveOrUpdate(fileUpload);
		verifyNotOpenAndNotClosedDBConnection();
	}

	@Test
	public void testDelete() {

		resetAndInitAll();

		dao.delete(fileUpload);
		verify(mockedSession, times(1)).delete(fileUpload);
		verifyOpenAndClosedDBConnection();
	}

	@Test
	public void testDeleteNull() {

		resetAndInitAll();

		dao.delete(null);
		verify(mockedSession, times(0)).delete(fileUpload);
		verifyNotOpenAndNotClosedDBConnection();
	}

	@Test
	public void testShutdown() {

		resetAndInitAll();

		dao.shutdown();

		verify(mockedSQlQuery, times(1)).executeUpdate();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testFindByAssociated() {

		resetAndInitAll();

		List list = alle;

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(list);

		Assert.assertEquals(
				fileUpload,
				dao.findByAssociated(fileUpload.getAssID(),
						fileUpload.getFileUploadType()));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testFindByAssociatedNull() {

		resetAndInitAll();

		List list = alle;

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(list);

		Assert.assertNull(dao.findByAssociated(null,
				fileUpload.getFileUploadType()));

		resetAndInitAll();

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(list);

		Assert.assertNull(dao.findByAssociated(fileUpload.getAssID(), null));

		resetAndInitAll();

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(null);

		Assert.assertNull(dao.findByAssociated(fileUpload.getAssID(),
				fileUpload.getFileUploadType()));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testFindByAssociatedEmptyList() {

		resetAndInitAll();

		List list = new ArrayList<>();

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(list);

		Assert.assertNull(dao.findByAssociated(fileUpload.getAssID(),
				fileUpload.getFileUploadType()));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testFindAllofType() {

		resetAndInitAll();

		List list = alle;

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(list);

		Assert.assertTrue(dao.findAllOfType(fileUpload.getFileUploadType())
				.contains(fileUpload));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testFindAllofTypeNull() {

		resetAndInitAll();

		List list = alle;

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(list);

		Assert.assertNull(dao.findAllOfType(null));

		resetAndInitAll();

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(null);

		Assert.assertNull(dao.findAllOfType(fileUpload.getFileUploadType()));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testFindAllofTypeEmptyList() {

		resetAndInitAll();

		List list = new ArrayList();

		when(hibernateTemplate.findByCriteria(any(DetachedCriteria.class)))
				.thenReturn(list);

		Assert.assertNull(dao.findAllOfType(fileUpload.getFileUploadType()));
	}
}
