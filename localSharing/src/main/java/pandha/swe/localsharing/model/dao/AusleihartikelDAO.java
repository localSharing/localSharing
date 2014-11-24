package pandha.swe.localsharing.model.dao;

import java.util.List;

import pandha.swe.localsharing.model.Ausleihartikel;

public interface AusleihartikelDAO {
	
	public Ausleihartikel findById(Long id);

	public List<Ausleihartikel> findAll();

	public void save(Ausleihartikel ausleihartikel);

	public void update(Ausleihartikel ausleihartikel);

	public void delete(Ausleihartikel ausleihartikel);

	public void shutdown();

}
