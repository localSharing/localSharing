package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Anfrage;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dao.AnfrageDAO;
import pandha.swe.localsharing.model.dto.AnfrageDTO;
import pandha.swe.localsharing.model.enums.AnfrageStatus;
import pandha.swe.localsharing.util.Datumsumwandler;

@Service("anfrageService")
public class AnfrageServiceImpl implements AnfrageService {

	@Autowired
	private AngebotService angebotService;

	@Autowired
	private BenutzerService benutzerService;

	@Autowired
	private AnfrageDAO anfrageDAO;

	@Override
	public Anfrage findById(long id) {
		return anfrageDAO.findById(id);
	}

	@Override
	public List<Anfrage> findAll() {
		return anfrageDAO.findAll();
	}

	@Override
	public List<AnfrageDTO> findAllDTO() {
		return list_Anfrage_TO_AnfrageDTO(findAll());
	}

	@Override
	public List<Anfrage> findByAngebot(Angebot angebot) {
		return anfrageDAO.findAllByAngebot(angebot);
	}

	@Override
	public List<Anfrage> findAllBySender(Benutzer sender) {
		return anfrageDAO.findAllBySender(sender);
	}

	@Override
	public List<Anfrage> findAllByEmpfaenger(Benutzer empfaenger) {
		return anfrageDAO.findAllByEmpfaenger(empfaenger);
	}

	@Override
	public List<Anfrage> findAllByEmpfaengerId(Long id) {
		return anfrageDAO.findAllByEmpfaenger(benutzerService.findById(id));
	}

	@Override
	public void save(Anfrage anfrage) {
		anfrageDAO.save(anfrage);
	}

	@Override
	public void update(Anfrage anfrage) {
		anfrageDAO.update(anfrage);
	}

	@Override
	public void delete(Anfrage anfrage) {
		anfrageDAO.delete(anfrage);
	}

	@Override
	public void shutdown() {
		anfrageDAO.shutdown();
	}

	@Override
	public void createAnfrage(AnfrageDTO anfrageDTO) {

		Anfrage anfrage = new Anfrage(null, anfrageDTO.getAngebot(),
				anfrageDTO.getSender(), new Date(),
				Datumsumwandler.stringToDate(anfrageDTO.getStartDatum()),
				Datumsumwandler.stringToDate(anfrageDTO.getEndDatum()),
				anfrageDTO.getKommentar(), AnfrageStatus.offen,
				anfrageDTO.getKontaktArt());

		save(anfrage);
	}

	@Override
	public Anfrage anfrageDTO_TO_Anfrage(AnfrageDTO anfrageDTO) {

		Anfrage anfrage = findById(anfrageDTO.getId());

		anfrage.setAngebot(anfrageDTO.getAngebot());
		anfrage.setSender(anfrageDTO.getSender());
		anfrage.setDatum(anfrageDTO.getDatum());
		anfrage.setStartDatum(Datumsumwandler.stringToDate(anfrageDTO
				.getStartDatum()));
		anfrage.setEndDatum(Datumsumwandler.stringToDate(anfrageDTO
				.getEndDatum()));
		anfrage.setKommentar(anfrageDTO.getKommentar());
		anfrage.setStatus(anfrageDTO.getStatus());
		anfrage.setKontaktArt(anfrageDTO.getKontaktArt());

		return anfrage;
	}

	@Override
	public AnfrageDTO anfrage_TO_AnfrageDTO(Anfrage anfrage) {

		AnfrageDTO anfrageDTO = new AnfrageDTO(anfrage.getAnfrageid(),
				anfrage.getAngebot(), anfrage.getSender(), anfrage.getDatum(),
				Datumsumwandler.dateToString(anfrage.getStartDatum()),
				Datumsumwandler.dateToString(anfrage.getEndDatum()),
				anfrage.getKommentar(), anfrage.getStatus(),
				anfrage.getKontaktArt());

		return anfrageDTO;
	}

	@Override
	public List<AnfrageDTO> list_Anfrage_TO_AnfrageDTO(
			List<Anfrage> listAnfragen) {

		List<AnfrageDTO> listAnfrageDTO = new ArrayList<AnfrageDTO>();

		for (Anfrage anfrage : listAnfragen) {
			listAnfrageDTO.add(anfrage_TO_AnfrageDTO(anfrage));
		}

		return listAnfrageDTO;
	}

}
