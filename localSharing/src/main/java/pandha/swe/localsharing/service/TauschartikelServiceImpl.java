package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import static pandha.swe.localsharing.util.Datumsumwandler.dateToString;
import static pandha.swe.localsharing.util.Datumsumwandler.stringToDate;

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
	public List<TauschartikelDTO> findAllDTO() {
		return list_Tauschartikel_TO_TauschartikelDTO(findAll());
	}

	@Override
	public List<TauschartikelDTO> findAllByBenutzer(Benutzer benutzer) {
		List<Tauschartikel> tauschartikelListe = tauschartikelDao
				.findAllByBenutzer(benutzer);
		List<TauschartikelDTO> tauschartikelDTOListe = new ArrayList<>();
		for (Tauschartikel tauschartikel : tauschartikelListe) {
			tauschartikelDTOListe
					.add(tauschartikel_TO_TauschartikelDTO(tauschartikel));
		}
		return tauschartikelDTOListe;
	}

	@Override
	public Long save(Tauschartikel tauschartikel) {
		return tauschartikelDao.save(tauschartikel);
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
	public Long createTauschartikel(TauschartikelDTO tauschartikelDTO) {

		Tauschartikel tauschartikel = new Tauschartikel(null,
				tauschartikelDTO.getEnabled(), tauschartikelDTO.getBenutzer(),
				tauschartikelDTO.getTitel(),
				tauschartikelDTO.getBeschreibung(),
				stringToDate(tauschartikelDTO.getStartDatum()),
				tauschartikelDTO.getKategorie());

		return save(tauschartikel);

	}

	@Override
	public Tauschartikel tauschartikelDTO_TO_Tauschartikel(
			TauschartikelDTO tauschartikelDTO) {

		Tauschartikel tauschartikel = findById(tauschartikelDTO.getId());

		tauschartikel.setBenutzer(tauschartikelDTO.getBenutzer());
		tauschartikel.setTitel(tauschartikelDTO.getTitel());
		tauschartikel.setBeschreibung(tauschartikelDTO.getBeschreibung());
		tauschartikel.setStartDatum(stringToDate(tauschartikelDTO
				.getStartDatum()));
		tauschartikel.setKategorie(tauschartikelDTO.getKategorie());

		return tauschartikel;
	}

	@Override
	public TauschartikelDTO tauschartikel_TO_TauschartikelDTO(
			Tauschartikel tauschartikel) {

		TauschartikelDTO tauschartikelDTO = new TauschartikelDTO(
				tauschartikel.getAngebotsid(), tauschartikel.getEnabled(),
				tauschartikel.getBenutzer(), tauschartikel.getTitel(),
				tauschartikel.getBeschreibung(),
				dateToString(tauschartikel.getStartDatum()),
				tauschartikel.getKategorie());

		return tauschartikelDTO;
	}
	
	@Override
	public List<TauschartikelDTO> list_Tauschartikel_TO_TauschartikelDTO(
			List<Tauschartikel> listTauschartikel) {
		List<TauschartikelDTO> listTauschartikelDTO = new ArrayList<TauschartikelDTO>();
		for (Tauschartikel tauschartikel : listTauschartikel) {
			listTauschartikelDTO.add(tauschartikel_TO_TauschartikelDTO(tauschartikel));
		}
		return listTauschartikelDTO;
	}

	@Override
	public void shutdown() {
		tauschartikelDao.shutdown();
	}

}
