package pandha.swe.localsharing.dao;

import java.util.List;

import pandha.swe.localsharing.dto.BenutzerRegisterDto;
import pandha.swe.localsharing.model.Benutzer;

public interface BenutzerDao {
	Benutzer findById(Long id);

	public Benutzer findByEmail(String email);

	public List<Benutzer> findAll();

	public void save(Benutzer benutzer);

	public void update(Benutzer benutzer);

	public void delete(Benutzer benutzer);
	
	public void registerBenzuter(BenutzerRegisterDto benutzerRegisterDto);

	public void shutdown();

}
