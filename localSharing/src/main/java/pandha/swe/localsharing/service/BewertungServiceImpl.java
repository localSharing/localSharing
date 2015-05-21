package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Bewertung;
import pandha.swe.localsharing.model.dao.BewertungDAO;
import pandha.swe.localsharing.model.dto.BewertungDTO;

@Service("bewertungService")
public class BewertungServiceImpl implements BewertungService {

	@Autowired
	private BewertungDAO bewertungDAO;

	@Override
	public Bewertung findById(long id) {
		return bewertungDAO.findById(id);
	}

	@Override
	public List<Bewertung> findAll() {
		return bewertungDAO.findAll();
	}

	@Override
	public List<Bewertung> findByAngebot(Angebot angebot) {
		return bewertungDAO.findAllByAngebot(angebot);
	}

	@Override
	public List<Bewertung> findByBewerter(Benutzer bewerter) {
		return bewertungDAO.findAllByBewerter(bewerter);
	}

	@Override
	public Long save(Bewertung bewertung) {
		return bewertungDAO.save(bewertung);
	}

	@Override
	public void update(Bewertung bewertung) {
		bewertungDAO.update(bewertung);
	}

	@Override
	public void delete(Bewertung bewertung) {
		bewertungDAO.delete(bewertung);

	}

	@Override
	public Bewertung bewertungDTO_TO_Bewertung(BewertungDTO bewertungDTO) {

		Bewertung bewertung = findById(bewertungDTO.getId());

		bewertung.setAngebot(bewertungDTO.getAngebot());
		bewertung.setBewerter(bewertungDTO.getBewerter());
		bewertung.setBewertungSterne(bewertungDTO.getBewertungSterne());
		bewertung.setKommentar(bewertungDTO.getKommentar());
		bewertung.setDatum(bewertungDTO.getDatum());

		return bewertung;
	}

	@Override
	public BewertungDTO bewertung_TO_BewertungDTO(Bewertung bewertung) {

		BewertungDTO bewertungDTO = new BewertungDTO(
				bewertung.getBewertungsid(), bewertung.getAngebot(),
				bewertung.getBewerter(), bewertung.getBewertungSterne(),
				bewertung.getKommentar(), bewertung.getDatum());

		return bewertungDTO;
	}

	@Override
	public List<BewertungDTO> list_Bewertung_TO_BewertungDTO(
			List<Bewertung> listBewertung) {

		List<BewertungDTO> listBewertungDTO = new ArrayList<BewertungDTO>();

		for (Bewertung bewertung : listBewertung) {
			listBewertungDTO.add(bewertung_TO_BewertungDTO(bewertung));
		}

		return listBewertungDTO;
	}

	@Override
	public void shutdown() {
		bewertungDAO.shutdown();
	}

	@Override
	public List<BewertungDTO> findAllDTO() {
		return list_Bewertung_TO_BewertungDTO(findAll());
	}

	@Override
	public Long createBewertung(BewertungDTO bewertungDTO) {

		Bewertung bewertung = new Bewertung(null, bewertungDTO.getAngebot(),
				bewertungDTO.getBewerter(), bewertungDTO.getBewertungSterne(),
				bewertungDTO.getKommentar(), new Date());

		return save(bewertung);
	}

}
