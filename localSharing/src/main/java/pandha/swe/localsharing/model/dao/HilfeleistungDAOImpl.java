package pandha.swe.localsharing.model.dao;

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
import pandha.swe.localsharing.model.Hilfeleistung;

@Repository("hilfeleistungDao")
public class HilfeleistungDAOImpl implements HilfeleistungDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Hilfeleistung findById(Long id) {
		Hilfeleistung hilfeleistung = (Hilfeleistung) hibernateTemplate.get(
				Hilfeleistung.class, id);
		return hilfeleistung;
	}

	@Override
	public List<Hilfeleistung> findAll() {
		List<Hilfeleistung> hilfeleistung = hibernateTemplate
				.loadAll(Hilfeleistung.class);
		return hilfeleistung;
	}

	@Override
	public List<Hilfeleistung> findAllByBenutzer(Benutzer benutzer) {

		@SuppressWarnings("unchecked")
		List<Hilfeleistung> hilfeleistungListe = (List<Hilfeleistung>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Hilfeleistung.class)
						.add(Restrictions.eq("benutzer", benutzer)));

		return hilfeleistungListe;
	}

	@Override
	public void save(Hilfeleistung hilfeleistung) {
		if (hilfeleistung != null) {
			hibernateTemplate.saveOrUpdate(hilfeleistung);
		}
	}

	@Override
	public void update(Hilfeleistung hilfeleistung) {
		if (hilfeleistung != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.saveOrUpdate(hilfeleistung);
			tx.commit();
			session.close();

		}
	}

	@Override
	public void delete(Hilfeleistung hilfeleistung) {
		if (hilfeleistung != null) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.delete(hilfeleistung);
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
