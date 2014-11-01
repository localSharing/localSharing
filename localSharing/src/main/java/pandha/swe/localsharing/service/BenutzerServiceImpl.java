package pandha.swe.localsharing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.dao.BenutzerDao;
import pandha.swe.localsharing.model.Benutzer;

@Service("benutzerService")
public class BenutzerServiceImpl implements BenutzerService {

	@Autowired
	private BenutzerDao benutzerDao;

	@Override
	public Benutzer findById(long id) {
		return benutzerDao.findById(id);
	}

	@Override
	public List<Benutzer> findAll() {
		return benutzerDao.findAll();
	}

	@Override
	public void save(Benutzer benutzer) {
		benutzerDao.save(benutzer);
	}

	@Override
	public void update(Benutzer benutzer) {
		benutzerDao.update(benutzer);
	}

	@Override
	public void delete(Benutzer benutzer) {
		benutzerDao.delete(benutzer);
	}

	@Override
	public void shutdown() {
		benutzerDao.shutdown();
	}


}
