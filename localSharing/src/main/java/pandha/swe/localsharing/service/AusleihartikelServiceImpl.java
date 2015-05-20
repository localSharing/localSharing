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
		return list_angebot_TO_AngebotDTO(findAll());
	}

	@Override
	public List<AusleihartikelDTO> findAllEnabled() {
		List<Ausleihartikel> ausleihartikelEnabled = ausleihartikelDao
				.findAllEnabled();
		return list_angebot_TO_AngebotDTO(ausleihartikelEnabled);
	}

	@Override
	public List<AusleihartikelDTO> findAllDisabled() {
		List<Ausleihartikel> ausleihartikelEnabled = ausleihartikelDao
				.findAllDisabled();
		return list_angebot_TO_AngebotDTO(ausleihartikelEnabled);
	}

	@Override
	public List<AusleihartikelDTO> findAllEnabledByBenutzer(Benutzer benutzer) {
		List<Ausleihartikel> ausleihartikelEnabled = ausleihartikelDao
				.findAllEnabledByBenutzer(benutzer);
		return list_angebot_TO_AngebotDTO(ausleihartikelEnabled);
	}

	@Override
	public List<AusleihartikelDTO> findAllByBenutzer(Benutzer benutzer) {
		List<Ausleihartikel> ausleihartikelListe = ausleihartikelDao
				.findAllByBenutzer(benutzer);
		return list_angebot_TO_AngebotDTO(ausleihartikelListe);
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
	public Long createAngebot(AusleihartikelDTO ausleihartikelDTO) {

		Ausleihartikel ausleihartikel = new Ausleihartikel(null, Boolean.TRUE,
				ausleihartikelDTO.getBenutzer(), ausleihartikelDTO.getTitel(),
				ausleihartikelDTO.getBeschreibung(),
				stringToDate(ausleihartikelDTO.getStartDatum()),
				stringToDate(ausleihartikelDTO.getEndDatum()),
				ausleihartikelDTO.getDauer(), ausleihartikelDTO.getKategorie());

		return save(ausleihartikel);

	}

	@Override
	public Ausleihartikel angebotDTO_TO_Angebot(
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
	public AusleihartikelDTO angebot_TO_AngebotDTO(Ausleihartikel ausleihartikel) {

		AusleihartikelDTO ausleihartikelDTO = new AusleihartikelDTO(
				ausleihartikel.getAngebotsid(), ausleihartikel.getEnabled(),
				ausleihartikel.getBenutzer(), ausleihartikel.getTitel(),
				ausleihartikel.getBeschreibung(),
				dateToString(ausleihartikel.getStartDatum()),
				dateToString(ausleihartikel.getEndDatum()),
				ausleihartikel.getDauer(), ausleihartikel.getKategorie());

		return ausleihartikelDTO;
	}

	@Override
	public List<AusleihartikelDTO> list_angebot_TO_AngebotDTO(
			List<Ausleihartikel> listAusleihartikel) {
		List<AusleihartikelDTO> listAusleihartikelDTO = new ArrayList<AusleihartikelDTO>();
		for (Ausleihartikel ausleihartikel : listAusleihartikel) {
			listAusleihartikelDTO.add(angebot_TO_AngebotDTO(ausleihartikel));
		}
		return listAusleihartikelDTO;
	}

	@Override
	public void shutdown() {
		ausleihartikelDao.shutdown();
	}

}
