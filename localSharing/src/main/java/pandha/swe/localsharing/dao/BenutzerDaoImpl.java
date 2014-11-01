package pandha.swe.localsharing.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pandha.swe.localsharing.model.Benutzer;

@Repository("benutzerDao")
public class BenutzerDaoImpl implements BenutzerDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Benutzer findById(long id) {
		Benutzer benutzer = (Benutzer) hibernateTemplate
				.get(Benutzer.class, id);
		return benutzer;
	}

	@Override
	public List<Benutzer> findAll() {
		List<Benutzer> benutzer = hibernateTemplate.loadAll(Benutzer.class);
		return benutzer;
	}

	@Override
	public void save(Benutzer benutzer) {
		hibernateTemplate.saveOrUpdate(benutzer);
	}

	@Override
	public void update(Benutzer benutzer) {
		hibernateTemplate.saveOrUpdate(benutzer);
	}

	@Override
	public void delete(Benutzer benutzer) {
		hibernateTemplate.delete(benutzer);
	}

	@Override
	public void shutdown() {
		hibernateTemplate.getSessionFactory().openSession()
				.createSQLQuery("SHUTDOWN").executeUpdate();
	}

	@Override
	public Benutzer findByEmail(String email) {

		List<?> benutzerList = hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Benutzer.class).add(
						Restrictions.eq("email", email)));

		if (benutzerList != null && benutzerList.size() == 1
				&& benutzerList.get(0) instanceof Benutzer) {

			return (Benutzer) benutzerList.get(0);
		}
		return null;
	}

}
