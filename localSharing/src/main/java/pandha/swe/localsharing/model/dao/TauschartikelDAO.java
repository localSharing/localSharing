package pandha.swe.localsharing.model.dao;

import java.util.List;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Tauschartikel;

public interface TauschartikelDAO {

	public Tauschartikel findById(Long id);

	public List<Tauschartikel> findAll();

	public List<Tauschartikel> findAllEnabled();

	public List<Tauschartikel> findAllDisabled();

	public List<Tauschartikel> findAllEnabledByBenutzer(Benutzer benutzer);
	
	public List<Tauschartikel> findAllByBenutzer(Benutzer benutzer);

	public Long save(Tauschartikel tauschartikel);

	public void update(Tauschartikel tauschartikel);

	public void delete(Tauschartikel tauschartikel);

	public void shutdown();

}
