package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public interface TauschartikelService {

	public Tauschartikel findById(Long id);

	public List<Tauschartikel> findAll();
	
	public List<TauschartikelDTO> findAllDTO();

	public List<TauschartikelDTO> findAllEnabled();

	public List<TauschartikelDTO> findAllByBenutzer(Benutzer benutzer);

	public Long save(Tauschartikel tauschartikel);

	public void update(Tauschartikel tauschartikel);

	public void delete(Tauschartikel tauschartikel);

	public Long createTauschartikel(TauschartikelDTO tauschartikelDTO);

	public Tauschartikel tauschartikelDTO_TO_Tauschartikel(
			TauschartikelDTO tauschartikelDTO);

	public TauschartikelDTO tauschartikel_TO_TauschartikelDTO(
			Tauschartikel tauschartikel);
	
	public List<TauschartikelDTO> list_Tauschartikel_TO_TauschartikelDTO(
			List<Tauschartikel> listTauschartikel);

	public void shutdown();

}
