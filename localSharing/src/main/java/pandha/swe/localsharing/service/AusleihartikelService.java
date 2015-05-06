package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;

public interface AusleihartikelService {

	public Ausleihartikel findById(Long id);

	public List<Ausleihartikel> findAll();
	
	public List<AusleihartikelDTO> findAllDTO();

	public List<AusleihartikelDTO> findAllByBenutzer(Benutzer benutzer);

	public Long save(Ausleihartikel ausleihartikel);

	public void update(Ausleihartikel ausleihartikel);

	public void delete(Ausleihartikel ausleihartikel);

	public Long createAusleihartikel(AusleihartikelDTO ausleihartikelDTO);

	public Ausleihartikel ausleihartikelDTO_TO_Ausleihartikel(
			AusleihartikelDTO ausleihartikelDTO);

	public AusleihartikelDTO ausleihartikel_TO_AusleihartikelDTO(
			Ausleihartikel ausleihartikel);
	
	public List<AusleihartikelDTO> list_Ausleihartikel_TO_AusleihartikelDTO(
			List<Ausleihartikel> listAusleihartikel);

	public void shutdown();

}
