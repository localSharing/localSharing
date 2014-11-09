package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.dto.BenutzerRegisterDto;
import pandha.swe.localsharing.model.Benutzer;

public interface BenutzerService {
	public Benutzer findById(long id);

	public List<Benutzer> findAll();

	public void save(Benutzer benutzer);

	public void update(Benutzer benutzer);

	public void delete(Benutzer benutzer);
	
	public void registerBenzuter(BenutzerRegisterDto benutzerRegisterDto);

	public void shutdown();
}
