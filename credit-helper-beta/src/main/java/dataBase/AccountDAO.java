package dataBase;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import main.accounts.AccountModel;

public class AccountDAO implements AutoCloseable {
	
private SessionFactory sessionFactory;
	
	public AccountDAO() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public SessionFactory getSession() {
		return sessionFactory;
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public void saveOrUpdate(AccountModel model) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(model);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(AccountModel form) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(form);
		session.getTransaction().commit();
		session.close();
	}
	
	public AccountModel getAccount(String login, char[] password) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<AccountModel> query = session.createQuery("FROM Accounts WHERE login = :login AND password = :password", AccountModel.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		AccountModel model = query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return model;
	}
	
	public ArrayList<AccountModel> getAllAccounts() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<AccountModel> query = session.createQuery("FROM Accounts", AccountModel.class);
		ArrayList<AccountModel> models = (ArrayList<AccountModel>) query.list();
		session.getTransaction().commit();
		session.close();
		return models;
	}

	public void close() {
		sessionFactory.close();	
	}

}
