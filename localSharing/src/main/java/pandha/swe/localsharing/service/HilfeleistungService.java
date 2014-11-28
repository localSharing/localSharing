package pandha.swe.localsharing.service;

import java.util.ArrayList;
import java.util.List;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;

public interface HilfeleistungService {

	public Hilfeleistung findById(Long id);

	// public Hilfeleistung findByBenutzer(Benutzer benutzer);
	// Julia: Ich brauch ne Liste, die mir zurückgegeben wird also bitte so wie
	// unten machen :)

	public ArrayList<HilfeleistungDTO> findAllByBenutzer(Benutzer benutzer);

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
