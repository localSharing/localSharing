package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.BenutzerRegisterDTO;

public interface BenutzerService {
	public Benutzer findById(long id);
	
	public Benutzer findByEmail(String email);

	public List<Benutzer> findAll();

	public void save(Benutzer benutzer);

	public void update(Benutzer benutzer);

	public void delete(Benutzer benutzer);
	
	public void registerBenutzer(BenutzerRegisterDTO benutzerRegisterDto);

	public void shutdown();
}
