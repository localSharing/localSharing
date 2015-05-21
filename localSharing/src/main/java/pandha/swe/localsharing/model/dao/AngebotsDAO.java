package pandha.swe.localsharing.model.dao;

import java.util.List;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;

public interface AngebotsDAO<E extends Angebot> {

	public E findById(Long id);

	public List<E> findAll();

	public List<E> findAllEnabled();

	public List<E> findAllDisabled();

	public List<E> findAllEnabledByBenutzer(Benutzer benutzer);

	public List<E> findAllByBenutzer(Benutzer benutzer);

	public Long save(E E);

	public void update(E E);

	public void delete(E E);

	public void shutdown();

}
