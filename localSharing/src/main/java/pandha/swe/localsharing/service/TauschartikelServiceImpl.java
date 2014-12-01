package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

@Service("tauschartikelService")
public class TauschartikelServiceImpl implements TauschartikelService {

	@Autowired
	TauschartikelDAO tauschartikelDao;

	@Override
	public Tauschartikel findById(Long id) {
		return tauschartikelDao.findById(id);
	}

	@Override
	public List<Tauschartikel> findAll() {
		return tauschartikelDao.findAll();
	}
	
	@Override
	public List<TauschartikelDTO> findAllByBenutzer(Benutzer benutzer) {
		List<Tauschartikel> tauschartikelListe = tauschartikelDao.findAllByBenutzer(benutzer);
		List<TauschartikelDTO> tauschartikelDTOListe = new ArrayList<>();
		for(Tauschartikel tauschartikel: tauschartikelListe) {
			tauschartikelDTOListe.add(tauschartikel_TO_TauschartikelDTO(tauschartikel));
		}
		return tauschartikelDTOListe;
	}

	@Override
	public void save(Tauschartikel tauschartikel) {
		tauschartikelDao.save(tauschartikel);
	}

	@Override
	public void update(Tauschartikel tauschartikel) {
		tauschartikelDao.update(tauschartikel);
	}

	@Override
	public void delete(Tauschartikel tauschartikel) {
		tauschartikelDao.delete(tauschartikel);
	}

	@Override
	public void createTauschartikel(TauschartikelDTO tauschartikelDTO) {

		Tauschartikel tauschartikel = new Tauschartikel(null,
				tauschartikelDTO.getBenutzer(), tauschartikelDTO.getTitel(),
				tauschartikelDTO.getBeschreibung(),
				tauschartikelDTO.getStartDatum(),
				tauschartikelDTO.getKategorie());

		save(tauschartikel);

	}

	@Override
	public Tauschartikel tauschartikelDTO_TO_Tauschartikel(
			TauschartikelDTO tauschartikelDTO) {

		Tauschartikel tauschartikel = findById(tauschartikelDTO.getId());

		tauschartikel.setBenutzer(tauschartikelDTO.getBenutzer());
		tauschartikel.setTitel(tauschartikelDTO.getTitel());
		tauschartikel.setBeschreibung(tauschartikelDTO.getBeschreibung());
		tauschartikel.setStartDatum(tauschartikelDTO.getStartDatum());
		tauschartikel.setKategorie(tauschartikelDTO.getKategorie());

		return tauschartikel;
	}

	@Override
	public TauschartikelDTO tauschartikel_TO_TauschartikelDTO(
			Tauschartikel tauschartikel) {

		TauschartikelDTO tauschartikelDTO = new TauschartikelDTO(
				tauschartikel.getAngebotsid(), tauschartikel.getBenutzer(),
				tauschartikel.getTitel(), tauschartikel.getBeschreibung(),
				tauschartikel.getStartDatum(), tauschartikel.getKategorie());

		return tauschartikelDTO;
	}

	@Override
	public void shutdown() {
		tauschartikelDao.shutdown();
	}

}
