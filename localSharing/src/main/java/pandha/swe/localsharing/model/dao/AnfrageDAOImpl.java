package pandha.swe.localsharing.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pandha.swe.localsharing.model.Anfrage;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.enums.AnfrageStatus;

@Repository("anfrageDao")
public class AnfrageDAOImpl implements AnfrageDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Cacheable("anfragen")
	@Override
	public Anfrage findById(Long id) {
		Anfrage anfrage = hibernateTemplate.get(Anfrage.class, id);
		return anfrage;
	}

	@Override
	public List<Anfrage> findAllByAngebot(Angebot angebot) {

		@SuppressWarnings("unchecked")
		List<Anfrage> anfragen = (List<Anfrage>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Anfrage.class).add(
						Restrictions.eq("angebot", angebot)));
		return anfragen;
	}

	@Override
	public List<Anfrage> findAllBySender(Benutzer sender) {

		@SuppressWarnings("unchecked")
		List<Anfrage> anfragen = (List<Anfrage>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Anfrage.class).add(
						Restrictions.eq("sender", sender)));

		return anfragen;
	}

	@Override
	public List<Anfrage> findAllByEmpfaenger(Benutzer empfanger) {

		@SuppressWarnings("unchecked")
		List<Anfrage> anfragen = (List<Anfrage>) hibernateTemplate
				.findByCriteria(DetachedCriteria
						.forClass(Anfrage.class)
						.createAlias("angebot.benutzer", "empfaenger")
						.add(Restrictions.eq("empfaenger.userid",
								empfanger.getId())));

		return anfragen;
	}
	
	@Override
	public List<Anfrage> findAngenommeneAnfragenByAngebotAndSender(
			Angebot angebot,
			Benutzer sender) {
		@SuppressWarnings("unchecked")
		List<Anfrage> anfragen = (List<Anfrage>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(Anfrage.class)
						.add(Restrictions.eq("status", AnfrageStatus.angenommen))
						.add(Restrictions.eq("angebot", angebot))
						.add(Restrictions.eq("sender", sender)));
		return anfragen;
	}

	@Override
	@Cacheable("anfragen")
	public List<Anfrage> findAll() {
		List<Anfrage> anfragen = hibernateTemplate.loadAll(Anfrage.class);
		return anfragen;
	}

	@Override
	public void save(Anfrage anfrage) {

		if (anfrage != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(anfrage);

			tx.commit();
			session.close();
		}

	}

	@Override
	public void update(Anfrage anfrage) {

		if (anfrage != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.saveOrUpdate(anfrage);
			tx.commit();
			session.close();
		}

	}

	@Override
	public void delete(Anfrage anfrage) {

		if (anfrage != null) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.delete(anfrage);
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
