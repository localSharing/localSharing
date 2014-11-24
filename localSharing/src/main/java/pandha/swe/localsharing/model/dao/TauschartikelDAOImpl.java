package pandha.swe.localsharing.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pandha.swe.localsharing.model.Tauschartikel;

@Repository("tauschartikelDao")
public class TauschartikelDAOImpl implements TauschartikelDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Tauschartikel findById(Long id) {
		Tauschartikel tauschartikel = (Tauschartikel) hibernateTemplate.get(
				Tauschartikel.class, id);
		return tauschartikel;
	}

	@Override
	public List<Tauschartikel> findAll() {
		List<Tauschartikel> tauschartikel = hibernateTemplate.loadAll(Tauschartikel.class);
		return tauschartikel;
	}

	@Override
	public void save(Tauschartikel tauschartikel) {
		if (tauschartikel != null) {
			hibernateTemplate.saveOrUpdate(tauschartikel);
		}
	}

	@Override
	public void update(Tauschartikel tauschartikel) {
		if (tauschartikel != null) {
			hibernateTemplate.saveOrUpdate(tauschartikel);
		}
	}

	@Override
	public void delete(Tauschartikel tauschartikel) {
		if (tauschartikel != null) {
			hibernateTemplate.delete(tauschartikel);
		}
	}

	@Override
	public void shutdown() {
		hibernateTemplate.getSessionFactory().openSession()
				.createSQLQuery("SHUTDOWN").executeUpdate();
	}

}
