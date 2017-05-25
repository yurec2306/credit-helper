package dataBase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import main.LegalModel;

public class LegalModelDAO implements AutoCloseable {
	
	private SessionFactory sessionFactory;
	
	public LegalModelDAO() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public SessionFactory getSession() {
		return sessionFactory;
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public void saveOrUpdate(LegalModel model) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(model);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(LegalModel form) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(form);
		session.getTransaction().commit();
		session.close();
	}
	
	public LegalModel getModel(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<LegalModel> query = session.createQuery("FROM Legal_Forms WHERE organization_name = :name", LegalModel.class);
		query.setParameter("name", name);
		LegalModel model = query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return model;
	}

	public void close() {
		sessionFactory.close();	
	}

	public List<LegalModel> getAllModels() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<LegalModel> query = session.createQuery("FROM Legal_Forms", LegalModel.class);
		List<LegalModel> models = query.list();
		session.getTransaction().commit();
		session.close();
		return models;
	}
	
}
