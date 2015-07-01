package pandha.swe.localsharing.controller;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.enums.FileUploadType;
import pandha.swe.localsharing.service.FileService;

public class FileControllerTest {

	@InjectMocks
	FileController controller;

	@Mock
	FileService fileService;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setViewResolvers(viewResolver).build();

	}

	@Test
	public void testGetFileUserExists() throws Exception {

		reset(fileService);

		FileUpload f = new FileUpload();
		f.setFile("12345678".getBytes());

		when(fileService.findByAssociated(new Long(234), FileUploadType.USER))
				.thenReturn(f);

		mockMvc.perform(get("/images/user/234/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(234),
				FileUploadType.USER);
		verify(fileService, times(0)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_USER);
	}

	@Test
	public void testGetFileUserNotExists() throws Exception {

		reset(fileService);

		FileUpload f = new FileUpload();
		f.setFile("default".getBytes());

		when(fileService.findByAssociated(new Long(234), FileUploadType.USER))
				.thenReturn(null);

		when(
				fileService.findByAssociated(new Long(1),
						FileUploadType.DEFAULT_USER)).thenReturn(f);

		mockMvc.perform(get("/images/user/234/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(234),
				FileUploadType.USER);
		verify(fileService, times(1)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_USER);
	}

	@Test
	public void testGetFileHilfangebotImageExists() throws Exception {

		reset(fileService);

		FileUpload f = new FileUpload();
		f.setFile("12345678".getBytes());

		when(
				fileService.findByAssociated(new Long(111),
						FileUploadType.HILFANGEBOT)).thenReturn(f);

		mockMvc.perform(get("/images/hilfsangebot/111/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(111),
				FileUploadType.HILFANGEBOT);
		verify(fileService, times(0)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);

	}

	@Test
	public void testGetFileHilfangebotImageNotExists() throws Exception {

		reset(fileService);

		when(
				fileService.findByAssociated(new Long(111),
						FileUploadType.HILFANGEBOT)).thenReturn(null);

		FileUpload f = new FileUpload();
		f.setFile("12345678".getBytes());

		when(
				fileService.findByAssociated(new Long(1),
						FileUploadType.DEFAULT_ANGEBOT)).thenReturn(f);

		mockMvc.perform(get("/images/hilfsangebot/111/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(111),
				FileUploadType.HILFANGEBOT);
		verify(fileService, times(1)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);

	}

	@Test
	public void testGetFileAusleihangebotImageExists() throws Exception {

		reset(fileService);

		FileUpload f = new FileUpload();
		f.setFile("12345678".getBytes());

		when(
				fileService.findByAssociated(new Long(111),
						FileUploadType.AUSLEIHANGEBOT)).thenReturn(f);

		mockMvc.perform(get("/images/ausleihangebot/111/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(111),
				FileUploadType.AUSLEIHANGEBOT);
		verify(fileService, times(0)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);

	}

	@Test
	public void testGetFileAusleihangebotImageNotExists() throws Exception {

		reset(fileService);

		when(
				fileService.findByAssociated(new Long(111),
						FileUploadType.AUSLEIHANGEBOT)).thenReturn(null);

		FileUpload f = new FileUpload();
		f.setFile("12345678".getBytes());

		when(
				fileService.findByAssociated(new Long(1),
						FileUploadType.DEFAULT_ANGEBOT)).thenReturn(f);

		mockMvc.perform(get("/images/ausleihangebot/111/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(111),
				FileUploadType.AUSLEIHANGEBOT);
		verify(fileService, times(1)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);
	}

	@Test
	public void testGetFileTauschangebotImageExists() throws Exception {

		reset(fileService);

		FileUpload f = new FileUpload();
		f.setFile("12345678".getBytes());

		when(
				fileService.findByAssociated(new Long(111),
						FileUploadType.TAUSCHANGEBOT)).thenReturn(f);

		mockMvc.perform(get("/images/tauschangebot/111/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(111),
				FileUploadType.TAUSCHANGEBOT);
		verify(fileService, times(0)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);

	}

	@Test
	public void testGetFileTauschangebotImageNotExists() throws Exception {

		reset(fileService);

		when(
				fileService.findByAssociated(new Long(111),
						FileUploadType.TAUSCHANGEBOT)).thenReturn(null);

		FileUpload f = new FileUpload();
		f.setFile("12345678".getBytes());

		when(
				fileService.findByAssociated(new Long(1),
						FileUploadType.DEFAULT_ANGEBOT)).thenReturn(f);

		mockMvc.perform(get("/images/tauschangebot/111/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(111),
				FileUploadType.TAUSCHANGEBOT);
		verify(fileService, times(1)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);
	}

	@Test
	public void testGetFileAangebotDefaultImageExists() throws Exception {

		reset(fileService);

		FileUpload f = new FileUpload();
		f.setFile("default".getBytes());

		when(
				fileService.findByAssociated(new Long(1),
						FileUploadType.DEFAULT_ANGEBOT)).thenReturn(f);

		mockMvc.perform(get("/images/angebot/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(f.getFile()));

		verify(fileService, times(1)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);

	}

	@Test
	public void testGetFileAangebotDefaultImageNotExists() throws Exception {
		reset(fileService);

		FileUpload f = new FileUpload();
		f.setFile("default".getBytes());

		when(
				fileService.findByAssociated(new Long(1),
						FileUploadType.DEFAULT_ANGEBOT)).thenReturn(null);

		byte[] testImage = Files.readAllBytes(new ClassPathResource(
				"static/images/angebot_default.jpg").getFile().toPath());

		mockMvc.perform(get("/images/angebot/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andExpect(content().bytes(testImage));

		verify(fileService, times(1)).findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);

	}
}
