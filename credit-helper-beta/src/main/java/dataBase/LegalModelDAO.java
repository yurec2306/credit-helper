package dataBase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.LegalModel;

public class LegalModelDAO implements AutoCloseable {
	
	private static Logger logger = LoggerFactory.getLogger(LegalModelDAO.class);
	
	private SessionFactory sessionFactory;
	
	public LegalModelDAO() {
		logger.trace("Calling LegalModelDAO()");
		this.sessionFactory = new Configuration().configure().buildSessionFactory();
		logger.trace("Returning from LegalModelDAO()");
	}

	public SessionFactory getSession() {
		return this.sessionFactory;
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public void saveOrUpdate(LegalModel model) {
		logger.trace("Calling saveOrUpdate({})", model);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(model);
		session.getTransaction().commit();
		session.close();
		
		logger.trace("Returning from saveOrUpdate({})", model);
	}
	
	public void delete(LegalModel model) {
		logger.trace("Calling delete({})", model);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.delete(model);
		session.getTransaction().commit();
		session.close();
		
		logger.trace("Returning from delete({})", model);
	}
	
	public LegalModel getModel(String name) {
		logger.trace("Calling getModel({})", name);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Query<LegalModel> query = session.createQuery("FROM Legal_Forms WHERE organization_name = :name", LegalModel.class);
		query.setParameter("name", name);
		LegalModel model = query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		logger.debug("model: {}", model);
		
		logger.trace("Returning from getModel({})", name);
		return model;
	}

	public List<LegalModel> getAllModels() {
		logger.trace("Calling getAllModels()");
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Query<LegalModel> query = session.createQuery("FROM Legal_Forms", LegalModel.class);
		List<LegalModel> models = query.list();
		session.getTransaction().commit();
		session.close();
		
		logger.debug("models: {}", models);
		
		logger.trace("Returning from getAllModels()");
		return models;
	}
	
	@Override
	public void close() {
		logger.trace("Calling close()");
		this.sessionFactory.close();	
		logger.trace("Returning from close()");	
	}
	
}
