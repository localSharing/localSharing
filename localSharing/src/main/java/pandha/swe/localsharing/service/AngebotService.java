package pandha.swe.localsharing.service;

import pandha.swe.localsharing.model.Angebot;

public interface AngebotService {
	
	public Angebot findByIdAndType(Long id, String type);

}