package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Anfrage;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AnfrageDTO;

public interface AnfrageService {

	public Anfrage findById(long id);

	public List<Anfrage> findAll();

	public List<AnfrageDTO> findAllDTO();

	public List<Anfrage> findByAngebot(Angebot angebot);

	public List<Anfrage> findAllBySender(Benutzer sender);

	public List<Anfrage> findAllByEmpfaenger(Benutzer empfaenger);

	public List<Anfrage> findAllByEmpfaengerId(Long id);

	public void save(Anfrage anfrage);

	public void update(Anfrage anfrage);

	public void delete(Anfrage anfrage);

	public void shutdown();

	public void createAnfrage(AnfrageDTO anfrageDTO);

	public Anfrage anfrageDTO_TO_Anfrage(AnfrageDTO anfrageDTO);

	public AnfrageDTO anfrage_TO_AnfrageDTO(Anfrage anfrage);

	public List<AnfrageDTO> list_Anfrage_TO_AnfrageDTO(
			List<Anfrage> listAnfragen);
}
