package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.List;

import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public interface TauschartikelService {

	public Tauschartikel findById(Long id);

	public ArrayList<TauschartikelDTO> findAllByBenutzer();

	public List<Tauschartikel> findAll();

	public void save(Tauschartikel tauschartikel);

	public void update(Tauschartikel tauschartikel);

	public void delete(Tauschartikel tauschartikel);

	public void createTauschartikel(TauschartikelDTO tauschartikelDTO);

	public Tauschartikel tauschartikelDTO_TO_Tauschartikel(
			TauschartikelDTO tauschartikelDTO);

	public TauschartikelDTO tauschartikel_TO_TauschartikelDTO(
			Tauschartikel tauschartikel);

	public void shutdown();

}
