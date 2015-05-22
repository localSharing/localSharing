package pandha.swe.localsharing.service;

import static pandha.swe.localsharing.util.Datumsumwandler.dateToString;
import static pandha.swe.localsharing.util.Datumsumwandler.stringToDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;
import pandha.swe.localsharing.model.dto.AngebotDTO;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;

@Service("angebotService")
public class AngebotServiceImpl implements AngebotService {
	
	@Autowired
	private AusleihartikelDAO ausleihartikelDao;

	@Autowired
	private TauschartikelDAO tauschartikelDao;

	@Autowired
	private HilfeleistungDAO hilfeleistungDao;

	@Override
	public Angebot findAngebotByIdAndType(Long id, String type) {
		Angebot angebot = null;
		switch (type) {
		case "ausleihen":
			angebot = ausleihartikelDao.findById(id);
			break;

		case "tauschen":
			angebot = tauschartikelDao.findById(id);
			break;

		case "helfen":
			angebot = hilfeleistungDao.findById(id);
			break;
		}
		return angebot;
	}
	
	@Override
	public Angebot findAngebotById(Long id) {
		// TODO Implement method by using methods of AngebotDAO
		return null;
	}
	
	@Override
	public AngebotDTO angebot_TO_AngebotDTO(Angebot angebot) {
		
		AngebotDTO angebotDTO = 
				new AngebotDTO(
						angebot.getAngebotsid(), 
						angebot.getEnabled(), 
						angebot.getBenutzer(), 
						angebot.getTitel(), 
						angebot.getBeschreibung(), 
						dateToString(angebot.getStartDatum()));
		
		return angebotDTO;
	}

	@Override
	public Angebot angebotDTO_TO_Angebot(AngebotDTO angebotDTO) {

		Angebot angebot =
				new Angebot(
						angebotDTO.getId(),
						angebotDTO.getEnabled(),
						angebotDTO.getBenutzer(),
						angebotDTO.getTitel(),
						angebotDTO.getBeschreibung(),
						stringToDate(angebotDTO.getStartDatum()));
		return angebot;
	}

}
