package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;

public interface AusleihangebotService {

	public Ausleihartikel findById(Long id);

	public List<Ausleihartikel> findAll();

	public void save(Ausleihartikel ausleihartikel);

	public void update(Ausleihartikel ausleihartikel);

	public void delete(Ausleihartikel ausleihartikel);
	
	public void createAusleihartikel(AusleihartikelDTO ausleihartikelDTO);

	public Ausleihartikel ausleihartikelDTO_TO_Ausleihartikel(
			AusleihartikelDTO ausleihartikelDTO);

	public AusleihartikelDTO ausleihartikel_TO_AusleihartikelDTO(
			Ausleihartikel ausleihartikel);

	public void shutdown();

}
