package dataBase;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.accounts.AccountModel;

public class AccountDAO implements AutoCloseable {
	
	private static Logger logger = LoggerFactory.getLogger(AccountDAO.class);

	private SessionFactory sessionFactory;

	public AccountDAO() {
		logger.trace("Calling AccountDAO()");
		this.sessionFactory = new Configuration().configure().buildSessionFactory();
		logger.trace("Returning from AccountDAO()");
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveOrUpdate(AccountModel model) {
		logger.trace("Calling saveOrUpdate({})", model);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(model);
		session.getTransaction().commit();
		session.close();
		
		logger.trace("Returning from saveOrUpdate({})", model);
	}

	public void delete(AccountModel model) {
		logger.trace("Calling delete({})", model);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.delete(model);
		session.getTransaction().commit();
		session.close();
		
		logger.trace("Returning from delete({})", model);
	}

	public AccountModel getAccount(String login, char[] password) {
		logger.trace("Calling getAccount({}, {})", login, password);
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Query<AccountModel> query = session.createQuery("FROM Accounts WHERE login = :login AND password = :password",
																									AccountModel.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		AccountModel model = query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		logger.debug("model: ", model);
		
		logger.trace("Returning from getAccount({}, {})", login, password);
		return model;
	}

	public ArrayList<AccountModel> getAllAccounts() {
		logger.trace("Calling getAllAccounts()");
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Query<AccountModel> query = session.createQuery("FROM Accounts", AccountModel.class);
		ArrayList<AccountModel> models = (ArrayList<AccountModel>) query.list();
		session.getTransaction().commit();
		session.close();
		
		logger.debug("models: ", models);
		
		logger.trace("Returning from getAllAccounts()");
		return models;
	}

	@Override
	public void close() {
		logger.trace("Calling close()");
		logger.debug("Closing sessionFactory");
		this.sessionFactory.close();
		logger.trace("Returning from close()");		
	}

}
