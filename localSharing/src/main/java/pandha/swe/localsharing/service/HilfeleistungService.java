package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;

public interface HilfeleistungService {

	public Hilfeleistung findById(Long id);

	public List<Hilfeleistung> findAll();
	
	public List<HilfeleistungDTO> findAllDTO();

	public List<HilfeleistungDTO> findAllEnabled();

	public List<HilfeleistungDTO> findAllByBenutzer(Benutzer benutzer);

	public Long save(Hilfeleistung hilfeleistung);

	public void update(Hilfeleistung hilfeleistung);

	public void delete(Hilfeleistung hilfeleistung);

	public Long createHilfeleistung(HilfeleistungDTO hilfeleistungDTO);

	public Hilfeleistung hilfeleistungDTO_TO_Hilfeleistung(
			HilfeleistungDTO hilfeleistungDTO);

	public HilfeleistungDTO hilfeleistung_TO_HilfeleistungDTO(
			Hilfeleistung hilfeleistung);
	
	public List<HilfeleistungDTO> list_Hilfeleistung_TO_HilfeleistungDTO(
			List<Hilfeleistung> listHilfeleistung);

	public void shutdown();

}
