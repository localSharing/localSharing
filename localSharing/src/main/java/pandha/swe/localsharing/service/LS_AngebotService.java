package pandha.swe.localsharing.service;

import java.util.List;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AngebotDTO;

public interface LS_AngebotService<E extends Angebot, T extends AngebotDTO> {

	public abstract E findById(Long id);

	public abstract List<E> findAll();

	public abstract List<T> findAllDTO();

	public abstract List<T> findAllEnabled();

	public abstract List<T> findAllDisabled();

	public abstract List<T> findAllEnabledByBenutzer(Benutzer benutzer);

	public abstract List<T> findAllByBenutzer(Benutzer benutzer);

	public abstract Long save(E angebot);

	public abstract void update(E angebot);

	public abstract void delete(E angebot);

	public abstract Long createAngebot(T angebotDTO);

	public abstract E angebotDTO_TO_Angebot(T angebotDTO);

	public abstract T angebot_TO_AngebotDTO(E angebot);

	public abstract List<T> list_angebot_TO_AngebotDTO(List<E> listangebot);

	public abstract void shutdown();

}