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
	public Angebot getAngebotByIdAndType(Long id, String type) {
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
	public AngebotDTO angebot_TO_AngebotDTO(Angebot angebot) {
		
		AngebotDTO angebotDTO = null;
		
		return angebotDTO;
	}

	@Override
	public Angebot angebotDTO_TO_Angebot(AngebotDTO angebotDTO) {

		return null;

	}

}
