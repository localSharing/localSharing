package pandha.swe.localsharing.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.BenutzerRolle;

@Repository("benutzerDao")
@Cacheable("benutzer")
public class BenutzerDAOImpl implements BenutzerDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Benutzer findById(Long id) {
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
	@CacheEvict(value = "benutzer", allEntries = true)
	public void save(Benutzer benutzer) {

		if (benutzer != null) {
			Set<BenutzerRolle> a = benutzer.getBenutzerRolle();

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			for (BenutzerRolle benutzerRolle : a) {
				session.saveOrUpdate(benutzerRolle);
			}

			session.saveOrUpdate(benutzer);
			tx.commit();
			session.close();

		}
	}

	@Override
	@CacheEvict(value = "benutzer", allEntries = true)
	public void update(Benutzer benutzer) {
		if (benutzer != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			// Set<BenutzerRolle> a = benutzer.getBenutzerRolle();
			//
			// for (BenutzerRolle benutzerRolle : a) {
			// session.saveOrUpdate(benutzerRolle);
			// }

			session.saveOrUpdate(benutzer);
			tx.commit();
			session.close();
		}
	}

	@Override
	@CacheEvict(value = "benutzer", allEntries = true)
	public void delete(Benutzer benutzer) {
		if (benutzer != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.delete(benutzer);
			Set<BenutzerRolle> a = benutzer.getBenutzerRolle();

			for (BenutzerRolle benutzerRolle : a) {
				session.delete(benutzerRolle);
			}

			tx.commit();
			session.close();
		}
	}

	@Override
	public void shutdown() {
		hibernateTemplate.getSessionFactory().openSession()
				.createSQLQuery("SHUTDOWN").executeUpdate();
	}

	@Override
	public Benutzer findByEmail(String email) {

		if (email != null) {

			List<?> benutzerList = hibernateTemplate
					.findByCriteria(DetachedCriteria.forClass(Benutzer.class)
							.add(Restrictions.eq("email", email)));

			if (benutzerList != null && !benutzerList.isEmpty()
					&& benutzerList.get(0) instanceof Benutzer) {

				return (Benutzer) benutzerList.get(0);
			}
		}
		return null;
	}

}
