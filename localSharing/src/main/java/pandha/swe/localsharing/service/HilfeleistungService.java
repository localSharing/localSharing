package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;

public interface HilfeleistungService {

	public Hilfeleistung findById(Long id);

	// public Hilfeleistung findByBenutzer(Benutzer benutzer);

	public List<Hilfeleistung> findAll();

	public void save(Hilfeleistung hilfeleistung);

	public void update(Hilfeleistung hilfeleistung);

	public void delete(Hilfeleistung hilfeleistung);

	public void createHilfeleistung(HilfeleistungDTO hilfeleistungDTO);

	public Hilfeleistung hilfeleistungDTO_TO_Hilfeleistung(
			HilfeleistungDTO hilfeleistungDTO);

	public HilfeleistungDTO hilfeleistung_TO_HilfeleistungDTO(
			Hilfeleistung hilfeleistung);

	public void shutdown();

}
