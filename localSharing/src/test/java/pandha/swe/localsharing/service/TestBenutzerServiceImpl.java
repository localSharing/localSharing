package pandha.swe.localsharing.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dao.AngebotsDAO;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dao.BenutzerDAO;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;
import pandha.swe.localsharing.model.dto.BenutzerDTO;
import pandha.swe.localsharing.model.dto.BenutzerRegisterDTO;
import pandha.swe.localsharing.model.enums.Geschlecht;
import pandha.swe.localsharing.model.enums.Rollen;

public class TestBenutzerServiceImpl {

	@Mock
	private BenutzerDAO benutzerDao;

	@InjectMocks
	private BenutzerServiceImpl service;

	@Mock
	private PasswordEncoder encoder;

	MockMvc mockMvc;

	@Mock
	private AusleihartikelDAO ausleihartikelDao;

	@Mock
	private TauschartikelDAO tauschartikelDao;

	@Mock
	private HilfeleistungDAO hilfeleistungDao;

	@Mock
	private HashMap<String, AngebotsDAO<?>> angebotDAOs;

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

		reset(benutzerDao);

		Long id = new Long(222);
		Benutzer a = new Benutzer();
		a.setId(id);
		when(benutzerDao.findById(id)).thenReturn(a);

		Assert.assertEquals(a, service.findById(id));

		verify(benutzerDao, times(1)).findById(id);
	}

	@Test
	public void testFindAll() {

		reset(benutzerDao);

		List<Benutzer> alle = new ArrayList<Benutzer>();

		Long id = new Long(222);
		Benutzer a = new Benutzer();
		a.setId(id);
		alle.add(0, a);

		when(benutzerDao.findAll()).thenReturn(alle);

		Assert.assertEquals(alle, service.findAll());

		verify(benutzerDao, times(1)).findAll();

	}

	@Test
	public void testFindAllByBenutzer() {
		reset(benutzerDao);

		Benutzer testUser = new Benutzer();
		testUser.setId(new Long(111));

		List<Benutzer> alle = new ArrayList<Benutzer>();

		Long id = new Long(222);
		Benutzer a = new Benutzer();
		a.setId(id);
		a.setBenutzerRolle(null);
		a.setEnabled(true);
		a.setEmail("testuser@localsharing.com");
		a.setGeschlecht(Geschlecht.FRAU);
		a.setHausnummer("34");
		a.setNachname("Grün");
		a.setVorname("Peter");
		a.setPlz(69115);
		a.setStrasse("Peterstraße");
		a.setStadt("Karlsruhe");
		a.setTelefonNr("123456");
		a.setPasswort("1345678");
		alle.add(0, a);

	}

	@Test
	public void testSave() {

		reset(benutzerDao);

		Long id = new Long(222);
		Benutzer a = new Benutzer();
		a.setId(id);

		service.save(a);

		verify(benutzerDao, times(1)).save(a);

	}

	@Test
	public void testUpdate() {

		reset(benutzerDao);

		Long id = new Long(222);
		Benutzer a = new Benutzer();
		a.setId(id);

		service.update(a);
		verify(benutzerDao, times(1)).update(a);

	}

	@Test
	public void testDelete() {

		reset(benutzerDao);

		Long id = new Long(222);
		Benutzer a = new Benutzer();
		a.setId(id);

		service.delete(a);
		verify(benutzerDao, times(1)).delete(a);

	}

	@Test
	public void testDTO_TO_Benutzer() throws ParseException {

		Long id = new Long(222);
		BenutzerDTO dto = new BenutzerDTO();

		dto.setId(id);
		dto.setEmail("testuser@localsharing.com");
		dto.setGeschlecht(Geschlecht.FRAU);
		dto.setHausnummer("34");
		dto.setNachname("Grün");
		dto.setVorname("Peter");
		dto.setPlz("69115");
		dto.setStrasse("Peterstraße");
		dto.setStadt("Karlsruhe");
		dto.setTelefonNummer("123456");

		Benutzer aReturn = new Benutzer();
		aReturn.setId(id);
		aReturn.setBenutzerRolle(null);
		aReturn.setEnabled(true);
		aReturn.setEmail("testuser@localsharing.com");
		aReturn.setGeschlecht(Geschlecht.FRAU);
		aReturn.setHausnummer("34");
		aReturn.setNachname("Grün");
		aReturn.setVorname("Peter");
		aReturn.setPlz(69115);
		aReturn.setStrasse("Peterstraße");
		aReturn.setStadt("Karlsruhe");
		aReturn.setTelefonNr("123456");
		aReturn.setPasswort("1345678");

		when(benutzerDao.findByEmail("testuser@localsharing.com")).thenReturn(
				aReturn);

		when(benutzerDao.findById(id)).thenReturn(aReturn);

		Benutzer a = service.benutzerDTO_TO_Benutzer(dto, new Principal() {

			@Override
			public String getName() {
				return "testuser@localsharing.com";
			}
		});

		Assert.assertEquals(dto.getId(), a.getId());
		Assert.assertEquals(dto.getEmail(), a.getEmail());
		Assert.assertEquals(dto.getHausnummer(), a.getHausnummer());
		Assert.assertEquals(dto.getPlz(), a.getPlz().toString());
		Assert.assertEquals(dto.getNachname(), a.getNachname());
		Assert.assertEquals(dto.getVorname(), a.getVorname());
		Assert.assertEquals(dto.getGeschlecht(), a.getGeschlecht());
		Assert.assertEquals(dto.getStadt(), a.getStadt());
		Assert.assertEquals(dto.getStrasse(), a.getStrasse());
	}

	@Test
	public void testBenutzer_TO_DTO() throws ParseException {

		Long id = new Long(222);

		Benutzer benutzer = new Benutzer();
		benutzer.setId(id);
		benutzer.setBenutzerRolle(null);
		benutzer.setEnabled(true);
		benutzer.setEmail("testuser@localsharing.com");
		benutzer.setGeschlecht(Geschlecht.FRAU);
		benutzer.setHausnummer("34");
		benutzer.setNachname("Grün");
		benutzer.setVorname("Peter");
		benutzer.setPlz(69115);
		benutzer.setStrasse("Peterstraße");
		benutzer.setStadt("Karlsruhe");
		benutzer.setTelefonNr("123456");
		benutzer.setPasswort("1345678");

		BenutzerDTO dtoReturn = new BenutzerDTO();

		dtoReturn.setId(id);
		dtoReturn.setEmail("testuser@localsharing.com");
		dtoReturn.setGeschlecht(Geschlecht.FRAU);
		dtoReturn.setHausnummer("34");
		dtoReturn.setNachname("Grün");
		dtoReturn.setVorname("Peter");
		dtoReturn.setPlz("69115");
		dtoReturn.setStrasse("Peterstraße");
		dtoReturn.setStadt("Karlsruhe");
		dtoReturn.setTelefonNummer("123456");

		when(benutzerDao.findById(id)).thenReturn(benutzer);

		BenutzerDTO dto = service.benutzer_TO_BenutzerDTO(benutzer);

		Assert.assertEquals(dto.getId(), dtoReturn.getId());
		Assert.assertEquals(dto.getEmail(), dtoReturn.getEmail());
		Assert.assertEquals(dto.getHausnummer(), dtoReturn.getHausnummer());
		Assert.assertEquals(dto.getPlz(), dtoReturn.getPlz());
		Assert.assertEquals(dto.getNachname(), dtoReturn.getNachname());
		Assert.assertEquals(dto.getVorname(), dtoReturn.getVorname());
		Assert.assertEquals(dto.getGeschlecht(), dtoReturn.getGeschlecht());
		Assert.assertEquals(dto.getStadt(), dtoReturn.getStadt());
		Assert.assertEquals(dto.getStrasse(), dtoReturn.getStrasse());
	}

	@Test
	public void testBenutzer_TO_DTO_PLZ() throws ParseException {

		Long id = new Long(222);

		Benutzer benutzer = new Benutzer();
		benutzer.setId(id);
		benutzer.setBenutzerRolle(null);
		benutzer.setEnabled(true);
		benutzer.setEmail("testuser@localsharing.com");
		benutzer.setGeschlecht(Geschlecht.FRAU);
		benutzer.setHausnummer("34");
		benutzer.setNachname("Grün");
		benutzer.setVorname("Peter");
		benutzer.setPlz(115);
		benutzer.setStrasse("Peterstraße");
		benutzer.setStadt("Karlsruhe");
		benutzer.setTelefonNr("123456");
		benutzer.setPasswort("1345678");

		BenutzerDTO dtoReturn = new BenutzerDTO();

		dtoReturn.setId(id);
		dtoReturn.setEmail("testuser@localsharing.com");
		dtoReturn.setGeschlecht(Geschlecht.FRAU);
		dtoReturn.setHausnummer("34");
		dtoReturn.setNachname("Grün");
		dtoReturn.setVorname("Peter");
		dtoReturn.setPlz("11500");
		dtoReturn.setStrasse("Peterstraße");
		dtoReturn.setStadt("Karlsruhe");
		dtoReturn.setTelefonNummer("123456");

		when(benutzerDao.findById(id)).thenReturn(benutzer);

		BenutzerDTO dto = service.benutzer_TO_BenutzerDTO(benutzer);

		Assert.assertEquals(dto.getId(), dtoReturn.getId());
		Assert.assertEquals(dto.getEmail(), dtoReturn.getEmail());
		Assert.assertEquals(dto.getHausnummer(), dtoReturn.getHausnummer());
		Assert.assertEquals(dto.getPlz(), dtoReturn.getPlz());
		Assert.assertEquals(dto.getNachname(), dtoReturn.getNachname());
		Assert.assertEquals(dto.getVorname(), dtoReturn.getVorname());
		Assert.assertEquals(dto.getGeschlecht(), dtoReturn.getGeschlecht());
		Assert.assertEquals(dto.getStadt(), dtoReturn.getStadt());
		Assert.assertEquals(dto.getStrasse(), dtoReturn.getStrasse());
	}

	@Test
	public void testRegisterBenutzer() {

		reset(benutzerDao);

		Long id = new Long(222);

		BenutzerRegisterDTO dto = new BenutzerRegisterDTO();

		dto.setId(id);
		dto.setEmail("testuser@localsharing.com");
		dto.setGeschlecht(Geschlecht.FRAU);
		dto.setHausnummer("34");
		dto.setNachname("Grün");
		dto.setVorname("Peter");
		dto.setPlz("69115");
		dto.setStrasse("Peterstraße");
		dto.setStadt("Karlsruhe");
		dto.setTelefonNummer("123456");
		dto.setPassword1("12345678");

		service.registerBenutzer(dto);

		verify(encoder, times(1)).encode(dto.getPassword1());
		verify(benutzerDao, times(1)).save(any(Benutzer.class));

	}

	@Test
	public void testShutdown() {

		reset(benutzerDao);

		service.shutdown();
		verify(benutzerDao, times(1)).shutdown();

	}

	@Test
	public void testFindByAngebotsIdAndType() {

		Long id = new Long(222);

		Benutzer benutzer = new Benutzer();
		benutzer.setId(id);
		benutzer.setBenutzerRolle(null);
		benutzer.setEnabled(true);
		benutzer.setEmail("testuser@localsharing.com");
		benutzer.setGeschlecht(Geschlecht.FRAU);
		benutzer.setHausnummer("34");
		benutzer.setNachname("Grün");
		benutzer.setVorname("Peter");
		benutzer.setPlz(115);
		benutzer.setStrasse("Peterstraße");
		benutzer.setStadt("Karlsruhe");
		benutzer.setTelefonNr("123456");
		benutzer.setPasswort("1345678");

		Ausleihartikel a = new Ausleihartikel();
		a.setBenutzer(benutzer);

		Tauschartikel t = new Tauschartikel();
		t.setBenutzer(benutzer);

		Hilfeleistung h = new Hilfeleistung();
		h.setBenutzer(benutzer);

		Mockito.<AngebotsDAO<? extends Angebot>> when(
				angebotDAOs.get("ausleihen")).thenReturn(ausleihartikelDao);
		Mockito.<AngebotsDAO<? extends Angebot>> when(
				angebotDAOs.get("tauschen")).thenReturn(tauschartikelDao);
		Mockito.<AngebotsDAO<? extends Angebot>> when(angebotDAOs.get("helfen"))
				.thenReturn(hilfeleistungDao);

		when(ausleihartikelDao.findById(id)).thenReturn(a);
		when(tauschartikelDao.findById(id)).thenReturn(t);
		when(hilfeleistungDao.findById(id)).thenReturn(h);

		Assert.assertEquals(benutzer,
				service.findByAngebotsIdAndType(id, "ausleihen"));
		Assert.assertEquals(benutzer,
				service.findByAngebotsIdAndType(id, "tauschen"));
		Assert.assertEquals(benutzer,
				service.findByAngebotsIdAndType(id, "helfen"));
		Assert.assertNull(service.findByAngebotsIdAndType(id, "13"));
	}

	@Test
	public void testHatBenutzerRolle() {
		Long id = new Long(222);

		Benutzer benutzer = new Benutzer();
		benutzer.setId(id);
		benutzer.setBenutzerRolle(null);
		benutzer.setEnabled(true);
		benutzer.setEmail("testuser@localsharing.com");
		benutzer.setGeschlecht(Geschlecht.FRAU);
		benutzer.setHausnummer("34");
		benutzer.setNachname("Grün");
		benutzer.setVorname("Peter");
		benutzer.setPlz(115);
		benutzer.setStrasse("Peterstraße");
		benutzer.setStadt("Karlsruhe");
		benutzer.setTelefonNr("123456");
		benutzer.setPasswort("1345678");

		Set<BenutzerRolle> rollen = new HashSet<>();
		rollen.add(new BenutzerRolle(id, benutzer, Rollen.USER));
		benutzer.setBenutzerRolle(rollen);

		Assert.assertTrue(service.hatBenutzerRolle(benutzer, Rollen.USER));
		Assert.assertFalse(service.hatBenutzerRolle(benutzer, Rollen.ADMIN));
		Assert.assertFalse(service.hatBenutzerRolle(null, Rollen.USER));
		Assert.assertFalse(service.hatBenutzerRolle(benutzer, null));

	}
}
