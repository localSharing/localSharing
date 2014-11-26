package pandha.swe.localsharing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;

@Service("hilfeleistungService")
public class HilfeleistungServiceImpl implements HilfeleistungService {
	
	@Autowired
	HilfeleistungDAO hilfeleistungDao;

	@Override
	public Hilfeleistung findById(Long id) {
		return hilfeleistungDao.findById(id);
	}

	@Override
	public List<Hilfeleistung> findAll() {
		return hilfeleistungDao.findAll();
	}

	@Override
	public void save(Hilfeleistung hilfeleistung) {
		hilfeleistungDao.save(hilfeleistung);
	}

	@Override
	public void update(Hilfeleistung hilfeleistung) {
		hilfeleistungDao.update(hilfeleistung);
	}

	@Override
	public void delete(Hilfeleistung hilfeleistung) {
		hilfeleistungDao.delete(hilfeleistung);
	}

	@Override
	public void createHilfeleistung(HilfeleistungDTO hilfeleistungDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public Hilfeleistung hilfeleistungDTO_TO_Hilfeleistung(
			HilfeleistungDTO hilfeleistungDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HilfeleistungDTO hilfeleistung_TO_HilfeleistungDTO(
			Hilfeleistung hilfeleistung) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() {
		hilfeleistungDao.shutdown();
	}

}
