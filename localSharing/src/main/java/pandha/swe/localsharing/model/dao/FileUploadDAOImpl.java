package pandha.swe.localsharing.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Repository("fileDao")
public class FileUploadDAOImpl implements FileUploadDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FileUpload> findAll() {

		List<?> fileListUN = hibernateTemplate.loadAll(FileUpload.class);

		if (fileListUN != null && fileListUN.size() > 0) {

			List<FileUpload> fileList = new ArrayList<>();

			for (int i = 0; i < fileListUN.size(); i++) {

				if (fileListUN.get(i) instanceof FileUpload) {
					fileList.add((FileUpload) fileListUN.get(i));

				}

			}

		}

		return null;
	}

	@Override
	public void save(FileUpload file) {

		if (file != null) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(file);
			tx.commit();
			session.close();

		}

	}

	@Override
	public void delete(FileUpload file) {

		if (file != null) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(file);
			tx.commit();
			session.close();

		}
	}

	@Override
	public FileUpload findByAssociated(Long id, FileUploadType type) {
		if (type != null && id != null) {

			List<?> fileListUN = hibernateTemplate
					.findByCriteria(DetachedCriteria.forClass(FileUpload.class)
							.add(Restrictions.eq("fileUploadType", type))
							.add(Restrictions.eq("assID", id)));

			if (fileListUN != null && fileListUN.size() > 0) {

				List<FileUpload> fileList = new ArrayList<>();

				for (int i = 0; i < fileListUN.size(); i++) {

					if (fileListUN.get(i) instanceof FileUpload) {
						fileList.add((FileUpload) fileListUN.get(i));

					}

				}

				return fileList.get(0);
			}
		}
		return null;
	}

	@Override
	public List<FileUpload> findAllOfType(FileUploadType type) {
		if (type != null) {

			List<?> fileListUN = hibernateTemplate
					.findByCriteria(DetachedCriteria.forClass(FileUpload.class)
							.add(Restrictions.eq("fileUploadType", type)));

			if (fileListUN != null && fileListUN.size() > 0) {

				List<FileUpload> fileList = new ArrayList<>();

				for (int i = 0; i < fileListUN.size(); i++) {

					if (fileListUN.get(i) instanceof FileUpload) {
						fileList.add((FileUpload) fileListUN.get(i));

					}

				}

				return fileList;
			}
		}
		return null;
	}

	@Override
	public void shutdown() {
		hibernateTemplate.getSessionFactory().openSession()
				.createSQLQuery("SHUTDOWN").executeUpdate();
	}

}
