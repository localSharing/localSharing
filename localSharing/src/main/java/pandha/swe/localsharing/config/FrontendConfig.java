package pandha.swe.localsharing.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import pandha.swe.localsharing.StringConstants;
import pandha.swe.localsharing.controller.pattern.backend.CreateArtikelInService;
import pandha.swe.localsharing.controller.pattern.backend.DtoToModelUmwander;
import pandha.swe.localsharing.controller.pattern.backend.ErlaubeAnfrageVonAdmin;
import pandha.swe.localsharing.controller.pattern.backend.ErlaubeAnfrageVonBesitzer;
import pandha.swe.localsharing.controller.pattern.backend.UpdateArtikelInService;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeAlleAngeboteEinesBenutzers;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeAlleDeaktiviertenAngebote;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeAlleEigenenAngebote;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeEinAngebot;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeEinAngebotDTO;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.NeuesAngebot;
import pandha.swe.localsharing.controller.pattern.backend.speichereDaten.B_AktiviereEinAngebot;
import pandha.swe.localsharing.controller.pattern.backend.speichereDaten.B_DeaktiviereEinAngebot;
import pandha.swe.localsharing.controller.pattern.backend.speichereDaten.LoescheEinAngebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dao.AngebotsDAO;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.LS_AngebotService;
import pandha.swe.localsharing.service.TauschartikelService;

@EnableAutoConfiguration
@Configuration
public class FrontendConfig extends WebMvcAutoConfigurationAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("homePage");
		registry.addViewController("/homepage").setViewName("homePage");
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("128KB");
		factory.setMaxRequestSize("128KB");
		return factory.createMultipartConfig();
	}

	@Bean
	public LadeAlleEigenenAngebote ladeAlleEigenenAngebote() {
		return new LadeAlleEigenenAngebote();
	}

	@Bean
	public LadeAlleAngeboteEinesBenutzers ladeAlleAngeboteEinesBenutzers() {
		return new LadeAlleAngeboteEinesBenutzers();
	}

	@Bean
	public B_DeaktiviereEinAngebot bDeaktiviereEinAngebot() {
		return new B_DeaktiviereEinAngebot();
	}

	@Bean
	public B_AktiviereEinAngebot bAktiviereEinAngebot() {
		return new B_AktiviereEinAngebot();
	}

	@Bean
	public LoescheEinAngebot loeescheEinAngebot() {
		return new LoescheEinAngebot();
	}

	@Bean
	public UpdateArtikelInService<Hilfeleistung> updateArtikelInServiceHilfeleistung() {
		return new UpdateArtikelInService<Hilfeleistung>();
	}

	@Bean
	public UpdateArtikelInService<Ausleihartikel> updateArtikelInServiceAusleihartikel() {
		return new UpdateArtikelInService<Ausleihartikel>();
	}

	@Bean
	public UpdateArtikelInService<Tauschartikel> updateArtikelInServiceTauschartikel() {
		return new UpdateArtikelInService<Tauschartikel>();
	}

	@Bean
	public NeuesAngebot neuesAngebot() {
		return new NeuesAngebot();
	}

	@Bean
	public LadeEinAngebotDTO ladeEinAngebotDTO() {
		return new LadeEinAngebotDTO();
	}

	@Bean
	public LadeEinAngebot ladeEinAngebot() {
		return new LadeEinAngebot();
	}

	@Bean
	public LadeAlleDeaktiviertenAngebote ladeAlleDeaktiviertenAngebote() {
		return new LadeAlleDeaktiviertenAngebote();
	}

	@Bean
	public CreateArtikelInService<AusleihartikelDTO> createArtikelInServiceAusleihartikelDTO() {
		return new CreateArtikelInService<AusleihartikelDTO>();
	}

	@Bean
	public CreateArtikelInService<TauschartikelDTO> createArtikelInServiceTauschartikelDTO() {
		return new CreateArtikelInService<TauschartikelDTO>();
	}

	@Bean
	public CreateArtikelInService<HilfeleistungDTO> createArtikelInServiceAusleihartikel() {
		return new CreateArtikelInService<HilfeleistungDTO>();
	}

	@Bean
	public ErlaubeAnfrageVonAdmin erlaubeAnfrageVonAdmin() {
		return new ErlaubeAnfrageVonAdmin();
	}

	@Bean
	public ErlaubeAnfrageVonBesitzer erlaubeAnfrageVonBesitzer() {
		return new ErlaubeAnfrageVonBesitzer();
	}

	@Bean
	public DtoToModelUmwander<AusleihartikelDTO, Ausleihartikel> dtoToModelUmwanderAusleih() {
		return new DtoToModelUmwander<AusleihartikelDTO, Ausleihartikel>();
	}

	@Bean
	public DtoToModelUmwander<TauschartikelDTO, Tauschartikel> dtoToModelUmwanderTausch() {
		return new DtoToModelUmwander<TauschartikelDTO, Tauschartikel>();
	}

	@Bean
	public DtoToModelUmwander<HilfeleistungDTO, Hilfeleistung> dtoToModelUmwanderHilfe() {
		return new DtoToModelUmwander<HilfeleistungDTO, Hilfeleistung>();
	}

	@Autowired
	private AusleihartikelDAO ausleihartikelDao;

	@Autowired
	private TauschartikelDAO tauschartikelDao;

	@Autowired
	private HilfeleistungDAO hilfeleistungDao;

	@Bean
	public HashMap<String, AngebotsDAO<?>> getAngebotDAOs() {
		HashMap<String, AngebotsDAO<?>> angebotDAOs = new HashMap<String, AngebotsDAO<?>>();
		angebotDAOs.put(StringConstants.AUSLEIHEN, ausleihartikelDao);
		angebotDAOs.put(StringConstants.TAUSCHEN, tauschartikelDao);
		angebotDAOs.put(StringConstants.HELFEN, hilfeleistungDao);

		System.out.println(angebotDAOs);

		return angebotDAOs;

	}

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	@Bean
	public Map<String, LS_AngebotService<?, ?>> getAngebotServices() {
		Map<String, LS_AngebotService<?, ?>> angebotServices = new HashMap<String, LS_AngebotService<?, ?>>();
		angebotServices.put(StringConstants.AUSLEIHEN, ausleihartikelService);
		angebotServices.put(StringConstants.TAUSCHEN, tauschartikelService);
		angebotServices.put(StringConstants.HELFEN, hilfeleistungService);
		return angebotServices;

	}

}
