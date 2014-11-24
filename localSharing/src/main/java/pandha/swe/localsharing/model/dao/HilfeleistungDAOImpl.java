package pandha.swe.localsharing.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pandha.swe.localsharing.model.Hilfeleistung;

@Repository("hilfeleistungDao")
public class HilfeleistungDAOImpl implements HilfeleistungDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public Hilfeleistung findById(Long id) {
		Hilfeleistung hilfeleistung = (Hilfeleistung) hibernateTemplate.get(
				Hilfeleistung.class, id);
		return hilfeleistung;
	}

	@Override
	public List<Hilfeleistung> findAll() {
		List<Hilfeleistung> hilfeleistung = hibernateTemplate.loadAll(Hilfeleistung.class);
		return hilfeleistung;
	}

	@Override
	public void save(Hilfeleistung hilfeleistung) {//TODO hilfeleistung usw. ausschreiben
		if (hilfeleistung != null) {
			hibernateTemplate.saveOrUpdate(hilfeleistung);
		}
	}

	@Override
	public void update(Hilfeleistung hilfeleistung) {
		if (hilfeleistung != null) {
			hibernateTemplate.saveOrUpdate(hilfeleistung);
		}
	}

	@Override
	public void delete(Hilfeleistung hilfeleistung) {
		if (hilfeleistung != null) {
			hibernateTemplate.delete(hilfeleistung);
		}
	}

	@Override
	public void shutdown() {
		hibernateTemplate.getSessionFactory().openSession()
		.createSQLQuery("SHUTDOWN").executeUpdate();
	}

}
