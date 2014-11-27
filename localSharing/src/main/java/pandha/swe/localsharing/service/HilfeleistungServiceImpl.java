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

		Hilfeleistung hilfeleistung = new Hilfeleistung(null,
				hilfeleistungDTO.getBenutzer(), hilfeleistungDTO.getTitel(),
				hilfeleistungDTO.getBeschreibung(),
				hilfeleistungDTO.getStartDatum(),
				hilfeleistungDTO.getEndDatum());

		save(hilfeleistung);

	}

	@Override
	public Hilfeleistung hilfeleistungDTO_TO_Hilfeleistung(
			HilfeleistungDTO hilfeleistungDTO) {

		Hilfeleistung hilfeleistung = findById(hilfeleistungDTO.getId());

		hilfeleistung.setBenutzer(hilfeleistungDTO.getBenutzer());
		hilfeleistung.setTitel(hilfeleistungDTO.getTitel());
		hilfeleistung.setBeschreibung(hilfeleistungDTO.getBeschreibung());
		hilfeleistung.setStartDatum(hilfeleistungDTO.getStartDatum());
		hilfeleistung.setEndDatum(hilfeleistungDTO.getEndDatum());

		return hilfeleistung;
	}

	@Override
	public HilfeleistungDTO hilfeleistung_TO_HilfeleistungDTO(
			Hilfeleistung hilfeleistung) {
		
		HilfeleistungDTO hilfeleistungDTO = new HilfeleistungDTO(
				hilfeleistung.getAngebotsid(), hilfeleistung.getBenutzer(),
				hilfeleistung.getTitel(), hilfeleistung.getBeschreibung(),
				hilfeleistung.getStartDatum(), hilfeleistung.getEndDatum());

		return hilfeleistungDTO;
		
	}

	@Override
	public void shutdown() {
		hilfeleistungDao.shutdown();
	}

}
