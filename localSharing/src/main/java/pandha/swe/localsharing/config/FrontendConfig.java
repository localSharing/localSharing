package pandha.swe.localsharing.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import pandha.swe.localsharing.controller.pattern.backend.CreateArtikelInService;
import pandha.swe.localsharing.controller.pattern.backend.ErlaubeAnfrageVonAdmin;
import pandha.swe.localsharing.controller.pattern.backend.ErlaubeAnfrageVonBesitzer;
import pandha.swe.localsharing.controller.pattern.backend.ErlaubeAnfrageVonBesitzerUndAdmin;
import pandha.swe.localsharing.controller.pattern.backend.UpdateArtikelInService;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeAlleAngeboteEinesBenutzers;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeAlleDeaktiviertenAngebote;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeAlleEigenenAngebote;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeEinAngebot;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeEinAngebotDTO;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.NeuesAngebot;
import pandha.swe.localsharing.controller.pattern.backend.speichereDaten.B_AktiviereEinAngebot;
import pandha.swe.localsharing.controller.pattern.backend.speichereDaten.B_DeaktiviereEinAngebot;
import pandha.swe.localsharing.controller.pattern.backend.speichereDaten.LoescheEinAngebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

@EnableAutoConfiguration
@Configuration
public class FrontendConfig extends WebMvcAutoConfigurationAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("homePage");
		registry.addViewController("/homepage").setViewName("homePage");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations(
					"classpath:/META-INF/resources/webjars/");
		}
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
	public ErlaubeAnfrageVonBesitzerUndAdmin erlaubeAnfrageVonBesitzerUndAdmin() {
		return new ErlaubeAnfrageVonBesitzerUndAdmin();
	}

	@Bean
	public ErlaubeAnfrageVonAdmin erlaubeAnfrageVonAdmin() {
		return new ErlaubeAnfrageVonAdmin();
	}

	@Bean
	public ErlaubeAnfrageVonBesitzer erlaubeAnfrageVonBesitzer() {
		return new ErlaubeAnfrageVonBesitzer();
	}

}
