package pandha.swe.localsharing;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableBatchProcessing
@EnableCaching
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}
