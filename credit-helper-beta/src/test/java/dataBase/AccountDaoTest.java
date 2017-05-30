package dataBase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.lang.annotation.Annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.junit.Test;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.fasterxml.classmate.AnnotationInclusion;

import main.accounts.AccountModel;

public class AccountDaoTest {

	@Test
	public void testSaveOrUpdate() {
		AccountDAO dao = new AccountDAO();
		File f = new File("..\\HibernateTest.cfg.xml");
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		AccountModel model = new AccountModel();
		model.setFirstName("Test Name");
		dao.saveOrUpdate(model);
		dao.close();
	}
	
	@Test
	public void testDelete() {
		
	}
	
	@Test
	public void testGetAccount() {

	}
	
	@Test
	public void testGetAllAccounts() {
	
	}
	
	@Test
	public void testClose() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactoryMock = mock(SessionFactory.class);
		dao.setSessionFactory(sessionFactoryMock);
		dao.close();
		verify(sessionFactoryMock).close();
	}
	
}
