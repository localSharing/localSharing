package pandha.swe.localsharing.model.dao;

import java.util.List;

import pandha.swe.localsharing.model.Hilfeleistung;

public interface HilfeleistungDAO {
	
	public Hilfeleistung findById(Long id);
	
	public List<Hilfeleistung> findAll();
	
	public void save(Hilfeleistung hilfeleistung);
	
	public void update(Hilfeleistung hilfeleistung);
	
	public void delete(Hilfeleistung hilfeleistung);
	
	public void shutdown();
	
}
