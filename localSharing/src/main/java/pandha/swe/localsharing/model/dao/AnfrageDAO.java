package pandha.swe.localsharing.model.dao;

import java.util.List;

import pandha.swe.localsharing.model.Anfrage;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;

public interface AnfrageDAO {

	public Anfrage findById(Long id);
	
	public List<Anfrage> findAllByAngebot(Angebot angebot);
	
	public List<Anfrage> findAllBySender(Benutzer sender);
	
	public List<Anfrage> findAllByEmpfaenger(Benutzer empfanger);
	
	public List<Anfrage> findAll();
	
	public void save(Anfrage anfrage);
	
	public void update(Anfrage anfrage);
	
	public void delete(Anfrage anfrage);
	
	public void shutdown();
}
