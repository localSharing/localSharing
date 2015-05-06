package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import static pandha.swe.localsharing.util.Datumsumwandler.dateToString;
import static pandha.swe.localsharing.util.Datumsumwandler.stringToDate;

@Service("ausleihartikelService")
public class AusleihartikelServiceImpl implements AusleihartikelService {

	@Autowired
	private AusleihartikelDAO ausleihartikelDao;

	@Override
	public Ausleihartikel findById(Long id) {
		return ausleihartikelDao.findById(id);
	}

	@Override
	public List<Ausleihartikel> findAll() {
		return ausleihartikelDao.findAll();
	}
	

	@Override
	public List<AusleihartikelDTO> findAllDTO() {
		return list_Ausleihartikel_TO_AusleihartikelDTO(findAll());
	}

	@Override
	public List<AusleihartikelDTO> findAllByBenutzer(Benutzer benutzer) {
		List<Ausleihartikel> ausleihartikelListe = ausleihartikelDao
				.findAllByBenutzer(benutzer);
		List<AusleihartikelDTO> ausleihartikelDTOListe = new ArrayList<>();
		for (Ausleihartikel ausleihartikel : ausleihartikelListe) {
			ausleihartikelDTOListe
					.add(ausleihartikel_TO_AusleihartikelDTO(ausleihartikel));
		}
		return ausleihartikelDTOListe;
	}

	@Override
	public Long save(Ausleihartikel ausleihartikel) {
		return ausleihartikelDao.save(ausleihartikel);
	}

	@Override
	public void update(Ausleihartikel ausleihartikel) {
		ausleihartikelDao.update(ausleihartikel);
	}

	@Override
	public void delete(Ausleihartikel ausleihartikel) {
		ausleihartikelDao.delete(ausleihartikel);
	}

	@Override
	public Long createAusleihartikel(AusleihartikelDTO ausleihartikelDTO) {

		Ausleihartikel ausleihartikel = new Ausleihartikel(null,
				ausleihartikelDTO.getBenutzer(), ausleihartikelDTO.getTitel(),
				ausleihartikelDTO.getBeschreibung(),
				stringToDate(ausleihartikelDTO.getStartDatum()),
				stringToDate(ausleihartikelDTO.getEndDatum()),
				ausleihartikelDTO.getDauer(), ausleihartikelDTO.getKategorie());

		return save(ausleihartikel);

	}

	@Override
	public Ausleihartikel ausleihartikelDTO_TO_Ausleihartikel(
			AusleihartikelDTO ausleihartikelDTO) {

		Ausleihartikel ausleihartikel = findById(ausleihartikelDTO.getId());

		ausleihartikel.setBenutzer(ausleihartikelDTO.getBenutzer());
		ausleihartikel.setTitel(ausleihartikelDTO.getTitel());
		ausleihartikel.setBeschreibung(ausleihartikelDTO.getBeschreibung());
		ausleihartikel.setStartDatum(stringToDate(ausleihartikelDTO
				.getStartDatum()));
		ausleihartikel
				.setEndDatum(stringToDate(ausleihartikelDTO.getEndDatum()));
		ausleihartikel.setDauer(ausleihartikelDTO.getDauer());
		ausleihartikel.setKategorie(ausleihartikelDTO.getKategorie());

		return ausleihartikel;
	}

	@Override
	public AusleihartikelDTO ausleihartikel_TO_AusleihartikelDTO(
			Ausleihartikel ausleihartikel) {

		AusleihartikelDTO ausleihartikelDTO = new AusleihartikelDTO(
				ausleihartikel.getAngebotsid(), ausleihartikel.getBenutzer(),
				ausleihartikel.getTitel(), ausleihartikel.getBeschreibung(),
				dateToString(ausleihartikel.getStartDatum()),
				dateToString(ausleihartikel.getEndDatum()),
				ausleihartikel.getDauer(), ausleihartikel.getKategorie());

		return ausleihartikelDTO;
	}


	@Override
	public List<AusleihartikelDTO> list_Ausleihartikel_TO_AusleihartikelDTO(
			List<Ausleihartikel> listAusleihartikel) {
		List<AusleihartikelDTO> listAusleihartikelDTO = new ArrayList<AusleihartikelDTO>();
		for (Ausleihartikel ausleihartikel : listAusleihartikel) {
			listAusleihartikelDTO.add(ausleihartikel_TO_AusleihartikelDTO(ausleihartikel));
		}
		return listAusleihartikelDTO;
	}
	
	@Override
	public void shutdown() {
		ausleihartikelDao.shutdown();
	}

}
