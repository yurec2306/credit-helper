package dataBase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.IndividualModel;

public class IndividualModelDAO implements AutoCloseable {
	
	private static Logger logger = LoggerFactory.getLogger(IndividualModelDAO.class);
	
	private SessionFactory sessionFactory;
	
	public IndividualModelDAO() {
		logger.trace("Calling IndividualModelDAO()");
		this.sessionFactory = new Configuration().configure().buildSessionFactory();
		logger.trace("Returning from IndividualModelDAO()");
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public void saveOrUpdate(IndividualModel model) {
		logger.trace("Calling saveOrUpdate({})", model);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(model);
		session.getTransaction().commit();
		session.close();
		
		logger.trace("Returning from saveOrUpdate({})", model);
	}
	
	public void delete(IndividualModel model) {
		logger.trace("Calling delete({})", model);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.delete(model);
		session.getTransaction().commit();
		session.close();
		
		logger.trace("Returning from delete({})", model);
	}
	
	public IndividualModel getModel(String lastName, String firstName, String middleName) {
		logger.trace("Calling getModel({}, {}, {})", lastName, firstName, middleName);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Query<IndividualModel> query = session.createQuery("FROM Forms WHERE last_name = :lastName AND first_name = :firstName AND middle_name = :middleName", IndividualModel.class);
		query.setParameter("lastName", lastName);
		query.setParameter("firstName", firstName);
		query.setParameter("middleName", middleName);
		IndividualModel model = query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		logger.debug("model: {}", model);
		
		logger.trace("Returning from getModel({}, {}, {})", lastName, firstName, middleName);
		return model;
	}
	
	public List<IndividualModel> getAllModels() {
		logger.trace("Calling getAllModels()");
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Query<IndividualModel> query = session.createQuery("FROM Forms", IndividualModel.class);
		List<IndividualModel> models = query.list();
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
