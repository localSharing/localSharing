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

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Bewertung;

@Repository("bewertungDao")
public class BewertungDAOImpl implements BewertungDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Bewertung findById(Long id) {
		Bewertung bewertung = hibernateTemplate.get(Bewertung.class, id);
		return bewertung;
	}

	@Override
	public List<Bewertung> findAll() {
		List<Bewertung> bewertungen = hibernateTemplate
				.loadAll(Bewertung.class);
		return bewertungen;
	}

	@Override
	public List<Bewertung> findAllByAngebot(Angebot angebot) {

		@SuppressWarnings("unchecked")
		List<Bewertung> bewertungen = (List<Bewertung>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Bewertung.class).add(
						Restrictions.eq("angebot", angebot)));

		return bewertungen;
	}

	@Override
	public List<Bewertung> findAllByBewerter(Benutzer bewerter) {

		@SuppressWarnings("unchecked")
		List<Bewertung> bewertungen = (List<Bewertung>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Bewertung.class).add(
						Restrictions.eq("bewerter", bewerter)));

		return bewertungen;
	}

	@Override
	public Long save(Bewertung bewertung) {

		if (bewertung != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Serializable save = session.save(bewertung);
			Long id = (Long) save;

			tx.commit();
			session.close();

			return id;
		}

		return null;
	}

	@Override
	public void update(Bewertung bewertung) {

		if (bewertung != null) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.saveOrUpdate(bewertung);
			tx.commit();
			session.close();
		}
	}

	@Override
	public void delete(Bewertung bewertung) {

		if (bewertung != null) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.delete(bewertung);
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
