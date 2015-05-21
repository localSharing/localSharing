package pandha.swe.localsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.config.StringConstants;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;

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
		case StringConstants.AUSLEIHEN:
			angebot = ausleihartikelDao.findById(id);
			break;

		case StringConstants.TAUSCHEN:
			angebot = tauschartikelDao.findById(id);
			break;

		case StringConstants.HELFEN:
			angebot = hilfeleistungDao.findById(id);
			break;
		}
		return angebot;
	}

}
