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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ausleihartikel ausleihartikelDTO_TO_Ausleihartikel(
			AusleihartikelDTO ausleihartikelDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AusleihartikelDTO ausleihartikel_TO_AusleihartikelDTO(
			Ausleihartikel ausleihartikel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() {
		ausleihartikelDao.shutdown();
	}

}
