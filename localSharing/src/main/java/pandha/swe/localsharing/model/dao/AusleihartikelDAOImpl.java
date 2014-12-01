package pandha.swe.localsharing.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;

@Repository("ausleihartikelDao")
public class AusleihartikelDAOImpl implements AusleihartikelDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Ausleihartikel findById(Long id) {
		Ausleihartikel ausleihartikel = (Ausleihartikel) hibernateTemplate.get(
				Ausleihartikel.class, id);
		return ausleihartikel;
	}

	@Override
	public List<Ausleihartikel> findAll() {
		List<Ausleihartikel> ausleihartikel = hibernateTemplate
				.loadAll(Ausleihartikel.class);
		return ausleihartikel;
	}

	@Override
	public List<Ausleihartikel> findAllByBenutzer(Benutzer benutzer) {

		@SuppressWarnings("unchecked")
		List<Ausleihartikel> ausleihartikelListe = (List<Ausleihartikel>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Ausleihartikel.class)
						.add(Restrictions.eq("benutzer", benutzer)));

		return ausleihartikelListe;
	}

	@Override
	public Long save(Ausleihartikel ausleihartikel) {
		if (ausleihartikel != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Serializable save = session.save(ausleihartikel);
			Long id = (Long) save;

			tx.commit();
			session.close();

			return id;

		}
		return null;
	}

	@Override
	public void update(Ausleihartikel ausleihartikel) {
		if (ausleihartikel != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.saveOrUpdate(ausleihartikel);
			tx.commit();
			session.close();

		}
	}

	@Override
	public void delete(Ausleihartikel ausleihartikel) {
		if (ausleihartikel != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.delete(ausleihartikel);
			tx.commit();
			session.close();

		}
	}

	@Override
	public void shutdown() {
		hibernateTemplate.getSessionFactory().openSession()
				.createSQLQuery("SHUTDOWN").executeUpdate();
	}

}
