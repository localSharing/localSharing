package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import static pandha.swe.localsharing.util.Datumsumwandler.dateToString;
import static pandha.swe.localsharing.util.Datumsumwandler.stringToDate;

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
	public List<HilfeleistungDTO> findAllDTO() {
		return list_angebot_TO_AngebotDTO(findAll());
	}
	
	@Override
	public List<HilfeleistungDTO> findAllEnabled() {
		List<Hilfeleistung> hilfeleistungListe = 
				hilfeleistungDao.findAllEnabled();
		return list_angebot_TO_AngebotDTO(hilfeleistungListe);
	}
	
	@Override
	public List<HilfeleistungDTO> findAllDisabled() {
		List<Hilfeleistung> hilfeleistungListe = 
				hilfeleistungDao.findAllDisabled();
		return list_angebot_TO_AngebotDTO(hilfeleistungListe);
	}
	
	@Override
	public List<HilfeleistungDTO> findAllEnabledByBenutzer(Benutzer benutzer) {
		List<Hilfeleistung> hilfeleistungListe = 
				hilfeleistungDao.findAllEnabledByBenutzer(benutzer);
		return list_angebot_TO_AngebotDTO(hilfeleistungListe);
	}

	@Override
	public List<HilfeleistungDTO> findAllByBenutzer(Benutzer benutzer) {
		List<Hilfeleistung> hilfeleistungListe = 
				hilfeleistungDao.findAllByBenutzer(benutzer);
		return list_angebot_TO_AngebotDTO(hilfeleistungListe);
	}
	

	@Override
	public Long save(Hilfeleistung hilfeleistung) {
		return hilfeleistungDao.save(hilfeleistung);
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
	public Long createAngebot(HilfeleistungDTO hilfeleistungDTO) {

		Hilfeleistung hilfeleistung = new Hilfeleistung(null,
				Boolean.TRUE, hilfeleistungDTO.getBenutzer(),
				hilfeleistungDTO.getTitel(),
				hilfeleistungDTO.getBeschreibung(),
				stringToDate(hilfeleistungDTO.getStartDatum()),
				stringToDate(hilfeleistungDTO.getEndDatum()));

		return save(hilfeleistung);

	}

	@Override
	public Hilfeleistung angebotDTO_TO_Angebot(
			HilfeleistungDTO hilfeleistungDTO) {

		Hilfeleistung hilfeleistung = findById(hilfeleistungDTO.getId());

		hilfeleistung.setBenutzer(hilfeleistungDTO.getBenutzer());
		hilfeleistung.setTitel(hilfeleistungDTO.getTitel());
		hilfeleistung.setBeschreibung(hilfeleistungDTO.getBeschreibung());
		hilfeleistung.setStartDatum(stringToDate(hilfeleistungDTO
				.getStartDatum()));
		hilfeleistung.setEndDatum(stringToDate(hilfeleistungDTO.getEndDatum()));

		return hilfeleistung;
	}

	@Override
	public HilfeleistungDTO angebot_TO_AngebotDTO(
			Hilfeleistung hilfeleistung) {

		HilfeleistungDTO hilfeleistungDTO = new HilfeleistungDTO(
				hilfeleistung.getAngebotsid(), hilfeleistung.getEnabled(),
				hilfeleistung.getBenutzer(), hilfeleistung.getTitel(),
				hilfeleistung.getBeschreibung(),
				dateToString(hilfeleistung.getStartDatum()),
				dateToString(hilfeleistung.getEndDatum()));

		return hilfeleistungDTO;

	}
	
	@Override
	public List<HilfeleistungDTO> list_angebot_TO_AngebotDTO(
			List<Hilfeleistung> listHilfeleistung) {
		List<HilfeleistungDTO> listHilfeleistungDTO = new ArrayList<HilfeleistungDTO>();
		for (Hilfeleistung hilfeleistung : listHilfeleistung) {
			listHilfeleistungDTO.add(angebot_TO_AngebotDTO(hilfeleistung));
		}
		return listHilfeleistungDTO;
	}

	@Override
	public void shutdown() {
		hilfeleistungDao.shutdown();
	}

}
