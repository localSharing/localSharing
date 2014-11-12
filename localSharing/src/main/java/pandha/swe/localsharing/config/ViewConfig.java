package pandha.swe.localsharing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ViewConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("homePage");
		registry.addViewController("/homepage").setViewName("homePage");
		// registry.addViewController("/login").setViewName("login");
	}

	// @Bean
	// public SpringTemplateEngine templateEngine() {
	// SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	//
	// templateEngine.addDialect(new LayoutDialect());
	// return templateEngine;
	// }
	//
	// @Bean
	// public ViewResolver viewResolver() {
	// ClassLoaderTemplateResolver templateResolver = new
	// ClassLoaderTemplateResolver();
	// templateResolver.setTemplateMode("XHTML");
	// templateResolver.setPrefix("../resources/templates/");
	// templateResolver.setSuffix(".html");
	//
	// SpringTemplateEngine engine = new SpringTemplateEngine();
	// engine.setTemplateResolver(templateResolver);
	//
	// ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	// viewResolver.setTemplateEngine(engine);
	// return viewResolver;
	// }
}
