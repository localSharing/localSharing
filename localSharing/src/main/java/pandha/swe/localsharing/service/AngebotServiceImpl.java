package pandha.swe.localsharing.service;

import static pandha.swe.localsharing.util.Datumsumwandler.dateToString;
import static pandha.swe.localsharing.util.Datumsumwandler.stringToDate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.dao.AngebotsDAO;
import pandha.swe.localsharing.model.dto.AngebotDTO;

@Service("angebotService")
public class AngebotServiceImpl implements AngebotService {

	@Autowired
	private HashMap<String, AngebotsDAO<?>> angebotDAOs;

	@Override
	public Angebot findAngebotByIdAndType(Long id, String type) {
		return angebotDAOs.get(type).findById(id);
	}

	@Override
	public Angebot findAngebotById(Long id) {

		for (String key : angebotDAOs.keySet()) {
			Angebot angebot = angebotDAOs.get(key).findById(id);
			if (angebot != null) {
				return angebot;
			}
		}
		return null;
	}

	@Override
	public AngebotDTO angebot_TO_AngebotDTO(Angebot angebot) {

		AngebotDTO angebotDTO = new AngebotDTO(angebot.getAngebotsid(),
				angebot.getEnabled(), angebot.getBenutzer(),
				angebot.getTitel(), angebot.getBeschreibung(),
				dateToString(angebot.getStartDatum()));

		return angebotDTO;
	}

	@Override
	public Angebot angebotDTO_TO_Angebot(AngebotDTO angebotDTO) {

		Angebot angebot = new Angebot(angebotDTO.getId(),
				angebotDTO.getEnabled(), angebotDTO.getBenutzer(),
				angebotDTO.getTitel(), angebotDTO.getBeschreibung(),
				stringToDate(angebotDTO.getStartDatum()));
		return angebot;
	}

}
