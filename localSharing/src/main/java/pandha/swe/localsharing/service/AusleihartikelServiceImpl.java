package pandha.swe.localsharing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;

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
	public void save(Ausleihartikel ausleihartikel) {
		ausleihartikelDao.save(ausleihartikel);
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
	public void createAusleihartikel(AusleihartikelDTO ausleihartikelDTO) {

		Ausleihartikel ausleihartikel = new Ausleihartikel(null,
				ausleihartikelDTO.getBenutzer(), ausleihartikelDTO.getTitel(),
				ausleihartikelDTO.getBeschreibung(),
				ausleihartikelDTO.getStartDatum(),
				ausleihartikelDTO.getEndDatum(), ausleihartikelDTO.getDauer(),
				ausleihartikelDTO.getKategorie());

		save(ausleihartikel);

	}

	@Override
	public Ausleihartikel ausleihartikelDTO_TO_Ausleihartikel(
			AusleihartikelDTO ausleihartikelDTO) {

		Ausleihartikel ausleihartikel = findById(ausleihartikelDTO.getId());

		ausleihartikel.setBenutzer(ausleihartikelDTO.getBenutzer());
		ausleihartikel.setTitel(ausleihartikelDTO.getTitel());
		ausleihartikel.setBeschreibung(ausleihartikelDTO.getBeschreibung());
		ausleihartikel.setStartDatum(ausleihartikelDTO.getStartDatum());
		ausleihartikel.setEndDatum(ausleihartikelDTO.getEndDatum());
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
				ausleihartikel.getStartDatum(), ausleihartikel.getEndDatum(),
				ausleihartikel.getDauer(), ausleihartikel.getKategorie());
		
		return ausleihartikelDTO;
		
	}

	@Override
	public void shutdown() {
		ausleihartikelDao.shutdown();
	}

}
