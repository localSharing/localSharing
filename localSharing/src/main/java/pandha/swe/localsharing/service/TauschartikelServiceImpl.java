package pandha.swe.localsharing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub

	}

	@Override
	public Tauschartikel tauschartikelDTO_TO_Tauschartikel(
			TauschartikelDTO tauschartikelDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TauschartikelDTO tauschartikel_TO_TauschartikelDTO(
			Tauschartikel tauschartikel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() {
		tauschartikelDao.shutdown();
	}

}
