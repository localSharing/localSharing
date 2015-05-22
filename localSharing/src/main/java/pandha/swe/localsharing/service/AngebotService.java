package pandha.swe.localsharing.service;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.dto.AngebotDTO;

public interface AngebotService {
	
	public Angebot getAngebotByIdAndType(Long id, String type);

	public AngebotDTO angebot_TO_AngebotDTO(Angebot angebot);

	public Angebot angebotDTO_TO_Angebot(AngebotDTO angebotDTO);
}