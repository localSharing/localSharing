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

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Tauschartikel;

@Repository("tauschartikelDao")
public class TauschartikelDAOImpl implements TauschartikelDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Tauschartikel findById(Long id) {
		Tauschartikel tauschartikel = (Tauschartikel) hibernateTemplate.get(
				Tauschartikel.class, id);
		return tauschartikel;
	}

	@Override
	public List<Tauschartikel> findAll() {
		List<Tauschartikel> tauschartikel = hibernateTemplate
				.loadAll(Tauschartikel.class);
		return tauschartikel;
	}

	@Override
	public Long save(Tauschartikel tauschartikel) {
		if (tauschartikel != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Serializable save = session.save(tauschartikel);
			Long id = (Long) save;
			tx.commit();
			session.close();

			return id;
		}
		return null;
	}

	@Override
	public void update(Tauschartikel tauschartikel) {
		if (tauschartikel != null) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.saveOrUpdate(tauschartikel);
			tx.commit();
			session.close();

		}
	}

	@Override
	public void delete(Tauschartikel tauschartikel) {
		if (tauschartikel != null) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(tauschartikel);
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
	public List<Tauschartikel> findAllByBenutzer(Benutzer benutzer) {

		@SuppressWarnings("unchecked")
		List<Tauschartikel> tauschartikelListe = (List<Tauschartikel>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Tauschartikel.class)
						.add(Restrictions.eq("benutzer", benutzer)));

		return tauschartikelListe;
	}

}
