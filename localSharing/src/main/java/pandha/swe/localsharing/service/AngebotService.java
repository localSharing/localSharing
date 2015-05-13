package pandha.swe.localsharing.service;

import pandha.swe.localsharing.model.Angebot;

public interface AngebotService {
	
	public Angebot getAngebotByIdAndType(Long id, String type);
	
}