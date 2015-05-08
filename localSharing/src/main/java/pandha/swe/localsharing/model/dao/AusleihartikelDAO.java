package pandha.swe.localsharing.model.dao;

import java.util.List;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;

public interface AusleihartikelDAO {

	public Ausleihartikel findById(Long id);

	public List<Ausleihartikel> findAll();
	
	public List<Ausleihartikel> findAllEnabled();

	public List<Ausleihartikel> findAllByBenutzer(Benutzer benutzer);

	public Long save(Ausleihartikel ausleihartikel);

	public void update(Ausleihartikel ausleihartikel);

	public void delete(Ausleihartikel ausleihartikel);

	public void shutdown();

}
