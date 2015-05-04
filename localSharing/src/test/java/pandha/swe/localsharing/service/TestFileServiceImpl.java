package pandha.swe.localsharing.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dao.FileUploadDAO;
import pandha.swe.localsharing.model.enums.FileUploadType;

public class TestFileServiceImpl {

	@Mock
	private FileUploadDAO fileDao;

	@InjectMocks
	private FileServiceImpl service;

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
	public void testFindByAssociatedBenutzer() {

		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(id, FileUploadType.USER)).thenReturn(
				upload);

		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.USER));

		verify(fileDao, times(1)).findByAssociated(id, FileUploadType.USER);
	}

	@Test
	public void testFindAll() {

		reset(fileDao);

		List<FileUpload> files = new ArrayList<FileUpload>();

		when(fileDao.findAll()).thenReturn(files);

		Assert.assertEquals(files, service.findAll());

		verify(fileDao, times(1)).findAll();

	}

	@Test
	public void testFindAllOfType() {

		reset(fileDao);

		List<FileUpload> files = new ArrayList<FileUpload>();

		when(fileDao.findAllOfType(any(FileUploadType.class)))
				.thenReturn(files);

		Assert.assertEquals(files,
				service.findAllOfType(FileUploadType.AUSLEIHANGEBOT));
		Assert.assertEquals(files,
				service.findAllOfType(FileUploadType.DEFAULT_ANGEBOT));
		Assert.assertEquals(files,
				service.findAllOfType(FileUploadType.DEFAULT_USER));
		Assert.assertEquals(files,
				service.findAllOfType(FileUploadType.HILFANGEBOT));
		Assert.assertEquals(files,
				service.findAllOfType(FileUploadType.TAUSCHANGEBOT));
		Assert.assertEquals(files, service.findAllOfType(FileUploadType.USER));

		verify(fileDao, times(6)).findAllOfType(any(FileUploadType.class));

	}

	@Test
	public void testSaveFileUpload() {

		reset(fileDao);

		FileUpload file = new FileUpload();
		service.save(file);

		verify(fileDao, times(1)).save(file);

	}

	@Test
	public void testDelete() {
		reset(fileDao);

		FileUpload file = new FileUpload();
		service.delete(file);

		verify(fileDao, times(1)).delete(file);
	}

	@Test
	public void testSaveBenutzerMultipartFile() {

		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(id, FileUploadType.USER)).thenReturn(
				upload);

		Benutzer benutzer = new Benutzer();
		benutzer.setId(id);

		String name = "file.txt";
		String originalFileName = "file.txt";
		String contentType = "text/plain";
		byte[] content = "test".getBytes();

		MultipartFile file = new MockMultipartFile(name, originalFileName,
				contentType, content);

		service.save(benutzer, file);

		verify(fileDao, times(1)).findByAssociated(id, FileUploadType.USER);
		verify(fileDao, times(1)).save(upload);

	}

	@Test
	public void testSaveBenutzerMultipartFileUploadNull() {

		reset(fileDao);

		Long id = new Long(222);

		when(fileDao.findByAssociated(id, FileUploadType.USER))
				.thenReturn(null);

		Benutzer benutzer = new Benutzer();
		benutzer.setId(id);

		String name = "file.txt";
		String originalFileName = "file.txt";
		String contentType = "text/plain";
		byte[] content = "test".getBytes();

		MultipartFile file = new MockMultipartFile(name, originalFileName,
				contentType, content);

		service.save(benutzer, file);

		verify(fileDao, times(1)).findByAssociated(id, FileUploadType.USER);
		verify(fileDao, times(1)).save(any(FileUpload.class));

	}

	@Test
	public void testFindByAssociatedLongFileUploadType() {

		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(eq(id), any(FileUploadType.class)))
				.thenReturn(upload);

		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.USER));
		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.AUSLEIHANGEBOT));
		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.DEFAULT_ANGEBOT));
		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.DEFAULT_USER));
		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.HILFANGEBOT));
		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.TAUSCHANGEBOT));

		verify(fileDao, times(6)).findByAssociated(eq(id),
				any(FileUploadType.class));

	}

	@Test
	public void testSaveAusleihartikelMultipartFile() {
		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(id, FileUploadType.AUSLEIHANGEBOT))
				.thenReturn(upload);

		Ausleihartikel ausleih = new Ausleihartikel();
		ausleih.setAngebotsid(id);

		String name = "file.txt";
		String originalFileName = "file.txt";
		String contentType = "text/plain";
		byte[] content = "test".getBytes();

		MultipartFile file = new MockMultipartFile(name, originalFileName,
				contentType, content);

		service.save(ausleih, file);

		verify(fileDao, times(1)).findByAssociated(id,
				FileUploadType.AUSLEIHANGEBOT);
		verify(fileDao, times(1)).save(upload);
	}

	@Test
	public void testSaveTauschartikelMultipartFile() {
		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(id, FileUploadType.TAUSCHANGEBOT))
				.thenReturn(upload);

		Tauschartikel tausch = new Tauschartikel();
		tausch.setAngebotsid(id);

		String name = "file.txt";
		String originalFileName = "file.txt";
		String contentType = "text/plain";
		byte[] content = "test".getBytes();

		MultipartFile file = new MockMultipartFile(name, originalFileName,
				contentType, content);

		service.save(tausch, file);

		verify(fileDao, times(1)).findByAssociated(id,
				FileUploadType.TAUSCHANGEBOT);
		verify(fileDao, times(1)).save(upload);

	}

	@Test
	public void testSaveHilfeleistungMultipartFile() {
		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(id, FileUploadType.HILFANGEBOT))
				.thenReturn(upload);

		Hilfeleistung hilfe = new Hilfeleistung();
		hilfe.setAngebotsid(id);

		String name = "file.txt";
		String originalFileName = "file.txt";
		String contentType = "text/plain";
		byte[] content = "test".getBytes();

		MultipartFile file = new MockMultipartFile(name, originalFileName,
				contentType, content);

		service.save(hilfe, file);

		verify(fileDao, times(1)).findByAssociated(id,
				FileUploadType.HILFANGEBOT);
		verify(fileDao, times(1)).save(upload);
	}

	@Test
	public void testFindByAssociatedAusleihartikel() {

		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(id, FileUploadType.AUSLEIHANGEBOT))
				.thenReturn(upload);

		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.AUSLEIHANGEBOT));

		verify(fileDao, times(1)).findByAssociated(id,
				FileUploadType.AUSLEIHANGEBOT);

	}

	@Test
	public void testFindByAssociatedTauschartikel() {
		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(id, FileUploadType.TAUSCHANGEBOT))
				.thenReturn(upload);

		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.TAUSCHANGEBOT));

		verify(fileDao, times(1)).findByAssociated(id,
				FileUploadType.TAUSCHANGEBOT);

	}

	@Test
	public void testFindByAssociatedHilfeleistung() {
		reset(fileDao);

		Long id = new Long(222);

		FileUpload upload = new FileUpload();

		when(fileDao.findByAssociated(id, FileUploadType.HILFANGEBOT))
				.thenReturn(upload);

		Assert.assertEquals(upload,
				service.findByAssociated(id, FileUploadType.HILFANGEBOT));

		verify(fileDao, times(1)).findByAssociated(id,
				FileUploadType.HILFANGEBOT);

	}

}
