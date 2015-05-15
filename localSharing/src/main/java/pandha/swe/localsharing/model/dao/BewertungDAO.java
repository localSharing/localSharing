package pandha.swe.localsharing.model.dao;

import java.util.List;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Bewertung;

public interface BewertungDAO {
	
	public Bewertung findById(Long id);
	
	public List<Bewertung> findAll();
	
	public List<Bewertung> findAllByAngebot(Angebot angebot);
	
	public List<Bewertung> findAllByBewerter(Benutzer bewerter);
	
	public Long save(Bewertung bewertung);
	
	public void update(Bewertung bewertung);
	
	public void delete(Bewertung bewertung);
	
	public void shutdown();

}
