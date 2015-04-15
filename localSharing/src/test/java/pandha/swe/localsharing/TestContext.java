package pandha.swe.localsharing;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pandha.swe.localsharing.service.BenutzerService;
import pandha.swe.localsharing.service.FileService;

@Configuration
public class TestContext {

	
	@Bean
    public BenutzerService benutzerService() {
        return Mockito.mock(BenutzerService.class);
    }
	
	@Bean
    public FileService fileService() {
        return Mockito.mock(FileService.class);
    }
}
