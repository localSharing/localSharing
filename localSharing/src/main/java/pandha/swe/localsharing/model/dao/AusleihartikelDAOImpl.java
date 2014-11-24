package pandha.swe.localsharing.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pandha.swe.localsharing.model.Ausleihartikel;

@Repository("ausleihartikelDao")
public class AusleihartikelDAOImpl implements AusleihartikelDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Ausleihartikel findById(Long id) {
		Ausleihartikel ausleihartikel = (Ausleihartikel) hibernateTemplate.get(
				Ausleihartikel.class, id);
		return ausleihartikel;
	}

	@Override
	public List<Ausleihartikel> findAll() {
		List<Ausleihartikel> ausleihartikel = hibernateTemplate.loadAll(Ausleihartikel.class);
		return ausleihartikel;
	}

	@Override
	public void save(Ausleihartikel ausleihartikel) {
		if (ausleihartikel != null) {
			hibernateTemplate.saveOrUpdate(ausleihartikel);
		}
	}

	@Override
	public void update(Ausleihartikel ausleihartikel) {
		if (ausleihartikel != null) {
			hibernateTemplate.saveOrUpdate(ausleihartikel);
		}
	}

	@Override
	public void delete(Ausleihartikel ausleihartikel) {
		if (ausleihartikel != null) {
			hibernateTemplate.delete(ausleihartikel);
		}
	}

	@Override
	public void shutdown() {
		hibernateTemplate.getSessionFactory().openSession()
		.createSQLQuery("SHUTDOWN").executeUpdate();
	}

}
