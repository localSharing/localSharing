package pandha.swe.localsharing.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@EnableAutoConfiguration
@Configuration
public class ViewConfig extends WebMvcAutoConfigurationAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("homePage");
		registry.addViewController("/homepage").setViewName("homePage");
		// registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations(
					"classpath:/META-INF/resources/webjars/");
		}
	}

	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("/static/**").addResourceLocations(
	// "classpath:/WEB-INF/classes/resources/static/");
	//
	// registry.addResourceHandler("/webjars/**").addResourceLocations(
	// "classpath:/WEB-INF/resources/webjars/");
	//
	// registry.addResourceHandler("/public/**").addResourceLocations(
	// "classpath:/public/");
	//
	// }

}
