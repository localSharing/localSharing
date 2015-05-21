package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Bewertung;
import pandha.swe.localsharing.model.dto.BewertungDTO;

public interface BewertungService {

	public Bewertung findById(long id);

	public List<Bewertung> findAll();
	
	public List<BewertungDTO> findAllDTO();

	public List<Bewertung> findByAngebot(Angebot angebot);

	public List<Bewertung> findByBewerter(Benutzer bewerter);

	public Long save(Bewertung bewertung);

	public void update(Bewertung bewertung);

	public void delete(Bewertung bewertung);
	
	public Long createBewertung(BewertungDTO bewertungDTO);

	public Bewertung bewertungDTO_TO_Bewertung(BewertungDTO bewertungDTO);

	public BewertungDTO bewertung_TO_BewertungDTO(Bewertung bewertung);

	public List<BewertungDTO> list_Bewertung_TO_BewertungDTO(
			List<Bewertung> listBewertung);

	public void shutdown();
}
