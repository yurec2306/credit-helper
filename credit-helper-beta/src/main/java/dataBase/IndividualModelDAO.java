package dataBase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import main.IndividualModel;

public class IndividualModelDAO implements AutoCloseable {
	
	private SessionFactory sessionFactory;
	
	public IndividualModelDAO() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public SessionFactory getSession() {
		return sessionFactory;
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public void saveOrUpdate(IndividualModel model) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(model);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(IndividualModel form) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(form);
		session.getTransaction().commit();
		session.close();
	}
	
	public IndividualModel getModel(String lastName, String firstName, String middleName) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<IndividualModel> query = session.createQuery("FROM Forms WHERE last_name = :lastName AND first_name = :firstName AND middle_name = :middleName", IndividualModel.class);
		query.setParameter("lastName", lastName);
		query.setParameter("firstName", firstName);
		query.setParameter("middleName", middleName);
		IndividualModel model = query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return model;
	}
	
	public List<IndividualModel> getAllModels() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<IndividualModel> query = session.createQuery("FROM Forms", IndividualModel.class);
		List<IndividualModel> models = query.list();
		session.getTransaction().commit();
		session.close();
		return models;
	}

	public void close() {
		sessionFactory.close();	
	}
	
}
